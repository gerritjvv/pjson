 (ns pjson.number-bench
     (:import (pjson NumberUtil))
     (:use perforate.core))

 (comment

   (defn number->str [n]
         (apply str (take n (apply str (take n (range 1 (inc n)))))))

   (defonce iter 1000)
   (defgoal number-parse "Number parse Benchmark"
            :setup (fn [] (let [s (number->str 5)]
                               [(.toCharArray s) s (count s)])))


   (defn parse5 [^chars ch]
         (NumberUtil/parse_5 ch (int 0)))


   (defn valueOf [^String s]
         (Long/valueOf s))


   (defcase number-parse :number-util-5 [^chars ch s l] (parse5 ch))

   (defcase number-parse :integer [_ ^String s l] (valueOf s))
   )