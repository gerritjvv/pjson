# pjson

Fast clojure json parser

The parser's focus is on speed and not validating json.

[![Clojars Project](http://clojars.org/pjson/latest-version.svg)](http://clojars.org/pjson)

## Usage

```clojure

(require '[pjson.core :refer [bts->json]])
(require '[pjson.data :refer [msg-bts]])

(bts->json msg-bts)

```

## Bencmarks

Using criterium:

```
Each run calls the parse function 100 000 times.

Evaluation count : 240 in 60 samples of 4 calls.
             Execution time mean : 321.539024 ms
    Execution time std-deviation : 2.396782 ms
   Execution time lower quantile : 317.474249 ms ( 2.5%)
   Execution time upper quantile : 326.185749 ms (97.5%)
                   Overhead used : 1.482882 ns

Found 3 outliers in 60 samples (5.0000 %)
	low-severe	 2 (3.3333 %)
	low-mild	 1 (1.6667 %)
 Variance from outliers : 1.6389 % Variance is slightly inflated by outliers

Single execution: 0,00321 ms (321/100000)

```
## License

Copyright © 2014 gerritjvv@gmail.com

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
