(ns pjson.bench
  (:require [pjson.core :refer [bts->json]]
            [pjson.data :refer [msg-bts msg]]
            [criterium.core :as crit])
  (:import [org.boon.json  JsonFactory JsonParserFactory ObjectMapper JsonParserAndMapper]))


(defn do-boon []
  (into {} (JsonFactory/fromJson ^String (String. msg-bts))))

(defn do-parse []
  (bts->json msg-bts))


(defn do-parse2 []
  (dotimes [i 100000]
    (bts->json msg-bts)))


(defn do-boon2 []
  (dotimes [i 100000]
    (do-boon)))

(defn bench []
  (crit/with-progress-reporting  (crit/bench (do-parse))))

(defn bench2 []
  (crit/with-progress-reporting  (crit/bench (do-parse2))))

  (defn bench-boon []
    (crit/with-progress-reporting  (crit/bench (do-boon2))))

(comment
  "boon
   Execution time mean : 1.372680 sec
  "
  "
  pjson
    Execution time mean : 2.061676 sec,  Execution time mean : 2.416611 sec
  "
  )