
(defn read-password [guide]
  (String/valueOf (.readPassword (System/console) guide nil)))

(set-env!
  :resource-paths #{"src"}
  :dependencies '[]
  :repositories #(conj % ["clojars" {:url "https://clojars.org/repo/"
                                     :username "jiyinyiyong"
                                     :password (read-password "Clojars password: ")}]))

(def +version+ "0.2.1")

(deftask deploy []
  (comp
    (pom :project     'respo/inflow-popup
         :version     +version+
         :description "Local popup component for Respo"
         :url         "https://github.com/Respo/inflow-popup"
         :scm         {:url "https://github.com/Respo/inflow-popup"}
         :license     {"MIT" "http://opensource.org/licenses/mit-license.php"})
    (jar)
    (install)
    (push :repo "clojars" :gpg-sign false)))
