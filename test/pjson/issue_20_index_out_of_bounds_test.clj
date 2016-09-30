(ns pjson.issue-20-index-out-of-bounds-test
  (:require [pjson.core :as pjson]
            [clojure.test :refer :all]))


(deftest test-reading
  (is
    (=
      (pjson/read-str "[{\"a\":\"J\\\\\"}]")
      [{"a" "J\\"}])))

(deftest test-read-write-escape
  (is
    (=
      (pjson/read-str (pjson/write-str (pjson/read-str (pjson/write-str (pjson/read-str (pjson/write-str [{:name "Joe\\"}]))))))
      [{"name" "Joe\\"}])))