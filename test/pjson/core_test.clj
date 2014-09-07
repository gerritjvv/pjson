(ns pjson.core-test
  (:require [clojure.test :refer :all]
            [pjson.core :refer :all]))


(deftest test-string-escaping

  (is (=
        (-> "{\"a\":\"1 \\\"  \"}"
            asObj asString asObj asString asObj)
        {"a" "1 \\\"  "})))