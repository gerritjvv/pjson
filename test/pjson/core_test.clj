(ns pjson.core-test
  (:require [clojure.test :refer :all]
            [pjson.core :refer :all])
  (:import [pjson CharArrayTool]
           [java.util HashMap ArrayList LinkedList TreeSet]))


(defn equal-after-decode [m1 m2]
  (= (-> m1 write-str read-str) m2))

(deftest test-string-escaping
  (let [test-str "[\"mystring\"]"]
    (is (= (CharArrayTool/endOfString (char-array test-str) 2 (count test-str)) 10)))

  (let [test-str "[\"mystri\\\"ng\"]"]
    (is (= (CharArrayTool/endOfString (char-array test-str) 2 (count test-str)) 12)))

  (is (=
        (-> "{\"a\":\"1 \\\"  \"}"
            read-str write-str read-str write-str read-str)
        {"a" "1 \\\"  "})))


(deftest test-readmap
  (is (= (read-str "{\"a\": 1, \"b\": 2}") {"a" 1 "b" 2}))
  (is (= (read-str "{\"a\": 1, \"b\": 2, \"c\": {\"d\": 3}}") {"a" 1 "b" 2 "c" {"d" 3}}))
  (is (= (read-str "{\"a\": 1, \"b\": 2, \"c\": {\"d\": [1, 2, 3]}}") {"a" 1 "b" 2 "c" {"d" [1 2 3]}})))


(deftest test-vector
  (is (= (read-str "[\"a\", \"b\"]") ["a" "b"]))
  (is (= (read-str "[\"a\", \"b\", [\"c\"]]") ["a" "b" ["c"]]))
  (is (= (read-str "[\"a\", \"b\", [\"c\"], {\"d\": 1}]") ["a" "b" ["c"] {"d" 1}])))


(deftest test-keywords
  (is (= (write-str {:a 1 'b 2})  "{\"b\":2,\"a\":1}")))


(deftest test-types-to-string
  (is (equal-after-decode (array-map "a" 1 "b" 2) {"a" 1 "b" 2})) ;test PersistentArrayMap
  (is (equal-after-decode (hash-map "a" 1 "b" 2) {"a" 1 "b" 2})) ;test PersistentHashMap
  (is (equal-after-decode (vector 1 2 3) [1 2 3]))          ;test PersistentVector
  (is (equal-after-decode (sequence [1 2 3]) [1 2 3]))          ;test Sequence

  (is (equal-after-decode (doto (HashMap.) (.put "a" 1) (.put "b" 2)) {"a" 1 "b" 2})) ;test HashMap
  (is (equal-after-decode (doto (ArrayList.) (.add 1.2) (.add 2.3)) [1.2 2.3])) ;test List
  (is (equal-after-decode (doto (LinkedList.) (.add 1.2) (.add 2.3)) [1.2 2.3])) ;test Collection/Iterable
  (is (equal-after-decode (doto (TreeSet.) (.add 1.2) (.add 2.3)) [1.2 2.3])) ;test Collection/Iterable

  )
