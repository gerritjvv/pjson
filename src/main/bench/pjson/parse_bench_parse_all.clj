(ns pjson.parse-bench-parse-all
  "Forces all fields in the parsed message to be accessed"
  (:require [pjson.core :refer [asObj asString]]
            [clojure.data.json :as data-json]
            [clj-json [core :as clj-json]]
            [cheshire.core :as cheshire])
  (:import [org.boon.json  JsonFactory])
  (:use perforate.core))

(defonce iter 1000)
(defgoal json-parse-all "JSON Parse Benchmark - parse all fields and substructures"
         :setup (fn [] (let [msg (-> "test-resources/msg.json" slurp )]
                            [(.getBytes msg "UTF-8") msg])))

(defcase json-parse-all :pjson
         [^"[B" bts _]
         (binding [pjson.core/*key-fn* keyword]
                  (dotimes [i iter]
                           (pr-str (asObj bts)))))

;(defcase json-parse-all :boon
;         [_ ^String msg]
;         (dotimes [i iter]
;                  (pr-str (JsonFactory/fromJson msg))))

;(defcase json-parse-all :data.json
;         [_ ^String msg]
;         (dotimes [i iter]
;                  (pr-str (data-json/read-str msg))))

(defcase json-parse-all :clj-json
         [_ ^String msg]
         (dotimes [i iter]
                  (pr-str (clj-json/parse-string msg))))


(defcase json-parse-all :cheshire
         [_ ^String msg]
         (dotimes [i iter]
                  (pr-str (cheshire/parse-string msg))))
(comment
 )