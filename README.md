# pjson

Fast clojure json parser

The parser's focus is on speed and not validating json.

[![Clojars Project](http://clojars.org/pjson/latest-version.svg)](http://clojars.org/pjson)


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

(require '[pjson.core :refer [bts->json DEFAULT_CHARSET]])
(require '[pjson.data :refer [msg-bts]])

(bts->json msg-bts)
;;{"id" "0.0.1.71.105.212.33.205.21.86.123.210.94.161.24.1.3", "uuid" 5090315992110618240, "id2" 11, "ts" 1406229815757, "type" 1, "anothertype" "null", "events" {"info" {"id" 770128}, "id2" 3, "events" {"id" 930415460}, "timestamp" 1406229815757}, "size" 5, "slots" 1, "geo" {"city" 9065607, "country" 59, "state" 162, "zip" 121397}}

;;with charset
(bts->json (get-charset "UTF-8") msg-bts)

;;specify array position
(bts->json DEFAULT_CHARSET msg-bts 0 (count msg-bts))


```

### Java

```java
byte[] msg_bts = "{\"id\" \"0.0.1.71.105.212.33.205.21.86.123.210.94.161.24.1.3\", \"uuid\" 5090315992110618240, \"id2\" 11, \"ts\" 1406229815757, \"type\" 1, \"anothertype\" \"null\", \"events\" {\"info\" {\"id\" 770128}, \"id2\" 3, \"events\" {\"id\" 930415460}, \"timestamp\" 1406229815757}, \"size\" 5, \"slots\" 1, \"geo\" {\"city\" 9065607, \"country\" 59, \"state\" 162, \"zip\" 121397}}".getBytes();

//if the message starts with an object we can cast to a Map, arrays are of type List.
Map<Object, Object> obj = (Map<Object, Object>) PJSON.defaultParse(StringUtil.DEFAULT_CHAR_SET, msg_bts);
System.out.println(obj);

```

## Benchmarks

Using criterium and JVM 1.7 b60 and Charset "ISO-8859-1"

```
Each run calls the parse function 100 000 times.

Checking outlier significance
Evaluation count : 240 in 60 samples of 4 calls.
             Execution time mean : 290.280219 ms
    Execution time std-deviation : 5.896978 ms
   Execution time lower quantile : 283.481998 ms ( 2.5%)
   Execution time upper quantile : 304.616505 ms (97.5%)
                   Overhead used : 1.788439 ns

Found 3 outliers in 60 samples (5.0000 %)
	low-severe	 3 (5.0000 %)
Variance from outliers : 9.3690 % Variance is slightly inflated by outliers
 
Single execution: 0,0029 ms (290/100000)

```
## License

Copyright © 2014 gerritjvv@gmail.com

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
