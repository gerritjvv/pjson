(ns pjson.core
  (:import [pjson PJSON StringUtil]
           (java.nio.charset Charset))
  (:gen-class))

(defonce DEFAULT_CHARSET (StringUtil/DEFAULT_CHAR_SET))

(defn get-charset [n]
  (Charset/forName n))

(defn bts->json
  ([^"[B" bts]
   (bts->json DEFAULT_CHARSET bts))
  ([^Charset charset ^"[B" bts]
   (PJSON/defaultParse charset bts))
  ([^Charset charset ^"[B" bts ^Long from ^Long len]
   ;defaultParse(final Charset charset, final byte[] bts, final int start, final int len)
   (PJSON/defaultParse charset bts (int from) (int len))))


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