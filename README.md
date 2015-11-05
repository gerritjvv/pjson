# pjson

Fast clojure json parser

The parser's focus is on speed and not validating json.

[![Build Status](https://travis-ci.org/gerritjvv/pjson.svg)](https://travis-ci.org/gerritjvv/pjson)

[![Clojars Project](http://clojars.org/pjson/latest-version.svg)](http://clojars.org/pjson)

All data returned are clojure persistent data structures that can be used with assoc, merge, map, reduce etc.

## Usage

### Clojure

```clojure
(require '[pjson.core :refer [read-str write-str get-charset]])

(def m (read-str "{\"a\": 1}"))
;;converts a String to a JSON object {} converts to Maps and [] converts to Vectors
(type m)
;;pjson.JSONAssociative
[(instance? java.util.Map m) (instance? clojure.lang.IPersistentMap m) (map? m)]
;;[true true true]

(def v (read-str "[1, 2, 3]"))
;;converts a String to a JSON object {} converts to Maps and [] converts to Vectors
(type v)
;;pjson.JSONAssociative$JSONVector
[(instance? java.util.Collection v) (instance? clojure.lang.IPersistentVector v) (vector? v)]
;;[true true true]

(prn (write-str m))
;;"{\"a\":1}"
(prn (write-str {:a 2, :b 2}))
"{\"b\":2,\"a\":2}"

;; read-str with different charsets
(read-str "{\"a\": 1}" (get-charset "UTF-8"))
;;{"a" 1}
;; read-str with different default charset at a different offset
(def s "myprop={\"a\": 1}")
(read-str s (get-charset "UTF-8") 8 (count s))
;;{"a" 1}


```

## Who should use this library

*   If any of the tradeoffs are not a stopper for your project
*   You have some knowledge (or control) of the content you parse.
*   You need speed at all cost.
*   You spend more than 30-50% of your application time on json parsing, and need to scale more.

### Assumptions about the data for the sake of performance

*   See Validation.
*   For maximum speed your JSON data should be compatible with the "ISO-8859-1" encoding, otherwise the way Strings are decoded by the JVM will slow you down. Please note that this does not mean that you cannot use other encoding formats, that that they are not the fastest and only affects speed if you translate from bytes to chars, if you already have a String or char array then no extra encoding is done.

### Validation

This library is about parsing the data as fast as possible, and validation is not done extensively.  
Exceptions are thrown where in a performant way data is deemed invalid, e.g 

```clojure
(require '[pjson.core :as pjson])

 (pjson/read-str "{\"abc\": bla}")
 ;; {}
 
 (pjson/read-str "{\"abc\": bla, "\edf\":122}")
 ;; RuntimeException Unsupported character: \edf
 
```

Data validation should be a separate step after parsing as required and normally can be done much faster on an application specific way than can be done generically by the parser.

```clojure
(require '[pjson.core :as pjson])

(def user (pjson/read-str "{\"abc\": bla}"))

(defn valid-user-token? [msg] (get msg "user"))

(valid-user-token? user)
;; nil
(valid-user-token? (pjson/read-str "{\"user\": \"abc\"}"))
;; "abc"
;; is much more performant and usable than


(def user (pjson/read-str "{\"abc\": bla}"))
;;ParserException !!!

```

So my point of view is ```invalid-document -> exception == invalid-document -> validation```. 

### Tradeoffs

*   sun.misc.Unsafe must be supported on the JVM you use, if you are not using the Oracle JVM please test first.
*   no format checking is provided.



## Speed, Charsets and JVM version

Although the code compiles with JRE 1.5 compatibility your encouraged to run with at least 1.7 b53 or upwards.
This is because in 1.7 b53 major performance improvements have been done on String creations, which is central to this library.

see: 

https://blogs.oracle.com/xuemingshen/entry/faster_new_string_bytes_cs
http://java-performance.info/charset-encoding-decoding-java-78/

Always use the DEFAULT_CHARSET (from this library) which use the "ISO-8859-1" encoding.

## JSONP

This library concentrates on maximum performance and thus tries to manipulate data is little as possible.
If you are using this library in a backend to send data to Java Script, you need to escape your strings according to
the document here: http://timelessrepo.com/json-isnt-a-javascript-subset.

## Charsets and language encodings

The fastest Charset is used by default which is the "ISO-8859-1" charset (optimized in java 1.7).

### Compatibility with other parser libraries.

The ```pjson.core``` namespace defines the following single arity functions to allow easy swap between other json libraries.

  *  write-str, read-str is the same as data.json
  *  generate-string, parse-string for cheshire
  *  asObj, asString for backwards compatibility in this library

### Java

```java
byte[] msg_bts = "{\"id\" \"0.0.1.71.105.212.33.205.21.86.123.210.94.161.24.1.3\", \"uuid\" 5090315992110618240, \"id2\" 11, \"ts\" 1406229815757, \"type\" 1, \"anothertype\" \"null\", \"events\" {\"info\" {\"id\" 770128}, \"id2\" 3, \"events\" {\"id\" 930415460}, \"timestamp\" 1406229815757}, \"size\" 5, \"slots\" 1, \"geo\" {\"city\" 9065607, \"country\" 59, \"state\" 162, \"zip\" 121397}}".getBytes();

//if the message starts with an object we can cast to a Map, arrays are of type List.
Map<Object, Object> obj = (Map<Object, Object>) PJSON.defaultLazyParse(StringUtil.DEFAULT_CHAR_SET, msg_bts);
System.out.println(obj);

```

#Benchmarks 

To run your own benchmarks use ```lein perforate```.  
The run takes some time, you might want to comment out the slower ones to speed up the process.  

Using criterium and JVM 1.7 b60 and Charset "ISO-8859-1"  


See [https://github.com/gerritjvv/pjson/blob/master/benchmarks.md]

## Benchmark 1 (Lazy minimum parsing)

The aim is to see how fast I can pass a message to a library,   
do the bare minimum parsing and return back a result.  
This benchmark is not fair to the non lazy libraries but does show a practical example where in practice you almost never access 100% of a message.  

### Summary

Summary of the benchmark results are below (in order of faster to slowest).

<table border="0">
<tr><th>Library</th><th>Mean in ms (lower is better)</th></tr>
<tr><td>pjson</td><td>348</td>
<tr><td>boon</td><td>806</td>
<tr><td>cheshire</td><td>2700</td>
<tr><td>clj-json</td><td>2900</td>
<tr><td>data.json</td><td>7000</td>
</table>

### Details

```
Goal:  JSON Parse Benchmark
-----

Case:  :pjson
Evaluation count : 180 in 60 samples of 3 calls.
             Execution time mean : 348.629604 ms
    Execution time std-deviation : 6.353164 ms
   Execution time lower quantile : 337.189665 ms ( 2.5%)
   Execution time upper quantile : 355.916665 ms (97.5%)
                   Overhead used : 1.788077 ns

Found 1 outliers in 60 samples (1.6667 %)
	low-severe	 1 (1.6667 %)
 Variance from outliers : 7.7888 % Variance is slightly inflated by outliers

Case:  :boon
Evaluation count : 120 in 60 samples of 2 calls.
             Execution time mean : 806.579640 ms
    Execution time std-deviation : 7.381952 ms
   Execution time lower quantile : 794.885499 ms ( 2.5%)
   Execution time upper quantile : 825.284011 ms (97.5%)
                   Overhead used : 1.788077 ns

Found 3 outliers in 60 samples (5.0000 %)
	low-severe	 1 (1.6667 %)
	low-mild	 2 (3.3333 %)
 Variance from outliers : 1.6389 % Variance is slightly inflated by outliers

Case:  :data.json
Evaluation count : 60 in 60 samples of 1 calls.
             Execution time mean : 7.652088 sec
    Execution time std-deviation : 83.905373 ms
   Execution time lower quantile : 7.549722 sec ( 2.5%)
   Execution time upper quantile : 7.837267 sec (97.5%)
                   Overhead used : 1.788077 ns

Found 2 outliers in 60 samples (3.3333 %)
	low-severe	 2 (3.3333 %)
 Variance from outliers : 1.6389 % Variance is slightly inflated by outliers

Case:  :clj-json
Evaluation count : 60 in 60 samples of 1 calls.
             Execution time mean : 2.923051 sec
    Execution time std-deviation : 30.183434 ms
   Execution time lower quantile : 2.890043 sec ( 2.5%)
   Execution time upper quantile : 3.001162 sec (97.5%)
                   Overhead used : 1.788077 ns

Found 2 outliers in 60 samples (3.3333 %)
	low-severe	 2 (3.3333 %)
 Variance from outliers : 1.6389 % Variance is slightly inflated by outliers

Case:  :cheshire
Evaluation count : 60 in 60 samples of 1 calls.
             Execution time mean : 2.729360 sec
    Execution time std-deviation : 30.997395 ms
   Execution time lower quantile : 2.692323 sec ( 2.5%)
   Execution time upper quantile : 2.804218 sec (97.5%)
                   Overhead used : 1.788077 ns

Found 2 outliers in 60 samples (3.3333 %)
	low-severe	 2 (3.3333 %)
 Variance from outliers : 1.6389 % Variance is slightly inflated by outliers
 
```
 
## Gratitude

This library could not have been written without the wonderful work done on the json boon library already.

It has shown me how to use Unsafe to create String(s) really fast, and where appropriate I've shamelessly copied.

#Contribute

Its impossible to create the perfect bug free library. So feel free if you have an improvement, bug fix or idea  
to open a Git Issue and or send me a Pull Request.

Github sometimes is not great for notifying when a new Issue has been made, so if I do not respond please ping me  
on my mail below :)

# License

Copyright © 2014 gerritjvv@gmail.com

Distributed under the Eclipse Public License either version 1.0
