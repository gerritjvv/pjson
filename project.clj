(defproject pjson "1.0.0"
            :description "Fast clojure json library"
            :url "https://github.com/gerritjvv/pjson"
            :license {:name "Eclipse Public License"
                      :url  "http://www.eclipse.org/legal/epl-v10.html"}


            :plugins [[perforate "0.3.3"]
                      [lein-ancient "0.6.15"]]

            :source-paths ["src/main/clojure"]
            :java-source-paths ["src/main/java"]
            :global-vars {*warn-on-reflection* true
                          *assert*             false}

            :javac-options ["-target" "1.8" "-source" "1.8" "-Xlint:-options"]

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
			   [riddley "0.2.0"]
			   [proteus "0.1.6"]
                           [criterium "0.4.6"]
                           [org.clojure/clojure "1.11.1"]
                           [io.fastjson/boon "0.18" :scope "provided"]
                           [org.clojure/data.json "0.2.6" :scope "provided"]
                           [clj-json "0.5.3" :scope "provided"]
                           [cheshire "5.10.2" :scope "provided"]]

            :perforate {:benchmark-paths ["src/main/bench/"]})
