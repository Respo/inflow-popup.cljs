
(ns inflow-popup.comp.dialog
  (:require [respo.alias :refer [create-comp div]]
            [respo.comp.text :refer [comp-text]]
            [inflow-popup.style.layout :as layout]
            [inflow-popup.style.widget :as widget]
            [inflow-popup.style.decoration :as decoration]))

(defn on-focus [e dispatch!])

(defn render [on-close element-inside]
  (fn [state mutate!]
    (div
      {:style
       (merge
         layout/float-fullscreen
         decoration/dim
         layout/hold-center),
       :event {:click (fn [e dispatch!] (on-close))}}
      (div
        {:style widget/card, :event {:click on-focus}}
        element-inside))))

(def comp-dialog (create-comp :dialog render))
