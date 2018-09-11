
(def config {:clojars-user "jiyinyiyong"
             :package 'respo/inflow-popup
             :version "0.2.3"
             :github-url "https://github.com/Respo/inflow-popup"
             :description "Local popup component for Respo"})

(defn read-password [guide]
  (String/valueOf (.readPassword (System/console) guide nil)))

(set-env!
  :resource-paths #{"src"}
  :dependencies '[]
  :repositories #(conj % ["clojars" {:url "https://clojars.org/repo/"
                                     :username (:clojars-user config)
                                     :password (read-password "Clojars password: ")}]))

(deftask deploy []
  (comp
    (pom :project     (:package config)
         :version     (:version config)
         :description (:description config)
         :url         (:github-url config)
         :scm         {:url (:github-url config)}
         :license     {"MIT" "http://opensource.org/licenses/mit-license.php"})
    (jar)
    (install)
    (push :repo "clojars" :gpg-sign false)))
