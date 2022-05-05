(ns pjson.bigdecimal-test
    (:require [clojure.test :refer :all]
      [pjson.core :refer :all]))



(defn big-decimal
      ([] (big-decimal (* (Math/random) 0.0000001)))
      ([n] (BigDecimal. n)))


(deftest test-reading-decimal-bug-25 []
         (is (= (read-str "[1.96264948324e+1000]") [(BigDecimal. "1.96264948324e+1000")]))
         (is (= (read-str "[-1101.96264948324e+1000]") [(BigDecimal. "-1101.96264948324e+1000")]))

         (is (= (read-str "[1.96264948324e-1000]") [(BigDecimal. "1.96264948324e-1000")]))
         (is (= (read-str "[-1101.96264948324e-1000]") [(BigDecimal. "-1101.96264948324e-1000")]))

         (is (= (read-str "[1.96264948324e+11]") [(BigDecimal. "1.96264948324e+11")]))
         (is (= (read-str "[-1.96264948324e+11]") [(BigDecimal. "-1.96264948324e+11")]))

         (is (= (read-str "[-4.626E-4]") [ (BigDecimal. "-4.626E-4")]))
         (is (= (read-str "[-4.626E+4]") [ (BigDecimal. "-4.626E+4")]))
         (is (= (read-str "[4.626E-4]") [ (BigDecimal. "4.626E-4")]))
         (is (= (read-str "[4.626E+4]") [ (BigDecimal. "4.626E+4")]))

         (is (= (read-str "[-4E-4]") [ (BigDecimal. "-4E-4")]))
         (is (= (read-str "[-4E+4]") [ (BigDecimal. "-4E+4")]))

         (is (= (read-str "[4E-4]") [ (BigDecimal. "4E-4")]))
         (is (= (read-str "[4E+4]") [ (BigDecimal. "4E+4")]))

         )



(deftest test-random-big-decimals []
         (doseq [_ (range 0 10000)]
                (let [b (big-decimal)
                      b-str (str "[" (str b) "]")]

                     (is (read-str b-str) [b]))
                ))