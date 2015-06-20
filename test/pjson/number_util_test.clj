(ns pjson.number-util-test
  (:use clojure.test)
  (:import
           (pjson NumberUtil)))


(defn call-number [n s]
  (clojure.lang.Reflector/invokeStaticMethod NumberUtil (str "parse_" n) (object-array [(.toCharArray (str s)) 0])))


(defn number->str [n]
  (apply str (take n (apply str (take n (range 1 (inc n)))))))

(deftest parse-number []
                      (dotimes [i 19]
                        (is
                          (=
                            (call-number (inc i) (number->str (inc i)))
                            (Long/parseLong (number->str (inc i)))))))