(ns
  ^{:doc "https://github.com/gerritjvv/pjson/issues/22

  unicode source for testing http://www.w3schools.com/charsets/ref_utf_basic_latin.asp
  "}
  pjson.issue-22-unicode-chars-test
  (:require [pjson.core :as pjson]
            [clojure.test :refer :all]))


(defn parse-char
  "expect a number like 0027 or 0000"
  [unicode]
  (String. (Character/toChars (Integer/parseInt (str unicode) 16))))

(defn unicode-seq [from to]
  (map #(format "%04x" %) (range from (inc to))))

(def unicode-map
  {"basic-latin"            (unicode-seq 0 127)
   "basic-latin-supplement" (unicode-seq 128 255)
   "latin-extended-a"       (unicode-seq 256 383)
   "latin-extended-b"       (unicode-seq 384 591)
   "modified-letters"       (unicode-seq 688 767)
   "diacritical-marks"      (unicode-seq 768 879)
   "greek-and-coptic"       (unicode-seq 880 1023)
   "cycrillic-basic"        (unicode-seq 1024 1279)
   "cycrillic-supplement"   (unicode-seq 1280 1327)})


(deftest test-unicode-parse
  (is
    (= (pjson/read-str "[\"opportunity\\u0027s\\u0027\\u0027\\u0027\",\"opportunities\"]")
       ["opportunity's'''" "opportunities"])))

(defn create-json-msg [unicode]
  (str "{\"a\": [\"mystring" "\\u" unicode "anditgoeson\"]}"))


(defn create-obj-msg [unicode]
  {"a" [(str "mystring" (parse-char unicode) "anditgoeson")]})

(deftest test-unicode-chars
  (doseq [[encoding unicodes] unicode-map]
    (prn encoding)
    (doseq [unicode unicodes]
      (is
        (=
          (pjson/parse-string (create-json-msg unicode))
          (create-obj-msg unicode))))))