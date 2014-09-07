(ns pjson.core-test
  (:require [clojure.test :refer :all]
            [pjson.core :refer :all])
  (:import [pjson CharArrayTool]))


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