(ns pjson.data)


(def msg (slurp "test-resources/msg.json"))

(def ^"[B" msg-bts (.getBytes msg "UTF-8"))


(def msg2 (slurp "test-resources/msg-test.json"))

(def ^"[B" msg-bts2 (.getBytes msg2 "UTF-8"))