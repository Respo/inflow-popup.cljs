
(ns inflow-popup.comp.container
  (:require [hsl.core :refer [hsl]]
            [respo.core :refer [defcomp >> <> div input span button]]
            [respo.comp.inspect :refer [comp-inspect]]
            [inflow-popup.comp.dialog :refer [comp-dialog comp-menu-dialog]]
            [inflow-popup.comp.dropdown :refer [comp-dropdown]]
            [inflow-popup.style.widget :as widget]
            [inflow-popup.style.layout :as layout]
            [inflow-popup.style.typeset :as typeset]
            [inflow-popup.style.decoration :as decoration]
            [reel.comp.reel :refer [comp-reel]]
            [inflow-popup.comp.popup :refer [comp-popup]]
            [respo-ui.core :as ui]
            [respo.comp.space :refer [=<]]))

(def example-data ["Clojure" "PureScript" "Reason" "Elm" "Haskell"])

(def initial-state {:show? false, :selected (first example-data), :show-menu? false})

(defcomp
 comp-container
 (reel)
 (let [store (:store reel)
       states (:states store)
       state (or (:data states) initial-state)
       cursor []]
   (div
    {:style (merge widget/card typeset/page-default)}
    (div
     {:style (merge layout/row widget/card)}
     (div {:style layout/field-area} (<> "a dialog"))
     (div
      {:style widget/button, :on-click (fn [e d!] (d! cursor (update state :show? not)))}
      (<> "Toggle"))
     (if (:show? state)
       (comp-dialog (fn [d!] (d! cursor (update state :show? not))) (div {} (<> "Inside")))))
    (div
     {:style (merge layout/row widget/card)}
     (div {:style layout/field-area} (<> "a droplist"))
     (comp-dropdown
      (>> states :dropdown)
      example-data
      (:selected state)
      (fn [next-item d!] (d! cursor (assoc state :selected next-item)))))
    (div
     {:style (merge layout/row widget/card)}
     (div {:style layout/field-area} (<> "a menu dialog"))
     (div
      {:style widget/button,
       :on-click (fn [e d!] (d! cursor (update state :show-menu? not)))}
      (<> "Toggle"))
     (if (:show-menu? state)
       (comp-menu-dialog
        (fn [result d!]
          (println "result" result)
          (d! cursor (update state :show-menu? not)))
        {:haskell "Haskell",
         :clojure "Clojure",
         :elixir (div
                  {:style {}}
                  (div {} (<> "Elixir"))
                  (div {} (<> "...with an extra line")))})))
    (div
     {:style (merge layout/row widget/card)}
     (div {:style layout/field-area} (<> "Popup"))
     (comp-popup
      (>> states :popup)
      {:trigger (<> "Launch"), :style {:background-color (hsl 0 0 96), :padding "0 8px"}}
      (fn [toggle!]
        (div
         {}
         (<> "Inside")
         (=< 8 nil)
         (button
          {:style ui/button, :inner-text "Close", :on-click (fn [e d!] (toggle! d!))})))))
    (comp-inspect "state" state nil)
    (comp-reel (>> states :reel) reel {}))))

(defn on-close [mutate!] (fn [] (mutate! :show?)))
