(ns pjson.core
  (:require [pjson.data :refer [msg-bts]]
            [criterium.core :as crit])
  (:import [pjson PJSON])
  (:gen-class))

(defn bts->json
  ([^"[B" bts]
   (bts->json bts true))
  ([^"[B" bts ^Boolean parseNumbers]
   (PJSON/defaultParse msg-bts parseNumbers)))

(defn do-parse []
  (PJSON/defaultParse msg-bts))


(defn do-parse2 []
  (dotimes [i 100000]
    (PJSON/defaultParse msg-bts true)))


(defn bench []
  (crit/with-progress-reporting  (crit/bench (do-parse))))

(defn bench2 []
  (crit/with-progress-reporting  (crit/bench (do-parse2))))

(defn -main [& args]
  (let [n (get args 0)]
    (prn "args: " args)
    (dotimes [i 10000000]
      (do-parse))))

(comment
  ;With Transient Map :inline
  ; no inline clojure.lang.PersistentHashMap$BitmapIndexedNode::assoc (552 bytes)   hot method too big
  "Evaluation count : 8496780 in 60 samples of 141613 calls.
             Execution time mean : 7.039408 µs
    Execution time std-deviation : 119.384282 ns
   Execution time lower quantile : 6.886992 µs ( 2.5%)
   Execution time upper quantile : 7.284869 µs (97.5%)
                   Overhead used : 1.817498 ns"

  ;With PersistentArrayMap
  "
  Evaluation count : 13056660 in 60 samples of 217611 calls.
             Execution time mean : 4.542464 µs
    Execution time std-deviation : 166.410236 ns
   Execution time lower quantile : 4.347147 µs ( 2.5%)
   Execution time upper quantile : 4.799114 µs (97.5%)
                   Overhead used : 1.792457 ns

Found 1 outliers in 60 samples (1.6667 %)
	low-severe	 1 (1.6667 %)
 Variance from outliers : 23.7872 % Variance is moderately inflated by outliers
  "

  ;With ArrayList then new PersistentArrayMap(list.toArray())
  "
  Evaluation count : 19772100 in 60 samples of 329535 calls.
             Execution time mean : 3.057527 µs
    Execution time std-deviation : 31.518001 ns
   Execution time lower quantile : 3.030034 µs ( 2.5%)
   Execution time upper quantile : 3.163325 µs (97.5%)
                   Overhead used : 1.553263 ns

Found 3 outliers in 60 samples (5.0000 %)
	low-severe	 3 (5.0000 %)
 Variance from outliers : 1.6389 % Variance is slightly inflated by outliers
  "

  ;;;with 100 000 dotimes
  "
  new String:
  Execution time mean : 350.024593 ms

  char[] array.
  Execution time mean : 337.154454 ms

  mix: Strings use new String, numbers use char[] array
  Execution time mean : 321.539024 ms
  "

  )