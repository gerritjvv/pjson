#JSON Parse Practical Example

<table border="0">
<tr><th>Library</th><th>Mean in ms (lower is better)</th></tr>
<tr><td>pjson</td><td>10.183120 ms</td>
<tr><td>clj-json</td><td>41.713927 ms</td>
<tr><td>boon</td><td>44.195550 ms</td>
<tr><td>cheshire</td><td>49.784366 ms</td>
<tr><td>data.json</td><td>165.176958 ms</td>
</table>


```clojure
Goal:  JSON Parse Practical Example
-----
Case:  :boon
Evaluation count : 1380 in 60 samples of 23 calls.
             Execution time mean : 44.195550 ms
    Execution time std-deviation : 530.594643 µs
   Execution time lower quantile : 43.236172 ms ( 2.5%)
   Execution time upper quantile : 45.166886 ms (97.5%)
                   Overhead used : 1.773684 ns

Found 1 outliers in 60 samples (1.6667 %)
        low-severe       1 (1.6667 %)
 Variance from outliers : 1.6389 % Variance is slightly inflated by outliers

Case:  :clj-json
Evaluation count : 1440 in 60 samples of 24 calls.
             Execution time mean : 41.713927 ms
    Execution time std-deviation : 902.884042 µs
   Execution time lower quantile : 40.742707 ms ( 2.5%)
   Execution time upper quantile : 43.691297 ms (97.5%)
                   Overhead used : 1.773684 ns

Found 4 outliers in 60 samples (6.6667 %)
        low-severe       3 (5.0000 %)
        low-mild         1 (1.6667 %)
 Variance from outliers : 9.4441 % Variance is slightly inflated by outliers

Case:  :data.json
Evaluation count : 420 in 60 samples of 7 calls.
             Execution time mean : 165.176958 ms
    Execution time std-deviation : 4.877089 ms
   Execution time lower quantile : 161.299284 ms ( 2.5%)
   Execution time upper quantile : 179.499291 ms (97.5%)
                   Overhead used : 1.773684 ns

Found 6 outliers in 60 samples (10.0000 %)
        low-severe       4 (6.6667 %)
        low-mild         2 (3.3333 %)
 Variance from outliers : 15.8369 % Variance is moderately inflated by outliers

Case:  :pjson
Evaluation count : 6000 in 60 samples of 100 calls.
             Execution time mean : 10.183120 ms
    Execution time std-deviation : 131.564971 µs
   Execution time lower quantile : 9.989686 ms ( 2.5%)
   Execution time upper quantile : 10.522050 ms (97.5%)
                   Overhead used : 1.773684 ns

Found 3 outliers in 60 samples (5.0000 %)
        low-severe       3 (5.0000 %)
 Variance from outliers : 1.6389 % Variance is slightly inflated by outliers

Case:  :cheshire
Evaluation count : 1260 in 60 samples of 21 calls.
             Execution time mean : 49.784366 ms
    Execution time std-deviation : 715.705218 µs
   Execution time lower quantile : 48.804332 ms ( 2.5%)
   Execution time upper quantile : 51.587957 ms (97.5%)
                   Overhead used : 1.773684 ns

Found 4 outliers in 60 samples (6.6667 %)
        low-severe       4 (6.6667 %)
 Variance from outliers : 1.6389 % Variance is slightly inflated by outliers

```

#JSON Parse Benchmark

<table border="0">
<tr><th>Library</th><th>Mean in ms (lower is better)</th></tr>
<tr><td>pjson</td><td>3.471202 ms</td>
<tr><td>clj-json</td><td>30.000884 ms</td>
<tr><td>boon</td><td>7.775955 ms</td>
<tr><td>cheshire</td><td>26.599338 ms</td>
<tr><td>data.json</td><td>74.359619 ms</td>
</table>

```clojure
Goal:  JSON Parse Benchmark
-----
Case:  :pjson
Evaluation count : 17640 in 60 samples of 294 calls.
             Execution time mean : 3.471202 ms
    Execution time std-deviation : 76.268306 µs
   Execution time lower quantile : 3.386039 ms ( 2.5%)
   Execution time upper quantile : 3.699820 ms (97.5%)
                   Overhead used : 1.773684 ns

Found 6 outliers in 60 samples (10.0000 %)
        low-severe       1 (1.6667 %)
        low-mild         5 (8.3333 %)
 Variance from outliers : 9.4606 % Variance is slightly inflated by outliers

Case:  :data.json
Evaluation count : 840 in 60 samples of 14 calls.
             Execution time mean : 74.359619 ms
    Execution time std-deviation : 1.500476 ms
   Execution time lower quantile : 72.550784 ms ( 2.5%)
   Execution time upper quantile : 78.341991 ms (97.5%)
                   Overhead used : 1.773684 ns

Found 4 outliers in 60 samples (6.6667 %)
        low-severe       4 (6.6667 %)
 Variance from outliers : 9.3604 % Variance is slightly inflated by outliers

Case:  :boon
Evaluation count : 8100 in 60 samples of 135 calls.
             Execution time mean : 7.775955 ms
    Execution time std-deviation : 342.345802 µs
   Execution time lower quantile : 7.465324 ms ( 2.5%)
   Execution time upper quantile : 8.620687 ms (97.5%)
                   Overhead used : 1.773684 ns

Found 5 outliers in 60 samples (8.3333 %)
        low-severe       5 (8.3333 %)
 Variance from outliers : 30.3194 % Variance is moderately inflated by outliers

Case:  :clj-json
Evaluation count : 2100 in 60 samples of 35 calls.
             Execution time mean : 30.000884 ms
    Execution time std-deviation : 1.324591 ms
   Execution time lower quantile : 28.835713 ms ( 2.5%)
   Execution time upper quantile : 33.053655 ms (97.5%)
                   Overhead used : 1.773684 ns

Found 1 outliers in 60 samples (1.6667 %)
        low-severe       1 (1.6667 %)
 Variance from outliers : 30.3271 % Variance is moderately inflated by outliers

Case:  :cheshire
Evaluation count : 2340 in 60 samples of 39 calls.
             Execution time mean : 26.599338 ms
    Execution time std-deviation : 273.218955 µs
   Execution time lower quantile : 26.257892 ms ( 2.5%)
   Execution time upper quantile : 27.330460 ms (97.5%)
                   Overhead used : 1.773684 ns

Found 2 outliers in 60 samples (3.3333 %)
        low-severe       2 (3.3333 %)
 Variance from outliers : 1.6389 % Variance is slightly inflated by outliers

```

#JSON Parse Benchmark - parse all fields and substructures

<table border="0">
<tr><th>Library</th><th>Mean in ms (lower is better)</th></tr>
<tr><td>pjson</td><td>210.109698 ms</td>
<tr><td>clj-json</td><td>234.800428 ms</td>
<tr><td>boon</td><td>226.109138 ms</td>
<tr><td>cheshire</td><td>233.513268 ms</td>
<tr><td>data.json</td><td>282.268952 ms</td>
</table>

```clojure
Goal:  JSON Parse Benchmark - parse all fields and substructures
-----
Case:  :clj-json
Evaluation count : 300 in 60 samples of 5 calls.
             Execution time mean : 234.800428 ms
    Execution time std-deviation : 2.747607 ms
   Execution time lower quantile : 231.480398 ms ( 2.5%)
   Execution time upper quantile : 242.931338 ms (97.5%)
                   Overhead used : 1.773684 ns

Found 4 outliers in 60 samples (6.6667 %)
        low-severe       2 (3.3333 %)
        low-mild         2 (3.3333 %)
 Variance from outliers : 1.6389 % Variance is slightly inflated by outliers

Case:  :pjson
Evaluation count : 300 in 60 samples of 5 calls.
             Execution time mean : 210.109698 ms
    Execution time std-deviation : 4.325224 ms
   Execution time lower quantile : 205.032198 ms ( 2.5%)
   Execution time upper quantile : 220.589058 ms (97.5%)
                   Overhead used : 1.773684 ns

Found 6 outliers in 60 samples (10.0000 %)
        low-severe       1 (1.6667 %)
        low-mild         5 (8.3333 %)
 Variance from outliers : 9.3855 % Variance is slightly inflated by outliers

Case:  :boon
Evaluation count : 300 in 60 samples of 5 calls.
             Execution time mean : 226.109138 ms
    Execution time std-deviation : 2.983553 ms
   Execution time lower quantile : 222.924798 ms ( 2.5%)
   Execution time upper quantile : 235.655008 ms (97.5%)
                   Overhead used : 1.773684 ns

Found 4 outliers in 60 samples (6.6667 %)
        low-severe       1 (1.6667 %)
        low-mild         3 (5.0000 %)
 Variance from outliers : 1.6389 % Variance is slightly inflated by outliers

Case:  :data.json
Evaluation count : 240 in 60 samples of 4 calls.
             Execution time mean : 282.268952 ms
    Execution time std-deviation : 5.193844 ms
   Execution time lower quantile : 277.290498 ms ( 2.5%)
   Execution time upper quantile : 295.739748 ms (97.5%)
                   Overhead used : 1.773684 ns

Found 8 outliers in 60 samples (13.3333 %)
        low-severe       4 (6.6667 %)
        low-mild         4 (6.6667 %)
 Variance from outliers : 7.7992 % Variance is slightly inflated by outliers

Case:  :cheshire
Evaluation count : 300 in 60 samples of 5 calls.
             Execution time mean : 233.513268 ms
    Execution time std-deviation : 3.247841 ms
   Execution time lower quantile : 229.660398 ms ( 2.5%)
   Execution time upper quantile : 241.141158 ms (97.5%)
                   Overhead used : 1.773684 ns

Found 4 outliers in 60 samples (6.6667 %)
        low-severe       3 (5.0000 %)
        low-mild         1 (1.6667 %)
 Variance from outliers : 1.6389 % Variance is slightly inflated by outliers
```

#JSON Encode Map

This benchmarks aims to test encoding speed from standard clojure/java data structures.  
The test message is parsed with clj-json which does this without any lazy intermediates.  

<table border="0">
<tr><th>Library</th><th>Mean in ms (lower is better)</th></tr>
<tr><td>pjson</td><td>9.881341 ms</td>
<tr><td>clj-json</td><td>9.240113 ms</td>
<tr><td>boon</td><td>60.509740 ms</td>
<tr><td>cheshire</td><td>19.348805 ms</td>
<tr><td>data.json</td><td>76.980829 ms</td>
</table>

```clojure
Case:  :pjson
Evaluation count : 6120 in 60 samples of 102 calls.
             Execution time mean : 9.881341 ms
    Execution time std-deviation : 114.447983 µs
   Execution time lower quantile : 9.744243 ms ( 2.5%)
   Execution time upper quantile : 10.150449 ms (97.5%)
                   Overhead used : 1.773684 ns

Case:  :clj-json
Evaluation count : 6600 in 60 samples of 110 calls.
             Execution time mean : 9.240113 ms
    Execution time std-deviation : 167.165730 µs
   Execution time lower quantile : 9.069826 ms ( 2.5%)
   Execution time upper quantile : 9.572871 ms (97.5%)
                   Overhead used : 1.773684 ns

Case:  :data.json
Evaluation count : 840 in 60 samples of 14 calls.
             Execution time mean : 76.980829 ms
    Execution time std-deviation : 1.411025 ms
   Execution time lower quantile : 75.354927 ms ( 2.5%)
   Execution time upper quantile : 80.638355 ms (97.5%)
                   Overhead used : 1.773684 ns

Case:  :boon
Evaluation count : 1020 in 60 samples of 17 calls.
             Execution time mean : 60.509740 ms
    Execution time std-deviation : 1.389779 ms
   Execution time lower quantile : 58.955116 ms ( 2.5%)
   Execution time upper quantile : 63.797057 ms (97.5%)
                   Overhead used : 1.773684 ns

Case:  :cheshire
Evaluation count : 3120 in 60 samples of 52 calls.
             Execution time mean : 19.348805 ms
    Execution time std-deviation : 196.589104 µs
   Execution time lower quantile : 19.157075 ms ( 2.5%)
   Execution time upper quantile : 19.766392 ms (97.5%)
                   Overhead used : 1.773684 ns
```

#JSON Encode Simple

This benchmarks reads the message using each API's decode/parse functions and then without edits  
encodes the message back to json.  

Note: pjson can remember the original json message for sub messages, such that if no edits were made the messages' 
original json string is written back.  

Summary of the benchmark results are below (in order of faster to slowest).  

<table border="0">
<tr><th>Library</th><th>Mean in ms (lower is better)</th></tr>
<tr><td>pjson</td><td>2.380149 ms</td>
<tr><td>clj-json</td><td>9.208672 ms</td>
<tr><td>boon</td><td>10.196466 ms</td>
<tr><td>cheshire</td><td>19.375317 ms</td>
<tr><td>data.json</td><td>74.689144 ms</td>
</table>


```
Goal:  JSON Encode Simple
-----
Case:  :clj-json
Evaluation count : 6600 in 60 samples of 110 calls.
             Execution time mean : 9.208672 ms
    Execution time std-deviation : 99.115803 µs
   Execution time lower quantile : 9.068942 ms ( 2.5%)
   Execution time upper quantile : 9.504303 ms (97.5%)
                   Overhead used : 1.773684 ns

Found 3 outliers in 60 samples (5.0000 %)
        low-severe       2 (3.3333 %)
        low-mild         1 (1.6667 %)
 Variance from outliers : 1.6389 % Variance is slightly inflated by outliers

Case:  :pjson
Evaluation count : 25320 in 60 samples of 422 calls.
             Execution time mean : 2.380149 ms
    Execution time std-deviation : 22.608318 µs
   Execution time lower quantile : 2.353344 ms ( 2.5%)
   Execution time upper quantile : 2.440221 ms (97.5%)
                   Overhead used : 1.773684 ns

Found 7 outliers in 60 samples (11.6667 %)
        low-severe       4 (6.6667 %)
        low-mild         3 (5.0000 %)
 Variance from outliers : 1.6389 % Variance is slightly inflated by outliers

Case:  :boon
Evaluation count : 6000 in 60 samples of 100 calls.
             Execution time mean : 10.196466 ms
    Execution time std-deviation : 190.958662 µs
   Execution time lower quantile : 10.026968 ms ( 2.5%)
   Execution time upper quantile : 10.700344 ms (97.5%)
                   Overhead used : 1.773684 ns

Found 7 outliers in 60 samples (11.6667 %)
        low-severe       4 (6.6667 %)
        low-mild         3 (5.0000 %)
 Variance from outliers : 7.8177 % Variance is slightly inflated by outliers

Case:  :data.json
Evaluation count : 840 in 60 samples of 14 calls.
             Execution time mean : 74.689144 ms
    Execution time std-deviation : 802.038350 µs
   Execution time lower quantile : 73.589498 ms ( 2.5%)
   Execution time upper quantile : 76.457998 ms (97.5%)
                   Overhead used : 1.773684 ns

Found 3 outliers in 60 samples (5.0000 %)
        low-severe       2 (3.3333 %)
        low-mild         1 (1.6667 %)
 Variance from outliers : 1.6389 % Variance is slightly inflated by outliers

Case:  :cheshire
Evaluation count : 3120 in 60 samples of 52 calls.
             Execution time mean : 19.375317 ms
    Execution time std-deviation : 260.338077 µs
   Execution time lower quantile : 19.132710 ms ( 2.5%)
   Execution time upper quantile : 20.151787 ms (97.5%)
                   Overhead used : 1.773684 ns

Found 5 outliers in 60 samples (8.3333 %)
        low-severe       2 (3.3333 %)
        low-mild         3 (5.0000 %)
 Variance from outliers : 1.6389 % Variance is slightly inflated by outliers

```


