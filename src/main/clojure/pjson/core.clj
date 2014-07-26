(ns pjson.core
  (:require [pjson.data :refer [msg-bts]]
            [criterium.core :as crit])
  (:import [pjson PJSON])
  (:gen-class))

(defn bts->json
  ([^"[B" bts]
   (PJSON/defaultParse msg-bts)))

(defn do-parse []
  (PJSON/defaultParse msg-bts))


(defn bench []
  (crit/with-progress-reporting  (crit/bench (do-parse))))

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


  )