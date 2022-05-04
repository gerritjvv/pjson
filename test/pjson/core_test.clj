(ns pjson.core-test
  (:require [clojure.test :refer :all]
            [pjson.core :refer :all])
  (:import [pjson CharArrayTool]
           [java.util HashMap ArrayList LinkedList TreeSet]))


(defn equal-after-decode [m1 m2]
  (= (-> m1 write-str read-str) m2))

(deftest empty-map-read
  (is (= (read-str "{}") {})))


(deftest empty-list-read
  (is (= (read-str "[]") [])))


(deftest test-string-escaping
  (let [test-str "[\"mystring\"]"]
    (is (= (CharArrayTool/endOfString (char-array test-str) 2 (count test-str)) 10)))

  (let [test-str "[\"mystri\\\"ng\"]"]
    (is (= (CharArrayTool/endOfString (char-array test-str) 2 (count test-str)) 12))))


(deftest test-readmap
  (is (= (read-str "{\"a\": 1, \"b\": 2}") {"a" 1 "b" 2}))
  (is (= (read-str "{\"a\": 1, \"b\": 2, \"c\": {\"d\": 3}}") {"a" 1 "b" 2 "c" {"d" 3}}))
  (is (= (read-str "{\"a\": 1, \"b\": 2, \"c\": {\"d\": [1, 2, 3]}}") {"a" 1 "b" 2 "c" {"d" [1 2 3]}})))


(deftest test-vector
  (is (= (read-str "[\"a\", \"b\"]") ["a" "b"]))
  (is (= (read-str "[\"a\", \"b\", [\"c\"]]") ["a" "b" ["c"]]))
  (is (= (read-str "[\"a\", \"b\", [\"c\"], {\"d\": 1}]") ["a" "b" ["c"] {"d" 1}])))


(deftest test-keywords
  (let [msg (write-str {:a 1 'b 2})]
    (is (or
          (=  msg "{\"b\":2,\"a\":1}")
          (=  msg "{\"a\":1,\"b\":2}")))))


(deftest test-types-to-string
  (is (equal-after-decode (array-map "a" 1 "b" 2) {"a" 1 "b" 2})) ;test PersistentArrayMap
  (is (equal-after-decode (array-map "a" -1 "b" -2.1) {"a" -1 "b" -2.1})) ;test negative numbers
  (is (equal-after-decode (array-map "a" true "b" false) {"a" true "b" false})) ;test boolean

  (is (equal-after-decode (hash-map "a" 1 "b" 2) {"a" 1 "b" 2})) ;test PersistentHashMap
  (is (equal-after-decode (vector 1 2 3) [1 2 3]))          ;test PersistentVector
  (is (equal-after-decode (sequence [1 2 3]) [1 2 3]))          ;test Sequence

  (is (equal-after-decode (doto (HashMap.) (.put "a" 1) (.put "b" 2)) {"a" 1 "b" 2})) ;test HashMap
  (is (equal-after-decode (doto (ArrayList.) (.add 1.2) (.add 2.3)) [1.2 2.3])) ;test List
  (is (equal-after-decode (doto (LinkedList.) (.add 1.2) (.add 2.3)) [1.2 2.3])) ;test Collection/Iterable
  (is (equal-after-decode (doto (TreeSet.) (.add 1.2) (.add 2.3)) [1.2 2.3])) ;test Collection/Iterable
  )


(deftest read-str-keys-1-test
         (let [data {:a 1 :b {:c [1 2 3]}}]
              (is (= (read-str-keys (write-str data)) data))))

(deftest read-str-keys-2-test
         (let [data {:a 1 :b {:c [1 2 3]}}]
              (is (= (read-str-keys (write-str data) DEFAULT_CHARSET) data))))


(deftest read-str-keys-2-test
         (let [data {:a 1 :b {:c [1 2 3]}}
               json-str (write-str data)]
              (is (= (read-str-keys json-str DEFAULT_CHARSET 0 (count json-str)) data))))

(deftest read-str-dyanmic-keys-1-test
         (binding [*key-fn* keyword]
                  (let [data {:a 1 :b {:c [1 2 3]}}]
                       (is (= (read-str (write-str data)) data)))))


(deftest read-str-dyanmic-keys-2-test
         (binding [*key-fn* keyword]
                  (let [data {:a 1 :b {:c [1 2 3]}}]
                       (is (= (read-str (write-str data) DEFAULT_CHARSET) data)))))

(deftest read-str-dyanmic-keys-3-test
         (binding [*key-fn* keyword]
                  (let [data {:a 1 :b {:c [1 2 3]}}
                        json-str (write-str data)]
                       (is (= (read-str json-str DEFAULT_CHARSET 0 (count json-str)) data)))))
