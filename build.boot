
(set-env!
  :resource-paths #{"src"}
  :dependencies '[])

(def +version+ "0.1.0")

(deftask build []
  (comp
    (pom :project     'respo/inflow-popup
         :version     +version+
         :description "Local popup component for Respo"
         :url         "https://github.com/Respo/inflow-popup"
         :scm         {:url "https://github.com/mvc-works/coworkflow"}
         :license     {"MIT" "http://opensource.org/licenses/mit-license.php"})
    (jar)
    (install)
    (target)))

(deftask deploy []
  (set-env!
    :repositories #(conj % ["clojars" {:url "https://clojars.org/repo/"}]))
  (comp
    (build)
    (push :repo "clojars" :gpg-sign (not (.endsWith +version+ "-SNAPSHOT")))))
