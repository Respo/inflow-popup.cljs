
(ns inflow-popup.config (:require [inflow-popup.util :refer [get-env!]]))

(def bundle-builds #{"release" "local-bundle"})

(def dev?
  (if (exists? js/window)
    (do ^boolean js/goog.DEBUG)
    (not (contains? bundle-builds (get-env! "mode")))))

(def site
  {:storage "inflow-popup",
   :dev-ui "http://localhost:8100/main.css",
   :release-ui "http://cdn.tiye.me/favored-fonts/main.css",
   :cdn-url "http://cdn.tiye.me/inflow-popup/",
   :cdn-folder "tiye.me:cdn/inflow-popup",
   :title "Inflow popup",
   :icon "http://cdn.tiye.me/logo/respo.png",
   :upload-folder "tiye.me:repo/Respo/inflow-popup/"})
