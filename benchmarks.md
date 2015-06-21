#JSON Parse Practical Example

<table border="0">
<tr><th>Library</th><th>Mean in ms (lower is better) 0.2.7</th><th>0.2.8</th></tr>
<tr><td>pjson</td><td>10.183120 ms</td><td>9.526570 ms</td>
<tr><td>clj-json</td><td>41.713927 ms</td><td>42.148021 ms</td>
<tr><td>boon</td><td>44.195550 ms</td><td>45.949513 ms</td>
<tr><td>cheshire</td><td>49.784366 ms</td><td>50.600446 ms</td>
<tr><td>data.json</td><td>165.176958 ms</td><td>171.720518 ms</td>
</table>


```clojure
Goal:  JSON Parse Practical Example
-----
Case:  :data.json
Evaluation count : 360 in 60 samples of 6 calls.
             Execution time mean : 171.720518 ms
    Execution time std-deviation : 3.128919 ms
   Execution time lower quantile : 167.051332 ms ( 2.5%)
   Execution time upper quantile : 176.926498 ms (97.5%)
                   Overhead used : 1.881626 ns

Found 1 outliers in 60 samples (1.6667 %)
        low-severe       1 (1.6667 %)
 Variance from outliers : 7.7886 % Variance is slightly inflated by outliers

Case:  :pjson
Evaluation count : 6300 in 60 samples of 105 calls.
             Execution time mean : 9.526570 ms
    Execution time std-deviation : 158.549428 µs
   Execution time lower quantile : 9.302846 ms ( 2.5%)
   Execution time upper quantile : 9.902265 ms (97.5%)
                   Overhead used : 1.881626 ns

Found 3 outliers in 60 samples (5.0000 %)
        low-severe       2 (3.3333 %)
        low-mild         1 (1.6667 %)
 Variance from outliers : 6.2488 % Variance is slightly inflated by outliers

Case:  :cheshire
Evaluation count : 1200 in 60 samples of 20 calls.
             Execution time mean : 50.600446 ms
    Execution time std-deviation : 1.236219 ms
   Execution time lower quantile : 49.122398 ms ( 2.5%)
   Execution time upper quantile : 54.349364 ms (97.5%)
                   Overhead used : 1.881626 ns

Found 3 outliers in 60 samples (5.0000 %)
        low-severe       2 (3.3333 %)
        low-mild         1 (1.6667 %)
 Variance from outliers : 12.5577 % Variance is moderately inflated by outliers

Case:  :boon
Evaluation count : 1320 in 60 samples of 22 calls.
             Execution time mean : 45.949513 ms
    Execution time std-deviation : 777.118124 µs
   Execution time lower quantile : 44.886725 ms ( 2.5%)
   Execution time upper quantile : 47.947312 ms (97.5%)
                   Overhead used : 1.881626 ns

Found 2 outliers in 60 samples (3.3333 %)
        low-severe       2 (3.3333 %)
 Variance from outliers : 6.2620 % Variance is slightly inflated by outliers

Case:  :clj-json
Evaluation count : 1500 in 60 samples of 25 calls.
             Execution time mean : 42.148021 ms
    Execution time std-deviation : 2.859100 ms
   Execution time lower quantile : 40.430278 ms ( 2.5%)
   Execution time upper quantile : 49.266426 ms (97.5%)
                   Overhead used : 1.881626 ns

Found 5 outliers in 60 samples (8.3333 %)
        low-severe       3 (5.0000 %)
        low-mild         2 (3.3333 %)
 Variance from outliers : 51.7236 % Variance is severely inflated by outliers
```

#JSON Parse Benchmark

<table border="0">
<tr><th>Library</th><th>Mean in ms (lower is better) 0.2.7</th><th>0.2.8</th></tr>
<tr><td>pjson</td><td>3.471202 ms</td><td>3.397253 ms</td>
<tr><td>clj-json</td><td>30.000884 ms</td><td>30.696739 ms</td>
<tr><td>boon</td><td>7.775955 ms</td><td>8.178839 ms</td>
<tr><td>cheshire</td><td>26.599338 ms</td><td>27.984381 ms</td>
<tr><td>data.json</td><td>74.359619 ms</td><td>76.775012 ms</td>
</table>

```clojure
Goal:  JSON Parse Benchmark
-----
Case:  :clj-json
Evaluation count : 1980 in 60 samples of 33 calls.
             Execution time mean : 30.696739 ms
    Execution time std-deviation : 546.312660 µs
   Execution time lower quantile : 29.917998 ms ( 2.5%)
   Execution time upper quantile : 32.038518 ms (97.5%)
                   Overhead used : 1.881626 ns

Found 4 outliers in 60 samples (6.6667 %)
        low-severe       4 (6.6667 %)
 Variance from outliers : 7.7624 % Variance is slightly inflated by outliers

Case:  :boon
Evaluation count : 7380 in 60 samples of 123 calls.
             Execution time mean : 8.178839 ms
    Execution time std-deviation : 163.192384 µs
   Execution time lower quantile : 7.953372 ms ( 2.5%)
   Execution time upper quantile : 8.628879 ms (97.5%)
                   Overhead used : 1.881626 ns

Found 6 outliers in 60 samples (10.0000 %)
        low-severe       5 (8.3333 %)
        low-mild         1 (1.6667 %)
 Variance from outliers : 7.8791 % Variance is slightly inflated by outliers

Case:  :pjson
Evaluation count : 17880 in 60 samples of 298 calls.
             Execution time mean : 3.397253 ms
    Execution time std-deviation : 65.798192 µs
   Execution time lower quantile : 3.297106 ms ( 2.5%)
   Execution time upper quantile : 3.567381 ms (97.5%)
                   Overhead used : 1.881626 ns

Found 4 outliers in 60 samples (6.6667 %)
        low-severe       4 (6.6667 %)
 Variance from outliers : 7.8512 % Variance is slightly inflated by outliers

Case:  :cheshire
Evaluation count : 2160 in 60 samples of 36 calls.
             Execution time mean : 27.984381 ms
    Execution time std-deviation : 695.037351 µs
   Execution time lower quantile : 27.106720 ms ( 2.5%)
   Execution time upper quantile : 29.370169 ms (97.5%)
                   Overhead used : 1.881626 ns

Found 5 outliers in 60 samples (8.3333 %)
        low-severe       4 (6.6667 %)
        low-mild         1 (1.6667 %)
 Variance from outliers : 12.5828 % Variance is moderately inflated by outliers

Case:  :data.json
Evaluation count : 840 in 60 samples of 14 calls.
             Execution time mean : 76.775012 ms
    Execution time std-deviation : 1.458656 ms
   Execution time lower quantile : 75.032641 ms ( 2.5%)
   Execution time upper quantile : 80.653407 ms (97.5%)
                   Overhead used : 1.881626 ns

Found 5 outliers in 60 samples (8.3333 %)
        low-severe       3 (5.0000 %)
        low-mild         2 (3.3333 %)
 Variance from outliers : 7.8323 % Variance is slightly inflated by outliers

```

#JSON Parse Benchmark - parse all fields and substructures

<table border="0">
<tr><th>Library</th><th>Mean in ms (lower is better) 0.2.7</th><th>0.2.8</th></tr>
<tr><td>pjson</td><td>210.109698 ms</td><td>214.128818 ms</td>
<tr><td>clj-json</td><td>234.800428 ms</td><td>244.911982 ms</td>
<tr><td>boon</td><td>226.109138 ms</td><td>235.744988 ms</td>
<tr><td>cheshire</td><td>233.513268 ms</td><td>242.169108 ms</td>
<tr><td>data.json</td><td>282.268952 ms</td><td>292.574602 ms</td>
</table>

```clojure
Goal:  JSON Parse Benchmark - parse all fields and substructures
-----
Case:  :pjson
Evaluation count : 300 in 60 samples of 5 calls.
             Execution time mean : 214.128818 ms
    Execution time std-deviation : 2.156902 ms
   Execution time lower quantile : 208.868798 ms ( 2.5%)
   Execution time upper quantile : 218.011483 ms (97.5%)
                   Overhead used : 1.881626 ns

Found 2 outliers in 60 samples (3.3333 %)
        low-severe       2 (3.3333 %)
 Variance from outliers : 1.6389 % Variance is slightly inflated by outliers

Case:  :data.json
Evaluation count : 240 in 60 samples of 4 calls.
             Execution time mean : 292.574602 ms
    Execution time std-deviation : 5.004733 ms
   Execution time lower quantile : 284.763467 ms ( 2.5%)
   Execution time upper quantile : 303.999405 ms (97.5%)
                   Overhead used : 1.881626 ns

Found 5 outliers in 60 samples (8.3333 %)
        low-severe       5 (8.3333 %)
 Variance from outliers : 6.2711 % Variance is slightly inflated by outliers

Case:  :clj-json
Evaluation count : 300 in 60 samples of 5 calls.
             Execution time mean : 244.911982 ms
    Execution time std-deviation : 4.472648 ms
   Execution time lower quantile : 239.367998 ms ( 2.5%)
   Execution time upper quantile : 257.302398 ms (97.5%)
                   Overhead used : 1.881626 ns

Found 7 outliers in 60 samples (11.6667 %)
        low-severe       4 (6.6667 %)
        low-mild         3 (5.0000 %)
 Variance from outliers : 7.7911 % Variance is slightly inflated by outliers

Case:  :cheshire
Evaluation count : 300 in 60 samples of 5 calls.
             Execution time mean : 242.169108 ms
    Execution time std-deviation : 4.188996 ms
   Execution time lower quantile : 236.415598 ms ( 2.5%)
   Execution time upper quantile : 251.543673 ms (97.5%)
                   Overhead used : 1.881626 ns

Found 2 outliers in 60 samples (3.3333 %)
        low-severe       2 (3.3333 %)
 Variance from outliers : 6.2799 % Variance is slightly inflated by outliers

Case:  :boon
Evaluation count : 300 in 60 samples of 5 calls.
             Execution time mean : 235.744988 ms
    Execution time std-deviation : 3.732181 ms
   Execution time lower quantile : 231.050798 ms ( 2.5%)
   Execution time upper quantile : 242.266528 ms (97.5%)
                   Overhead used : 1.881626 ns

Found 2 outliers in 60 samples (3.3333 %)
        low-severe       1 (1.6667 %)
        low-mild         1 (1.6667 %)
 Variance from outliers : 1.6389 % Variance is slightly inflated by outliers
```

#JSON Encode Map

This benchmarks aims to test encoding speed from standard clojure/java data structures.  
The test message is parsed with clj-json which does this without any lazy intermediates.  

<table border="0">
<tr><th>Library</th><th>Mean in ms (lower is better) 0.2.7</th><th>0.2.8</th></tr>
<tr><td>pjson</td><td>9.881341 ms</td><td>10.291857 ms</td>
<tr><td>clj-json</td><td>9.240113 ms</td><td>8.785915 ms</td>
<tr><td>boon</td><td>60.509740 ms</td><td>65.338146 ms</td>
<tr><td>cheshire</td><td>19.348805 ms</td><td>20.054495 ms</td>
<tr><td>data.json</td><td>76.980829 ms</td><td>79.525906 ms</td>
</table>

```clojure
Goal:  JSON Encode Map Benchmark
-----
Case:  :clj-json
Evaluation count : 7020 in 60 samples of 117 calls.
             Execution time mean : 8.785915 ms
    Execution time std-deviation : 160.040731 µs
   Execution time lower quantile : 8.554904 ms ( 2.5%)
   Execution time upper quantile : 9.193672 ms (97.5%)
                   Overhead used : 1.881626 ns

Found 5 outliers in 60 samples (8.3333 %)
        low-severe       5 (8.3333 %)
 Variance from outliers : 7.7883 % Variance is slightly inflated by outliers

Case:  :pjson
Evaluation count : 5820 in 60 samples of 97 calls.
             Execution time mean : 10.291857 ms
    Execution time std-deviation : 185.576377 µs
   Execution time lower quantile : 10.081706 ms ( 2.5%)
   Execution time upper quantile : 10.716864 ms (97.5%)
                   Overhead used : 1.881626 ns

Found 5 outliers in 60 samples (8.3333 %)
        low-severe       3 (5.0000 %)
        low-mild         2 (3.3333 %)
 Variance from outliers : 7.7771 % Variance is slightly inflated by outliers

Case:  :data.json
Evaluation count : 780 in 60 samples of 13 calls.
             Execution time mean : 79.525906 ms
    Execution time std-deviation : 1.618396 ms
   Execution time lower quantile : 77.785921 ms ( 2.5%)
   Execution time upper quantile : 83.439398 ms (97.5%)
                   Overhead used : 1.881626 ns

Found 5 outliers in 60 samples (8.3333 %)
        low-severe       3 (5.0000 %)
        low-mild         2 (3.3333 %)
 Variance from outliers : 9.3712 % Variance is slightly inflated by outliers

Case:  :boon
Evaluation count : 960 in 60 samples of 16 calls.
             Execution time mean : 65.338146 ms
    Execution time std-deviation : 1.339375 ms
   Execution time lower quantile : 63.200873 ms ( 2.5%)
   Execution time upper quantile : 68.428662 ms (97.5%)
                   Overhead used : 1.881626 ns

Found 6 outliers in 60 samples (10.0000 %)
        low-severe       6 (10.0000 %)
 Variance from outliers : 9.3803 % Variance is slightly inflated by outliers

Case:  :cheshire
Evaluation count : 3000 in 60 samples of 50 calls.
             Execution time mean : 20.054495 ms
    Execution time std-deviation : 385.203492 µs
   Execution time lower quantile : 19.487878 ms ( 2.5%)
   Execution time upper quantile : 21.046898 ms (97.5%)
                   Overhead used : 1.881626 ns

Found 3 outliers in 60 samples (5.0000 %)
        low-severe       2 (3.3333 %)
        low-mild         1 (1.6667 %)
 Variance from outliers : 7.8432 % Variance is slightly inflated by outliers
```

#JSON Encode Simple

This benchmarks reads the message using each API's decode/parse functions and then without edits  
encodes the message back to json.  

Note: pjson can remember the original json message for sub messages, such that if no edits were made the messages' 
original json string is written back.  

Summary of the benchmark results are below (in order of faster to slowest).  

<table border="0">
<tr><th>Library</th><th>Mean in ms (lower is better) 0.2.7</th><th>0.2.8</th></tr>
<tr><td>pjson</td><td>2.380149 ms</td><td>2.394470 ms</td>
<tr><td>clj-json</td><td>9.208672 ms</td><td>9.950834 ms</td>
<tr><td>boon</td><td>10.196466 ms</td><td>11.121667 ms</td>
<tr><td>cheshire</td><td>19.375317 ms</td><td>20.302390 ms</td>
<tr><td>data.json</td><td>74.689144 ms</td><td>82.368058 ms</td>
</table>


```clojure
Goal:  JSON Encode Simple
-----
Case:  :data.json
Evaluation count : 780 in 60 samples of 13 calls.
             Execution time mean : 82.368058 ms
    Execution time std-deviation : 2.061501 ms
   Execution time lower quantile : 76.922229 ms ( 2.5%)
   Execution time upper quantile : 85.884158 ms (97.5%)
                   Overhead used : 1.881626 ns

Found 2 outliers in 60 samples (3.3333 %)
        low-severe       2 (3.3333 %)
 Variance from outliers : 12.5942 % Variance is moderately inflated by outliers

Case:  :boon
Evaluation count : 5460 in 60 samples of 91 calls.
             Execution time mean : 11.121667 ms
    Execution time std-deviation : 205.412815 µs
   Execution time lower quantile : 10.672020 ms ( 2.5%)
   Execution time upper quantile : 11.444006 ms (97.5%)
                   Overhead used : 1.881626 ns

Found 1 outliers in 60 samples (1.6667 %)
        low-severe       1 (1.6667 %)
 Variance from outliers : 7.8032 % Variance is slightly inflated by outliers

Case:  :clj-json
Evaluation count : 6060 in 60 samples of 101 calls.
             Execution time mean : 9.950834 ms
    Execution time std-deviation : 169.705745 µs
   Execution time lower quantile : 9.542503 ms ( 2.5%)
   Execution time upper quantile : 10.272098 ms (97.5%)
                   Overhead used : 1.881626 ns

Found 2 outliers in 60 samples (3.3333 %)
        low-severe       2 (3.3333 %)
 Variance from outliers : 6.2687 % Variance is slightly inflated by outliers

Case:  :cheshire
Evaluation count : 3000 in 60 samples of 50 calls.
             Execution time mean : 20.302390 ms
    Execution time std-deviation : 359.782122 µs
   Execution time lower quantile : 19.596678 ms ( 2.5%)
   Execution time upper quantile : 21.128399 ms (97.5%)
                   Overhead used : 1.881626 ns

Found 7 outliers in 60 samples (11.6667 %)
        low-severe       4 (6.6667 %)
        low-mild         3 (5.0000 %)
 Variance from outliers : 6.2981 % Variance is slightly inflated by outliers

Case:  :pjson
Evaluation count : 25200 in 60 samples of 420 calls.
             Execution time mean : 2.394470 ms
    Execution time std-deviation : 35.138850 µs
   Execution time lower quantile : 2.335346 ms ( 2.5%)
   Execution time upper quantile : 2.464308 ms (97.5%)
                   Overhead used : 1.881626 ns
```


#Number parse Benchmark

This benchmark is used to show the difference between the manual string to integer reading in pjson
and the java Long.valueOf

<table border="0">
<tr><th>Library</th><th>Mean in ms (lower is better) 0.2.8</th></tr>
<tr><td>pjson number-util-parse5</td><td>12.869024 ns</td>
<tr><td>java Long.valueOf</td><td>38.067585 ns</td>
</table>