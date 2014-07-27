(defproject pjson "0.1.0"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}


  :source-paths      ["src/main/clojure"]
  :java-source-paths ["src/main/java"]
  :global-vars {*warn-on-reflection* true
                *assert* false}

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
                  [org.clojure/clojure "1.6.0"]])
