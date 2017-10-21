
(ns inflow-popup.main
  (:require [respo.core :refer [render! clear-cache! realize-ssr!]]
            [inflow-popup.comp.container :refer [comp-container]]
            [inflow-popup.updater :refer [updater]]
            [inflow-popup.schema :as schema]
            [reel.util :refer [id!]]
            [reel.schema :as reel-schema]
            [reel.core :refer [reel-updater refresh-reel listen-devtools!]]))

(defonce *reel
  (atom
   (-> reel-schema/reel
       (assoc :base schema/store)
       (assoc :store schema/store)
       (assoc :display? false))))

(defn dispatch! [op op-data]
  (let [op-id (id!), next-reel (reel-updater updater @*reel op op-data op-id)]
    (reset! *reel next-reel)))

(def mount-target (.querySelector js/document ".app"))

(defn render-app! [renderer] (renderer mount-target (comp-container @*reel) dispatch!))

(def ssr? (some? (js/document.querySelector "meta.respo-ssr")))

(defn main! []
  (if ssr? (render-app! realize-ssr!))
  (render-app! render!)
  (add-watch *reel :changes (fn [] (render-app! render!)))
  (listen-devtools! "a" dispatch!)
  (println "App started!"))

(defn reload! []
  (clear-cache!)
  (reset! *reel (refresh-reel @*reel schema/store updater))
  (println "code updated."))

(set! js/window.onload main!)
