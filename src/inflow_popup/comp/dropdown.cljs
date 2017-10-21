
(ns inflow-popup.comp.dropdown
  (:require-macros [respo.macros :refer [defcomp cursor-> <> div span style]])
  (:require [hsl.core :refer [hsl]]
            [respo.core :refer [create-comp]]
            [inflow-popup.style.layout :as layout]
            [inflow-popup.style.widget :as widget]))

(def style-icon {:width "32px", :font-size "10px"})

(def style-menu
  {:position "absolute",
   :right 0,
   :top "34px",
   :box-shadow (str "0 0 4px " (hsl 0 0 0 0.4)),
   :width "100%",
   :color (hsl 0 0 40)})

(def style-button
  {:height "32px",
   :line-height "32px",
   :background-color (hsl 200 80 60),
   :color (hsl 0 0 100),
   :align-items "stretch",
   :cursor "pointer",
   :font-size "14px",
   :position "relative"})

(def item-hover-style
  (style
   {:attrs {:innerHTML (str
                        ".dropdown-item:hover{"
                        "background-color:"
                        (hsl 0 0 60 0.1)
                        "}")}}))

(def style-divider {:width "1px", :background-color (hsl 0 0 100 0.5)})

(def style-text
  {:width "120px",
   :display "inline-block",
   :padding "0 8px",
   :text-align "center",
   :white-space "nowrap",
   :overflow "hidden",
   :text-overflow "ellipsis"})

(def style-item {:padding "0 8px"})

(defcomp
 comp-dropdown
 (states candidates current on-select)
 (let [state (if (some? (:data states)) (:data states) false)]
   (div
    {:style (merge layout/row style-button), :on {:click (fn [e d! m!] (m! (not state)))}}
    (<> span current (merge layout/flex style-text))
    (div {:style style-divider})
    (div
     {:style (merge layout/hold-center style-icon)}
     (span {:class-name "ion-arrow-down-b"}))
    (if state
      (div
       {:style style-menu}
       (->> candidates
            (map
             (fn [item]
               [item
                (div
                 {:class-name "dropdown-item",
                  :style style-item,
                  :on {:click (fn [e d! m!] (on-select item m!) (m! (not state)))}}
                 (<> item))])))))
    (if state item-hover-style))))
