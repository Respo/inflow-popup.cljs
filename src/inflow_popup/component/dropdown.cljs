
(ns inflow-popup.component.dropdown
  (:require [hsl.core :refer [hsl]]
            [respo.alias :refer [create-comp div span style]]
            [respo.comp.text :refer [comp-text]]
            [inflow-popup.style.layout :as layout]
            [inflow-popup.style.widget :as widget]))

(defn on-toggle [mutate!] (fn [e dispatch!] (mutate!)))

(defn on-item-click [on-select item on-toggle]
  (fn [e dispatch!] (on-select item) (on-toggle)))

(def style-icon {:font-size "10px", :width "32px"})

(defn update-state [state] (not state))

(def style-menu
 {:box-shadow (str "0 0 4px " (hsl 0 0 0 0.4)),
  :color (hsl 0 0 40),
  :top "34px",
  :width "100%",
  :right 0,
  :position "absolute"})

(def style-button
 {:line-height "32px",
  :align-items "stretch",
  :color (hsl 0 0 100),
  :font-size "14px",
  :background-color (hsl 200 80 60),
  :cursor "pointer",
  :position "relative",
  :height "32px"})

(def item-hover-style
 (style
   {:attrs
    {:innerHTML
     (str
       ".dropdown-item:hover{"
       "background-color:"
       (hsl 0 0 60 0.1)
       "}")}}))

(defn init-state [& args] false)

(def style-divider {:background-color (hsl 0 0 100 0.5), :width "1px"})

(def style-text
 {:text-overflow "ellipsis",
  :text-align "center",
  :white-space "nowrap",
  :overflow "hidden",
  :width "120px",
  :padding "0 8px",
  :display "inline-block"})

(def style-item {:padding "0 8px"})

(defn render [candidates current on-select]
  (fn [state mutate!]
    (div
      {:style (merge layout/row style-button),
       :event {:click (on-toggle mutate!)}}
      (comp-text current (merge layout/flex style-text))
      (div {:style style-divider})
      (div
        {:style (merge layout/hold-center style-icon)}
        (span {:attrs {:class-name "ion-arrow-down-b"}}))
      (if state
        (div
          {:style style-menu}
          (->>
            candidates
            (map
              (fn [item] [item
                          (div
                            {:style style-item,
                             :event
                             {:click
                              (on-item-click
                                on-select
                                item
                                (on-toggle mutate!))},
                             :attrs {:class-name "dropdown-item"}}
                            (comp-text item nil))])))))
      (if state item-hover-style))))

(def comp-dropdown
 (create-comp :dropdown init-state update-state render))
