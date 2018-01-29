
(ns inflow-popup.comp.dialog
  (:require [respo.macros :refer [defcomp cursor-> <> div span]]
            [inflow-popup.style.layout :as layout]
            [inflow-popup.style.widget :as widget]
            [inflow-popup.style.decoration :as decoration]))

(defn on-focus [e dispatch!] )

(defcomp
 comp-dialog
 (on-close element-inside)
 (div
  {:style (merge layout/float-fullscreen decoration/dim layout/hold-center {:z-index 100}),
   :on-click (fn [e d! m!] (on-close m!))}
  (div {:on-click on-focus, :style widget/card} element-inside)))
