(ns pjson.issue-20-index-out-of-bounds-test
  (:require [pjson.core :as pjson]
            [clojure.test :refer :all]))


(deftest test-reading
  (is (pjson.core/read-str "[{\"a\":\"J\\\\\"}]")))