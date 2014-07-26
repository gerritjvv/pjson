# pjson

Fast clojure json parser

Note: this is still a work in progress.

The parser's focus is on speed and not validating json.

## Usage

```clojure

(require '[pjson.core :refer [bts->json]])
(require '[pjson.data :refer [msg-bts]])

(bts->json msg-bts)

```

## Bencmarks

Using criterium:

```
Evaluation count : 19772100 in 60 samples of 329535 calls.
             Execution time mean : 3.057527 µs
    Execution time std-deviation : 31.518001 ns
   Execution time lower quantile : 3.030034 µs ( 2.5%)
   Execution time upper quantile : 3.163325 µs (97.5%)
                   Overhead used : 1.553263 ns

Found 3 outliers in 60 samples (5.0000 %)
	low-severe	 3 (5.0000 %)
 Variance from outliers : 1.6389 % Variance is slightly inflated by outliers
```
## License

Copyright © 2014 gerritjvv@gmail.com

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
