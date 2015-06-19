(ns pjson.parse-bench
  (:require [pjson.core :refer [read-str]]
            [clojure.data.json :as data-json]
            [clj-json [core :as clj-json]]
            [cheshire.core :as cheshire])
  (:import [org.boon.json  JsonFactory])
  (:use perforate.core))

(defonce iter 1000)
(defgoal json-parse "JSON Parse Benchmark"
         :setup (fn [] (let [msg (-> "test-resources/msg.json" slurp )]
                            [(.getBytes msg "UTF-8") msg])))

(defcase json-parse :pjson
         [^"[B" bts _]
         (dotimes [i iter]
                  (read-str bts)))


(defcase json-parse :boon
         [_ ^String msg]
         (dotimes [i iter]
                  (JsonFactory/fromJson msg)))


(defcase json-parse :data.json
         [_ ^String msg]
         (dotimes [i iter]
                  (data-json/read-str msg)))

(defcase json-parse :clj-json
         [_ ^String msg]
         (dotimes [i iter]
                  (clj-json/parse-string msg)))


(defcase json-parse :cheshire
         [_ ^String msg]
         (dotimes [i iter]
                  (cheshire/parse-string msg)))