
(ns inflow-popup.style.layout
  (:require [hsl.core :refer [hsl]]))

(def hold-center
 {:align-items "center",
  :overflow "auto",
  :justify-content "center",
  :display "flex",
  :flex-direction "column"})

(def field-area {:color (hsl 0 0 40), :width "240px"})

(def column
 {:align-items "flex-start",
  :justify-content "flex-start",
  :display "flex",
  :flex-direction "column"})

(def row
 {:align-items "flex-start",
  :justify-content "flex-start",
  :display "flex",
  :flex-direction "row"})

(def float-fullscreen
 {:top 0, :width "100%", :position "fixed", :height "100%", :left 0})

(def flex {:flex 1})
