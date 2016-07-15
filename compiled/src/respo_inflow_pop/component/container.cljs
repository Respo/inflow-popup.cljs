
(ns respo-inflow-pop.component.container
  (:require [hsl.core :refer [hsl]]
            [respo.alias :refer [create-comp div span]]
            [respo.component.text :refer [comp-text]]
            [respo.component.debug :refer [comp-debug]]
            [respo-inflow-pop.component.dialog :refer [comp-dialog]]
            [respo-inflow-pop.component.dropdown :refer [comp-dropdown]]
            [respo-inflow-pop.style.widget :as widget]
            [respo-inflow-pop.style.layout :as layout]
            [respo-inflow-pop.style.typeset :as typeset]
            [respo-inflow-pop.style.decoration :as decoration]))

(def example-data ["Clojure" "PureScript" "Flow by Facebook" "Elm"])

(defn init-state [store] {:selected (first example-data), :show? false})

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

(defn on-select [mutate!]
  (fn [next-item] (mutate! :selected next-item)))

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
