
(ns inflow-popup.comp.container
  (:require [hsl.core :refer [hsl]]
            [respo.alias :refer [create-comp div span]]
            [respo.comp.text :refer [comp-text]]
            [respo.comp.debug :refer [comp-debug]]
            [inflow-popup.comp.dialog :refer [comp-dialog]]
            [inflow-popup.comp.dropdown :refer [comp-dropdown]]
            [inflow-popup.style.widget :as widget]
            [inflow-popup.style.layout :as layout]
            [inflow-popup.style.typeset :as typeset]
            [inflow-popup.style.decoration :as decoration]))

(defn update-state [state op op-data]
  (case
    op
    :show?
    (update state :show? not)
    :selected
    (assoc state :selected op-data)
    state))

(defn on-click [mutate!] (fn [e dispatch!] (mutate! :show?)))

(defn on-close [mutate!] (fn [] (mutate! :show?)))

(def example-data ["Clojure" "PureScript" "Flow by Facebook" "Elm"])

(defn on-select [mutate!]
  (fn [next-item] (mutate! :selected next-item)))

(defn init-state [store] {:selected (first example-data), :show? false})

(defn render [store]
  (fn [state mutate!]
    (div
      {:style (merge widget/card typeset/page-default)}
      (div
        {:style (merge layout/row widget/card)}
        (div {:style layout/field-area} (comp-text "a dialog" nil))
        (div
          {}
          (div
            {:style widget/button, :event {:click (on-click mutate!)}}
            (comp-text "Toggle" nil)))
        (if (:show? state)
          (comp-dialog
            (on-close mutate!)
            (div {} (comp-text "Inside" nil)))))
      (div
        {:style (merge layout/row widget/card)}
        (div {:style layout/field-area} (comp-text "a droplist" nil))
        (comp-dropdown
          example-data
          (:selected state)
          (on-select mutate!)))
      (comp-debug state nil))))

(def comp-container
 (create-comp :container init-state update-state render))
