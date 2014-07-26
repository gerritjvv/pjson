(ns pjson.data)


(def msg (slurp "test-resources/msg.json"))

(def ^"[B" msg-bts (.getBytes msg "UTF-8"))