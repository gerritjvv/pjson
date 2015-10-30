(ns
  ^{:doc "Test reading data that has characters {} [] in the string values and keys"}
  pjson.escaping-test
  (:require
    [pjson.core :as pjson]))



(defn read-msg []
  (pjson/read-str (slurp "test-resources/escapetest.json")))

(defn test-as-str []
  (let [org-msg (read-msg)
        s (pjson/write-str org-msg)
        msg (pjson/parse-string s)]

    (= (get-in org-msg "a")
       (get-in msg "a"))))