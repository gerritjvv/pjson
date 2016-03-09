(ns pjson.number-overflow-test
  (:require [clojure.test :refer :all]
            [pjson.core :as json])
  (:import [java.math BigDecimal BigInteger]))


(defn test-num-str [n]
  (apply str (repeatedly n #(rand-int 10))))

(defn negate-str [is-neg s]
  (if is-neg
    (str "-" s)
    s))

(defn test-num
  ([n]
    (test-num n false))
  ([n is-neg]
   (try
     (Long/parseLong (negate-str is-neg (test-num-str n)))
     (catch NumberFormatException _ (test-num n is-neg)))))

(defn test-double
  ([n]
    (test-double n false))
  ([n is-neg]
   (try
     (Double/parseDouble (negate-str is-neg (str "0." (test-num-str n))))
     (catch NumberFormatException _ (test-double n is-neg)))))

(defn test-biginteger
  ([n]
    (test-biginteger n false))
  ([n is-neg]
   (BigInteger. (negate-str is-neg (test-num-str n)))))

(defn test-bigdecimal
  ([n]
    (test-bigdecimal n false))
  ([n is-neg]
   (BigDecimal. (negate-str is-neg (str (test-num-str n) "." (test-num-str n))))))

(defn message [n]
  (str "{\"num\": " n "}"))

(defn num [msg] (get msg "num"))

(deftest read-number-test
  (dotimes [i 19]
    (let [n (inc i)
          test-n (test-num n)]
      (is
        (=
          test-n
          (num (json/read-str (message test-n))))))))

(deftest read-number-neg-test
  (dotimes [i 19]
    (let [n (inc i)
          is-neg true
          test-n (test-num n is-neg)]
      (is
        (=
          test-n
          (num (json/read-str (message test-n))))))))

(deftest read-double-test
  (dotimes [i 19]
    (let [n (inc i)
          test-n (test-double n)]
      (is
        (=
          test-n
          (num (json/read-str (message test-n))))))))

(deftest read-double-neg-test
  (dotimes [i 19]
    (let [n (inc i)
          is-neg true
          test-n (test-double n is-neg)]
      (is
        (=
          test-n
          (num (json/read-str (message test-n))))))))

(deftest read-biginteger-test
  (dotimes [i 40]
    (let [n (+ i 20)
          test-n (test-biginteger n)]
      (is
        (=
          test-n
          (num (json/read-str (message test-n))))))))

(deftest read-biginteger-neg-test
  (dotimes [i 40]
    (let [n (+ i 20)
          is-neg true
          test-n (test-biginteger n is-neg)]
      (is
        (=
          test-n
          (num (json/read-str (message test-n))))))))

(deftest read-bigdecimal-test
  (dotimes [i 40]
    (let [n (+ i 20)
          test-n (test-bigdecimal n)]
      (is
        (=
          test-n
          (num (json/read-str (message test-n))))))))

(deftest read-bigdecimal-neg-test
  (dotimes [i 40]
    (let [n (+ i 20)
          is-neg true
          test-n (test-bigdecimal n is-neg)]
      (is
        (=
          test-n
          (num (json/read-str (message test-n))))))))