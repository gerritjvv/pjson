(ns pjson.parse-bench-practical
  "Bencharks the json libraries by creating a practical example where not all fields are accessed
   The test does a parse and then write using each lib's to json function"
  (:require [pjson.core :refer [read-str write-str]]
            [clojure.data.json :as data-json]
            [clj-json [core :as clj-json]]
            [cheshire.core :as cheshire]
            [clojure.data.json :as data-json]
            [clj-json.core :as clj-json]
            [cheshire.core :as cheshire])
  (:import (org.boon.json  JsonFactory))
  (:use perforate.core))

(defonce iter 1000)
(defgoal json-parse-practical "JSON Parse Practical Example"
         :setup (fn [] (let [msg (-> "test-resources/msg.json" slurp )]
                            [(.getBytes msg "UTF-8") msg])))

(defn- add-data [json n]
       (assoc json
              :mykey 122
              :abc {"hi" "anothertest"}
              "final-add" [1 2 3 4]))

(defn- get-data [json]
       (get-in json ["other_info" "region"]))


(defn- do-work [json]
       (add-data json (get-data json)))

(defcase json-parse-practical :pjson
         [^"[B" bts _]
         (dotimes [i iter]
                  (-> bts read-str do-work write-str)))

(defcase json-parse-practical :boon
         [_ ^String msg]
         (dotimes [i iter]
                  (->> msg JsonFactory/fromJson (into {}) do-work JsonFactory/toJson)))

(defcase json-parse-practical :data.json
         [_ ^String msg]
         (dotimes [i iter]
                  (-> msg data-json/read-str do-work data-json/write-str)))

(defcase json-parse-practical :clj-json
         [_ ^String msg]
         (dotimes [i iter]
                  (-> msg clj-json/parse-string do-work clj-json/generate-string)))


(defcase json-parse-practical :cheshire
         [_ ^String msg]
         (dotimes [i iter]
                  (-> msg cheshire/parse-string do-work cheshire/generate-string)))
