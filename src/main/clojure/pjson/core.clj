(ns pjson.core
  (:import [pjson PJSON StringUtil JSONAssociative ToJSONString]
           (java.nio.charset Charset)
           (java.util Map Collection)
           (clojure.lang Seqable IPersistentMap))
  (:gen-class))

(defonce ^Charset DEFAULT_CHARSET (StringUtil/DEFAULT_CHAR_SET))

(defprotocol JSONToString
  (asString [obj]))

(defprotocol JSONParser
  (asObj [obj]))

(defn get-charset [n]
  (Charset/forName n))

(defn bts->json
  ([^"[B" bts]
   (bts->json DEFAULT_CHARSET bts))
  ([^Charset charset ^"[B" bts]
   (PJSON/defaultParse charset bts))
  ([^Charset charset ^"[B" bts ^Long from ^Long len]
   ;defaultParse(final Charset charset, final byte[] bts, final int start, final int len)
   (PJSON/defaultParse charset bts (int from) (int len))))

(defn bts->lazy-json
  ([^"[B" bts]
   (bts->lazy-json DEFAULT_CHARSET bts))
  ([^Charset charset ^"[B" bts]
   (PJSON/defaultLazyParse charset bts))
  ([^Charset charset ^"[B" bts ^Long from ^Long len]
   (PJSON/defaultLazyParse charset bts (int from) (int len))))

(def Chararray (Class/forName "[C"))

(extend-protocol JSONParser
  String
  (asObj [^String obj]
    (asObj (.toCharArray obj))))

(extend-protocol JSONParser
  (Class/forName "[C")
  (asObj [^"[C" obj]
    (PJSON/defaultLazyParse DEFAULT_CHARSET ^"[C" obj)))

(extend-protocol JSONParser
  (Class/forName "[B")
  (asObj [^"[B" obj]
    (bts->lazy-json obj)))


(extend-protocol JSONToString
  ToJSONString
  (asString [^ToJSONString this]
    (.toString this))
  IPersistentMap
  (asString [^IPersistentMap this]
    (StringUtil/toJSONString this))
  Map
  (asString [^Map this]
    (StringUtil/toJSONString this))
  Collection
  (asString [^Collection this]
    (StringUtil/toJSONString this))
  Seqable
  (asString [^Seqable this]
    (StringUtil/toJSONString this))
  Object
  (asString [^Object this]
    (StringUtil/toJSONString this)))
