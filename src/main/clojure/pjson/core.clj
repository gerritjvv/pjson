(ns pjson.core
    (:import [pjson PJSON StringUtil JSONAssociative ToJSONString JSONGenerator]
      (java.nio.charset Charset)
      (java.util Map Collection)
      (clojure.lang Seqable IPersistentMap)
      (pjson.key StringKeyFn KeyFn ClojureFn KeywordKeyFn))
  (:gen-class))

(defonce ^Charset DEFAULT_CHARSET (StringUtil/DEFAULT_CHAR_SET))


(def ^:dynamic *key-fn* "Key function that is used to transform map keys, called using (*key-fn* kw-str)" StringKeyFn/INSTANCE)

(defprotocol JSONToString
  "Convert an ordinary java/clojure object to a JSON compcatible String"
  (write-str [obj]))

(defprotocol JSONParser
  "Convert a json string to clojure objects lazily"
  (read-str [obj] [obj ^Charset charset] [obj ^Charset charset ^Long from ^Long len]))

;compatibility functions for easy swap between other libraries
;note that only arity one support is added, use read-str for multi arity support
(defn asObj [obj] (read-str obj))
(defn parse-string [obj] (read-str obj))
(defn asString [obj] (write-str obj))
(defn generate-string [obj] (write-str obj))

(defn get-charset [n]
  (Charset/forName (str n)))


(defn -create-key-fn ^KeyFn [key-fn]
      (if (= key-fn keyword)
        KeywordKeyFn/INSTANCE
        (ClojureFn/create key-fn)))

(defn- bts->lazy-json
  ([^"[B" bts]
   (bts->lazy-json DEFAULT_CHARSET bts))
  ([^Charset charset ^"[B" bts]
   (PJSON/defaultLazyParse charset bts  ^KeyFn (-create-key-fn *key-fn*)))
  ([^Charset charset ^"[B" bts ^Long from ^Long len]
   (PJSON/defaultLazyParse charset bts (int from) (int len)  ^KeyFn (-create-key-fn *key-fn*))))


(extend-protocol JSONParser
  String
  (read-str
    ([obj] (read-str (StringUtil/toCharArray ^String obj)))
    ([obj charset] (read-str (StringUtil/toCharArray ^String obj) ^Charset charset))
    ([obj charset from len] (read-str (StringUtil/toCharArray ^String obj) ^Charset charset ^Long from ^Long len))))

(extend-protocol JSONParser
  (Class/forName "[C")
  (read-str
    ([^"[C" obj]
     (PJSON/defaultLazyParse DEFAULT_CHARSET ^"[C" obj ^KeyFn (-create-key-fn *key-fn*)))
    ([^"[C" obj charset]
     (PJSON/defaultLazyParse ^Charset charset ^"[C" obj  ^KeyFn (-create-key-fn *key-fn*)))
    ([^"[C" obj charset ^Long from ^Long len]
     (PJSON/defaultLazyParse ^Charset charset ^"[C" obj (int from) (int len)  ^KeyFn (-create-key-fn *key-fn*)))))

(extend-protocol JSONParser
  (Class/forName "[B")
  (read-str
    ([^"[B" obj]
     (bts->lazy-json obj))
    ([^"[B" obj charset]
     (bts->lazy-json charset obj))
    ([^"[B" obj charset from len]
     (bts->lazy-json charset obj from len))))


(extend-protocol JSONToString
  ToJSONString
  (write-str [^ToJSONString this]
    (.toString this))
  IPersistentMap
  (write-str [^IPersistentMap this]
    (JSONGenerator/forObj this))
  Map
  (write-str [^Map this]
    (JSONGenerator/forObj this))
  Collection
  (write-str [^Collection this]
    (JSONGenerator/forObj this))
  Seqable
  (write-str [^Seqable this]
    (JSONGenerator/forObj this))
  Object
  (write-str [^Object this]
    (JSONGenerator/forObj this)))
