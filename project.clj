(defproject pjson "0.2.8-SNAPSHOT"
            :description "Fast clojure json library"
            :url "https://github.com/gerritjvv/pjson"
            :license {:name "Eclipse Public License"
                      :url  "http://www.eclipse.org/legal/epl-v10.html"}


            :plugins [[perforate "0.3.3"]]

            :source-paths ["src/main/clojure"]
            :java-source-paths ["src/main/java"]
            :global-vars {*warn-on-reflection* true
                          *assert*             false}

            :javac-options ["-target" "1.5" "-source" "1.5" "-Xlint:-options"]

            :jvm-opts ["-Xmx1g"
                       "-server"
                       ;"-XX:+UnlockDiagnosticVMOptions"
                       ;"-XX:+PrintInlining"
                       ;"-XX:MaxInlineSize=600"

                       ;"-XX:+PrintCompilation"
                       ;"-XX:CompileThreshold=100"
                       ]
            :aot [pjson.core]
            :main pjson.core
            :dependencies [
                           [criterium "0.4.3"]
                           [org.clojure/clojure "1.6.0"]
                           [io.fastjson/boon "0.18"]
                           [org.clojure/data.json "0.2.5"]
                           [clj-json "0.5.3"]
                           [cheshire "5.3.1"]]

            :perforate {:benchmark-paths ["src/main/bench/"]})
