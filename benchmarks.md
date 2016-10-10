#JSON Parse Practical Example

<table border="0">
<tr><th>Library</th><th>Mean in ms (lower is better) 0.2.7</th><th>0.2.8</th><th>0.3.6</th></tr>
<tr><td>pjson</td><td>10.183120 ms</td><td>9.526570 ms</td><td>9.382459 ms</td>
<tr><td>clj-json</td><td>41.713927 ms</td><td>42.148021 ms</td><td></td>
<tr><td>boon</td><td>44.195550 ms</td><td>45.949513 ms</td><td>40.701913 ms</td>
<tr><td>cheshire</td><td>49.784366 ms</td><td>50.600446 ms</td><td></td>
<tr><td>data.json</td><td>165.176958 ms</td><td>171.720518 ms</td><td></td>
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
Evaluation count : 6360 in 60 samples of 106 calls.
             Execution time mean : 9.382459 ms
    Execution time std-deviation : 96.696634 µs
   Execution time lower quantile : 9.258054 ms ( 2.5%)
   Execution time upper quantile : 9.694433 ms (97.5%)
                   Overhead used : 1.553512 ns

Found 4 outliers in 60 samples (6.6667 %)
        low-severe       1 (1.6667 %)
        low-mild         3 (5.0000 %)
 Variance from outliers : 1.6389 % Variance is slightly inflated by outliers

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
Evaluation count : 1500 in 60 samples of 25 calls.
             Execution time mean : 40.701913 ms
    Execution time std-deviation : 587.048788 µs
   Execution time lower quantile : 39.874727 ms ( 2.5%)
   Execution time upper quantile : 42.248703 ms (97.5%)
                   Overhead used : 1.553512 ns

Found 3 outliers in 60 samples (5.0000 %)
        low-severe       3 (5.0000 %)
 Variance from outliers : 1.6389 % Variance is slightly inflated by outliers

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
<tr><th>Library</th><th>Mean in ms (lower is better) 0.2.7</th><th>0.2.8</th><th>0.3.6</th></tr>
<tr><td>pjson</td><td>3.471202 ms</td><td>3.397253 ms</td><td>3.239881 ms</td>
<tr><td>clj-json</td><td>30.000884 ms</td><td>30.696739 ms</td><td></td>
<tr><td>boon</td><td>7.775955 ms</td><td>8.178839 ms</td><td>6.669186 ms</td>
<tr><td>cheshire</td><td>26.599338 ms</td><td>27.984381 ms</td><td></td>
<tr><td>data.json</td><td>74.359619 ms</td><td>76.775012 ms</td><td></td>
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
Evaluation count : 9000 in 60 samples of 150 calls.
             Execution time mean : 6.669186 ms
    Execution time std-deviation : 146.054908 µs
   Execution time lower quantile : 6.563548 ms ( 2.5%)
   Execution time upper quantile : 7.100086 ms (97.5%)
                   Overhead used : 1.553512 ns

Found 5 outliers in 60 samples (8.3333 %)
        low-severe       1 (1.6667 %)
        low-mild         4 (6.6667 %)
 Variance from outliers : 9.4570 % Variance is slightly inflated by outliers

Case:  :pjson
Evaluation count : 18780 in 60 samples of 313 calls.
             Execution time mean : 3.239881 ms
    Execution time std-deviation : 35.395760 µs
   Execution time lower quantile : 3.206980 ms ( 2.5%)
   Execution time upper quantile : 3.329477 ms (97.5%)
                   Overhead used : 1.553512 ns

Found 5 outliers in 60 samples (8.3333 %)
        low-severe       3 (5.0000 %)
        low-mild         2 (3.3333 %)
 Variance from outliers : 1.6389 % Variance is slightly inflated by outliers

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
<tr><th>Library</th><th>Mean in ms (lower is better) 0.2.7</th><th>0.2.8</th><th>0.3.6</th></tr>
<tr><td>pjson</td><td>210.109698 ms</td><td>214.128818 ms</td><td>154.815778 ms</td>
<tr><td>clj-json</td><td>234.800428 ms</td><td>244.911982 ms</td><td>185.095822 ms</td>
<tr><td>boon</td><td>226.109138 ms</td><td>235.744988 ms</td><td>167.414613 ms</td>
<tr><td>cheshire</td><td>233.513268 ms</td><td>242.169108 ms</td><td>180.337285 ms</td>
<tr><td>data.json</td><td>282.268952 ms</td><td>292.574602 ms</td><td>221.465102 ms</td>
</table>

```clojure
Goal:  JSON Parse Benchmark - parse all fields and substructures
-----
Case:  :data.json
Evaluation count : 300 in 60 samples of 5 calls.
             Execution time mean : 221.465102 ms
    Execution time std-deviation : 2.685839 ms
   Execution time lower quantile : 217.661820 ms ( 2.5%)
   Execution time upper quantile : 227.296331 ms (97.5%)
                   Overhead used : 1.553512 ns

Found 4 outliers in 60 samples (6.6667 %)
        low-severe       3 (5.0000 %)
        low-mild         1 (1.6667 %)
 Variance from outliers : 1.6389 % Variance is slightly inflated by outliers

Case:  :boon
Evaluation count : 420 in 60 samples of 7 calls.
             Execution time mean : 167.414613 ms
    Execution time std-deviation : 1.828060 ms
   Execution time lower quantile : 165.567706 ms ( 2.5%)
   Execution time upper quantile : 172.131859 ms (97.5%)
                   Overhead used : 1.553512 ns

Found 4 outliers in 60 samples (6.6667 %)
        low-severe       3 (5.0000 %)
        low-mild         1 (1.6667 %)
 Variance from outliers : 1.6389 % Variance is slightly inflated by outliers

Case:  :cheshire
Evaluation count : 360 in 60 samples of 6 calls.
             Execution time mean : 180.337285 ms
    Execution time std-deviation : 3.151541 ms
   Execution time lower quantile : 176.927905 ms ( 2.5%)
   Execution time upper quantile : 187.636870 ms (97.5%)
                   Overhead used : 1.553512 ns

Found 8 outliers in 60 samples (13.3333 %)
        low-severe       7 (11.6667 %)
        low-mild         1 (1.6667 %)
 Variance from outliers : 6.2877 % Variance is slightly inflated by outliers

Case:  :pjson
Evaluation count : 420 in 60 samples of 7 calls.
             Execution time mean : 154.815778 ms
    Execution time std-deviation : 1.529529 ms
   Execution time lower quantile : 153.082568 ms ( 2.5%)
   Execution time upper quantile : 157.792013 ms (97.5%)
                   Overhead used : 1.553512 ns

Found 3 outliers in 60 samples (5.0000 %)
        low-severe       2 (3.3333 %)
        low-mild         1 (1.6667 %)
 Variance from outliers : 1.6389 % Variance is slightly inflated by outliers

Case:  :clj-json
Evaluation count : 360 in 60 samples of 6 calls.
             Execution time mean : 185.095822 ms
    Execution time std-deviation : 2.106442 ms
   Execution time lower quantile : 182.398961 ms ( 2.5%)
   Execution time upper quantile : 190.273053 ms (97.5%)
                   Overhead used : 1.553512 ns

Found 2 outliers in 60 samples (3.3333 %)
        low-severe       2 (3.3333 %)
 Variance from outliers : 1.6389 % Variance is slightly inflated by outliers
```

#JSON Encode Map

This benchmarks aims to test encoding speed from standard clojure/java data structures.  
The test message is parsed with clj-json which does this without any lazy intermediates.  

<table border="0">
<tr><th>Library</th><th>Mean in ms (lower is better) 0.2.7</th><th>0.2.8</th><th>0.3.6</th></tr>
<tr><td>pjson</td><td>9.881341 ms</td><td>10.291857 ms</td><td>9.577133 ms</td>
<tr><td>clj-json</td><td>9.240113 ms</td><td>8.785915 ms</td><td>7.440156 ms</td>
<tr><td>boon</td><td>60.509740 ms</td><td>65.338146 ms</td><td>56.358606 ms</td>
<tr><td>cheshire</td><td>19.348805 ms</td><td>20.054495 ms</td><td>21.417734 ms</td>
<tr><td>data.json</td><td>76.980829 ms</td><td>79.525906 ms</td><td>73.388868 ms</td>
</table>

```clojure
Goal:  JSON Encode Map Benchmark
-----
Case:  :boon
Evaluation count : 1080 in 60 samples of 18 calls.
             Execution time mean : 56.358606 ms
    Execution time std-deviation : 1.507475 ms
   Execution time lower quantile : 55.202649 ms ( 2.5%)
   Execution time upper quantile : 59.223079 ms (97.5%)
                   Overhead used : 1.553512 ns

Found 4 outliers in 60 samples (6.6667 %)
        low-severe       1 (1.6667 %)
        low-mild         3 (5.0000 %)
 Variance from outliers : 14.1810 % Variance is moderately inflated by outliers

Case:  :pjson
Evaluation count : 6300 in 60 samples of 105 calls.
             Execution time mean : 9.577133 ms
    Execution time std-deviation : 65.927621 µs
   Execution time lower quantile : 9.473455 ms ( 2.5%)
   Execution time upper quantile : 9.736950 ms (97.5%)
                   Overhead used : 1.553512 ns

Found 3 outliers in 60 samples (5.0000 %)
        low-severe       1 (1.6667 %)
        low-mild         2 (3.3333 %)
 Variance from outliers : 1.6389 % Variance is slightly inflated by outliers

Case:  :data.json
Evaluation count : 840 in 60 samples of 14 calls.
             Execution time mean : 73.388868 ms
    Execution time std-deviation : 416.120409 µs
   Execution time lower quantile : 72.807563 ms ( 2.5%)
   Execution time upper quantile : 74.423537 ms (97.5%)
                   Overhead used : 1.553512 ns

Found 2 outliers in 60 samples (3.3333 %)
        low-severe       2 (3.3333 %)
 Variance from outliers : 1.6389 % Variance is slightly inflated by outliers

Case:  :cheshire
Evaluation count : 2820 in 60 samples of 47 calls.
             Execution time mean : 21.417734 ms
    Execution time std-deviation : 186.582387 µs
   Execution time lower quantile : 21.146894 ms ( 2.5%)
   Execution time upper quantile : 21.862683 ms (97.5%)
                   Overhead used : 1.553512 ns

Found 3 outliers in 60 samples (5.0000 %)
        low-severe       3 (5.0000 %)
 Variance from outliers : 1.6389 % Variance is slightly inflated by outliers

Case:  :clj-json
Evaluation count : 8160 in 60 samples of 136 calls.
             Execution time mean : 7.440156 ms
    Execution time std-deviation : 44.436823 µs
   Execution time lower quantile : 7.360257 ms ( 2.5%)
   Execution time upper quantile : 7.527837 ms (97.5%)
                   Overhead used : 1.553512 ns

Found 2 outliers in 60 samples (3.3333 %)
        low-severe       2 (3.3333 %)
 Variance from outliers : 1.6389 % Variance is slightly inflated by outliers
```

#JSON Encode Simple

This benchmarks reads the message using each API's decode/parse functions and then without edits  
encodes the message back to json.  

Note: pjson can remember the original json message for sub messages, such that if no edits were made the messages' 
original json string is written back.  

Summary of the benchmark results are below (in order of faster to slowest).  

<table border="0">
<tr><th>Library</th><th>Mean in ms (lower is better) 0.2.7</th><th>0.2.8</th><th>0.3.6</th></tr>
<tr><td>pjson</td><td>2.380149 ms</td><td>2.394470 ms</td><td>2.431828 ms</td>
<tr><td>clj-json</td><td>9.208672 ms</td><td>9.950834 ms</td><td>8.026480 ms</td>
<tr><td>boon</td><td>10.196466 ms</td><td>11.121667 ms</td><td>9.488874 ms</td>
<tr><td>cheshire</td><td>19.375317 ms</td><td>20.302390 ms</td><td>21.962842 ms</td>
<tr><td>data.json</td><td>74.689144 ms</td><td>82.368058 ms</td><td>78.556321 ms</td>
</table>


```clojure
Goal:  JSON Encode Simple
-----
Case:  :clj-json
Evaluation count : 7800 in 60 samples of 130 calls.
             Execution time mean : 8.026480 ms
    Execution time std-deviation : 305.671832 µs
   Execution time lower quantile : 7.589653 ms ( 2.5%)
   Execution time upper quantile : 8.600770 ms (97.5%)
                   Overhead used : 1.553512 ns

Case:  :pjson
Evaluation count : 25080 in 60 samples of 418 calls.
             Execution time mean : 2.431828 ms
    Execution time std-deviation : 42.347751 µs
   Execution time lower quantile : 2.352209 ms ( 2.5%)
   Execution time upper quantile : 2.506976 ms (97.5%)
                   Overhead used : 1.553512 ns

Case:  :data.json
Evaluation count : 840 in 60 samples of 14 calls.
             Execution time mean : 78.556321 ms
    Execution time std-deviation : 2.979267 ms
   Execution time lower quantile : 73.135290 ms ( 2.5%)
   Execution time upper quantile : 83.312295 ms (97.5%)
                   Overhead used : 1.553512 ns

Case:  :cheshire
Evaluation count : 2880 in 60 samples of 48 calls.
             Execution time mean : 21.962842 ms
    Execution time std-deviation : 756.145386 µs
   Execution time lower quantile : 21.024825 ms ( 2.5%)
   Execution time upper quantile : 23.578767 ms (97.5%)
                   Overhead used : 1.553512 ns

Case:  :boon
Evaluation count : 6360 in 60 samples of 106 calls.
             Execution time mean : 9.488874 ms
    Execution time std-deviation : 419.549008 µs
   Execution time lower quantile : 8.984589 ms ( 2.5%)
   Execution time upper quantile : 10.278705 ms (97.5%)
                   Overhead used : 1.553512 ns
```


#Number parse Benchmark

This benchmark is used to show the difference between the manual string to integer reading in pjson
and the java Long.valueOf

<table border="0">
<tr><th>Library</th><th>Mean in ms (lower is better) 0.2.8</th></tr>
<tr><td>pjson number-util-parse5</td><td>12.869024 ns</td>
<tr><td>java Long.valueOf</td><td>38.067585 ns</td>
</table>