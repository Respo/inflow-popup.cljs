
(ns inflow-popup.comp.popup
  (:require [hsl.core :refer [hsl]]
            [respo.core :refer [defcomp >> list-> <> div span style]]
            [inflow-popup.style.layout :as layout]
            [inflow-popup.style.widget :as widget]
            [inflow-popup.style.decoration :as decoration]))

(defcomp
 comp-popup
 (states options renderer)
 (let [state (or (:data states) {:show? false})
       cursor (:cursor states)
       toggle! (fn [d!] (d! cursor (update state :show? not)))
       trigger (:trigger options)
       user-style (:style options)
       on-popup (:on-popup options)]
   (div
    {:style (merge {:cursor :pointer} user-style),
     :on-click (fn [e d!] (toggle! d!) (if (fn? on-popup) (on-popup e d!)))}
    trigger
    (if (:show? state)
      (div
       {:style (merge
                layout/float-fullscreen
                decoration/dim
                layout/hold-center
                {:z-index 100, :cursor :default}),
        :on-click (fn [e d!] (toggle! d!))}
       (div {:style (merge widget/card), :on-click (fn [e d!] )} (renderer toggle!)))))))
