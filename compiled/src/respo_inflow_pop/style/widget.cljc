
(ns respo-inflow-pop.style.widget
  (:require [hsl.core :refer [hsl]]))

(def button
 {:line-height 2,
  :color (hsl 0 0 100),
  :font-size "14px",
  :background-color (hsl 200 80 60),
  :cursor "pointer",
  :padding "0 8px",
  :display "inline-block"})

(def card {:background-color (hsl 0 0 100), :padding "16px"})
