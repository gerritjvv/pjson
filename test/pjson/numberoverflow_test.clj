(ns
  ^{:doc "Test that we can handle big integer and big decimal numbers if integer/double overflow happens,
          this is assumed better than throwing an excpetion and is more in line with clojure's auto promotion of things"}
  pjson.numberoverflow-test
  (:require [pjson.core :as pjson]
            [clojure.test :refer :all]))


(def big-integer-n 111111111111111111111111111111111111111111111111111111111111111111111)

(defn parse-big-integer-message []
  (pjson/parse-string (str "{\"a\": " big-integer-n "}")))


(deftest test-parse-big-integer
  (= (parse-big-integer-message) big-integer-n))