
#JSON Encode Map

This benchmarks aims to test encoding speed from standard clojure/java data structures.  
The test message is parsed with clj-json which does this without any lazy intermediates.  

Goal:  JSON Encode Map Benchmark
-----
Case:  :data.json
Evaluation count : 60 in 60 samples of 1 calls.
             Execution time mean : 7.965589 sec
    Execution time std-deviation : 24.707182 ms
   Execution time lower quantile : 7.921671 sec ( 2.5%)
   Execution time upper quantile : 8.007025 sec (97.5%)
                   Overhead used : 1.812383 ns

Case:  :pjson
Evaluation count : 120 in 60 samples of 2 calls.
             Execution time mean : 686.595274 ms
    Execution time std-deviation : 5.334484 ms
   Execution time lower quantile : 677.943999 ms ( 2.5%)
   Execution time upper quantile : 694.937974 ms (97.5%)
                   Overhead used : 1.812383 ns

Found 1 outliers in 60 samples (1.6667 %)
	low-severe	 1 (1.6667 %)
 Variance from outliers : 1.6389 % Variance is slightly inflated by outliers

Case:  :cheshire
Evaluation count : 60 in 60 samples of 1 calls.
             Execution time mean : 1.815776 sec
    Execution time std-deviation : 10.120367 ms
   Execution time lower quantile : 1.796211 sec ( 2.5%)
   Execution time upper quantile : 1.834315 sec (97.5%)
                   Overhead used : 1.812383 ns

Case:  :clj-json
Evaluation count : 120 in 60 samples of 2 calls.
             Execution time mean : 931.659699 ms
    Execution time std-deviation : 4.580340 ms
   Execution time lower quantile : 922.325999 ms ( 2.5%)
   Execution time upper quantile : 939.897911 ms (97.5%)
                   Overhead used : 1.812383 ns

Case:  :boon
Evaluation count : 60 in 60 samples of 1 calls.
             Execution time mean : 6.229864 sec
    Execution time std-deviation : 24.790728 ms
   Execution time lower quantile : 6.175403 sec ( 2.5%)
   Execution time upper quantile : 6.267085 sec (97.5%)
                   Overhead used : 1.812383 ns

#JSON Encode Simple

This benchmarks reads the message using each API's decode/parse functions and then without edits  
encodes the message back to json.  

Note: pjson can remember the original json message for sub messages, such that if no edits were made the messages' 
original json string is written back.  

Summary of the benchmark results are below (in order of faster to slowest).  

<table border="0">
<tr><th>Library</th><th>Mean in ms (lower is better)</th></tr>
<tr><td>pjson</td><td>255.363232</td>
<tr><td>clj-json</td><td>927.270765</td>
<tr><td>boon</td><td>1056.440</td>
<tr><td>cheshire</td><td>1846.539</td>
<tr><td>data.json</td><td>7987.892</td>
</table>


```
Goal:  JSON Encode Simple
-----
Case:  :cheshire
Evaluation count : 60 in 60 samples of 1 calls.
             Execution time mean : 1.846539 sec
    Execution time std-deviation : 11.068562 ms
   Execution time lower quantile : 1.827917 sec ( 2.5%)
   Execution time upper quantile : 1.864664 sec (97.5%)
                   Overhead used : 1.783760 ns

Found 1 outliers in 60 samples (1.6667 %)
	low-severe	 1 (1.6667 %)
 Variance from outliers : 1.6389 % Variance is slightly inflated by outliers

Case:  :pjson
Evaluation count : 240 in 60 samples of 4 calls.
             Execution time mean : 255.363232 ms
    Execution time std-deviation : 1.387454 ms
   Execution time lower quantile : 253.048342 ms ( 2.5%)
   Execution time upper quantile : 257.529998 ms (97.5%)
                   Overhead used : 1.783760 ns

Case:  :data.json
Evaluation count : 60 in 60 samples of 1 calls.
             Execution time mean : 7.987892 sec
    Execution time std-deviation : 182.620122 ms
   Execution time lower quantile : 7.721798 sec ( 2.5%)
   Execution time upper quantile : 8.304643 sec (97.5%)
                   Overhead used : 1.783760 ns

Case:  :boon
Evaluation count : 60 in 60 samples of 1 calls.
             Execution time mean : 1.056440 sec
    Execution time std-deviation : 16.720413 ms
   Execution time lower quantile : 1.032285 sec ( 2.5%)
   Execution time upper quantile : 1.086853 sec (97.5%)
                   Overhead used : 1.783760 ns

Case:  :clj-json
Evaluation count : 120 in 60 samples of 2 calls.
             Execution time mean : 927.270765 ms
    Execution time std-deviation : 6.247747 ms
   Execution time lower quantile : 915.885499 ms ( 2.5%)
   Execution time upper quantile : 940.229599 ms (97.5%)
                   Overhead used : 1.783760 ns
```


