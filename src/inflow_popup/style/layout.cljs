
(ns inflow-popup.style.layout (:require [hsl.core :refer [hsl]]))

(def column
  {:display "flex",
   :flex-direction "column",
   :align-items "flex-start",
   :justify-content "flex-start"})

(def field-area {:width "240px", :color (hsl 0 0 40)})

(def flex {:flex 1})

(def hold-center
  {:display "flex",
   :flex-direction "column",
   :justify-content "center",
   :align-items "center",
   :overflow "auto"})

(def float-fullscreen {:position "fixed", :width "100%", :height "100%", :top 0, :left 0})

(def row
  {:display "flex",
   :flex-direction "row",
   :align-items "flex-start",
   :justify-content "flex-start"})
