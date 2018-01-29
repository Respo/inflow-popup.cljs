
(ns inflow-popup.comp.container
  (:require [hsl.core :refer [hsl]]
            [respo.macros :refer [defcomp cursor-> <> div input span]]
            [respo.comp.inspect :refer [comp-inspect]]
            [inflow-popup.comp.dialog :refer [comp-dialog]]
            [inflow-popup.comp.dropdown :refer [comp-dropdown]]
            [inflow-popup.style.widget :as widget]
            [inflow-popup.style.layout :as layout]
            [inflow-popup.style.typeset :as typeset]
            [inflow-popup.style.decoration :as decoration]
            [reel.comp.reel :refer [comp-reel]]))

(def example-data ["Clojure" "PureScript" "Reason" "Elm" "Haskell"])

(def initial-state {:show? false, :selected (first example-data)})

(defn on-click [state] (fn [e d! m!] (m! (update state :show? not))))

(defcomp
 comp-container
 (reel)
 (let [store (:store reel), states (:states store), state (or (:data states) initial-state)]
   (div
    {:style (merge widget/card typeset/page-default)}
    (div
     {:style (merge layout/row widget/card)}
     (div {:style layout/field-area} (<> "a dialog"))
     (div {:style widget/button, :on-click (on-click state)} (<> "Toggle"))
     (if (:show? state)
       (comp-dialog
        (fn [mutate!] (mutate! %cursor (update state :show? not)))
        (div {} (<> "Inside")))))
    (div
     {:style (merge layout/row widget/card)}
     (div {:style layout/field-area} (<> "a droplist"))
     (cursor->
      :dropdown
      comp-dropdown
      states
      example-data
      (:selected state)
      (fn [next-item m!] (m! %cursor (assoc state :selected next-item)))))
    (comp-inspect "state" state nil)
    (cursor-> :reel comp-reel states reel {}))))

(defn on-close [mutate!] (fn [] (mutate! :show?)))
