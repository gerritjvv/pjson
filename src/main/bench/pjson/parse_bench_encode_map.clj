(ns pjson.parse-bench-encode-map
  (:require [pjson.core :refer [read-str write-str]]
            [clojure.data.json :as data-json]
            [clj-json [core :as clj-json]]
            [cheshire.core :as cheshire]
            [clj-json.core :as clj-json]
            [cheshire.core :as cheshire])
  (:import [org.boon.json  JsonFactory])
  (:use perforate.core))

;;Test message encoding from a clojure map instance, i.e no lazy constructs or anything, just raw encoding speed.

(defgoal json-parse-encode-map "JSON Encode Map Benchmark"
         :setup (fn []
                    [(-> "test-resources/msg.json" slurp cheshire/parse-string)]))

(defcase json-parse-encode-map :pjson
         [msg]
         (dotimes [i 100000]
                  (write-str msg)))

(defcase json-parse-encode-map :clj-json
         [msg]
         (dotimes [i 100000]
                  (clj-json/generate-string msg)))

(defcase json-parse-encode-map :boon
         [msg]
         (dotimes [i 100000]
                  (JsonFactory/toJson msg)))


(defcase json-parse-encode-map :data.json
         [msg]
         (dotimes [i 100000]
                  (data-json/write-str msg)))



(defcase json-parse-encode-map :cheshire
         [msg]
         (dotimes [i 100000]
                  (cheshire/generate-string msg)))
(comment

  )