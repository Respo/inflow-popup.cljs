
(ns inflow-popup.comp.dialog
  (:require-macros [respo.macros :refer [defcomp cursor-> <> div span]])
  (:require [respo.core :refer [create-comp]]
            [inflow-popup.style.layout :as layout]
            [inflow-popup.style.widget :as widget]
            [inflow-popup.style.decoration :as decoration]))

(defn on-focus [e dispatch!] )

(defcomp
 comp-dialog
 (on-close element-inside)
 (div
  {:style (merge layout/float-fullscreen decoration/dim layout/hold-center {:z-index 100}),
   :event {:click (fn [e d! m!] (on-close m!))}}
  (div {:event {:click on-focus}, :style widget/card} element-inside)))
