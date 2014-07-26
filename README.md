# pjson

Fast clojure json parser

## Usage

```clojure

(require '[pjson.core :refer [bts->json]])
(require '[pjson.data :refer [msg-bts]])

(bts->json msg-bts)

```

## Bencmarks

Using criterium:

```
Evaluation count : 13056660 in 60 samples of 217611 calls.
             Execution time mean : 4.542464 µs
    Execution time std-deviation : 166.410236 ns
   Execution time lower quantile : 4.347147 µs ( 2.5%)
   Execution time upper quantile : 4.799114 µs (97.5%)
                   Overhead used : 1.792457 ns
```
## License

Copyright © 2014 gerritjvv@gmail.com

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
