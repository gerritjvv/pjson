(ns pjson.parse-bench-encode
  (:require [pjson.core :refer [read-str write-str]]
            [clojure.data.json :as data-json]
            [clj-json [core :as clj-json]]
            [cheshire.core :as cheshire]
            [clj-json.core :as clj-json]
            [cheshire.core :as cheshire])
  (:import [org.boon.json  JsonFactory])
  (:use perforate.core))

;;Test message to encoding using for each library the original message as read by the library itself.

(defgoal json-parse-encode "JSON Encode Simple"
         :setup (fn []
                    (let [msg (slurp "test-resources/msg.json")]
                         [{:pjson (read-str msg)
                           :boon (JsonFactory/fromJson msg)
                           :clj-json (clj-json/parse-string msg)
                           :data-json (data-json/read-str msg)
                           :cheshire (cheshire/parse-string msg)}])))

(defonce iter 1000)
(defcase json-parse-encode :pjson
         [{:keys [pjson]}]
         (dotimes [i iter]
                  (write-str pjson)))
(defcase json-parse-encode :boon
         [{:keys [boon]}]
         (dotimes [i iter]
                  (JsonFactory/toJson boon)))


(defcase json-parse-encode :data.json
         [{:keys [data-json]}]
         (dotimes [i iter]
                  (data-json/write-str data-json)))

(defcase json-parse-encode :clj-json
         [{:keys [clj-json]}]
         (dotimes [i iter]
                  (clj-json/generate-string clj-json)))


(defcase json-parse-encode :cheshire
         [{:keys [cheshire]}]
         (dotimes [i iter]
                  (cheshire/generate-string cheshire)))
