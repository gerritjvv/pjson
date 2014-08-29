# pjson

Fast clojure json parser

The parser's focus is on speed and not validating json.

[![Clojars Project](http://clojars.org/pjson/latest-version.svg)](http://clojars.org/pjson)

All data returned are clojure persistent data structures that can be used with assoc, merge, map, reduce etc.


## Speed, Charsets and JVM version

Although the code compiles with JRE 1.5 compatibility your encouraged to run with at least 1.7 b53 or upwards.
This is because in 1.7 b53 major performance improvements have been done on String creations, which is central to this library.

see: 

https://blogs.oracle.com/xuemingshen/entry/faster_new_string_bytes_cs
http://java-performance.info/charset-encoding-decoding-java-78/

Always use the DEFAULT_CHARSET (from this library) which use the "ISO-8859-1" encoding.

## Usage

### Clojure

```clojure
(require '[pjson.core :refer [asObj asString]])

(def m (asObj "{\"a\": 1}"))
;;converts a String to a JSON object {} converts to Maps and [] converts to Vectors
(type m)
;;pjson.JSONAssociative
[(instance? java.util.Map m) (instance? clojure.lang.IPersistentMap m) (map? m)]
;;[true true true]

(def v (asObj "[1, 2, 3]"))
;;converts a String to a JSON object {} converts to Maps and [] converts to Vectors
(type m)
;;pjson.JSONAssociative$JSONVector
[(instance? java.util.Collection v) (instance? clojure.lang.IPersistentVector v) (vector? v)]
;;[true true true]

(prn (asString m))
;;"{\"a\":1}"
(prn (asString {:a 2, :b 2}))
"{\"b\":2,\"a\":2}"

```

### Java

```java
byte[] msg_bts = "{\"id\" \"0.0.1.71.105.212.33.205.21.86.123.210.94.161.24.1.3\", \"uuid\" 5090315992110618240, \"id2\" 11, \"ts\" 1406229815757, \"type\" 1, \"anothertype\" \"null\", \"events\" {\"info\" {\"id\" 770128}, \"id2\" 3, \"events\" {\"id\" 930415460}, \"timestamp\" 1406229815757}, \"size\" 5, \"slots\" 1, \"geo\" {\"city\" 9065607, \"country\" 59, \"state\" 162, \"zip\" 121397}}".getBytes();

//if the message starts with an object we can cast to a Map, arrays are of type List.
Map<Object, Object> obj = (Map<Object, Object>) PJSON.defaultParse(StringUtil.DEFAULT_CHAR_SET, msg_bts);
System.out.println(obj);

```

## Benchmarks

To run your own benchmarks use ```lein perforate```.

Using criterium and JVM 1.7 b60 and Charset "ISO-8859-1"

Note:

The benchmarks use the default lazy parsing of both boon and pjson.
PJSON takes less than half

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
## License

Copyright © 2014 gerritjvv@gmail.com

Distributed under the Eclipse Public License either version 1.0
