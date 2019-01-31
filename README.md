
Respo Inflow Popup
----

> Popup UI demo for Respo apps

Demo http://repo.respo-mvc.org/inflow-popup/

[![Clojars Project](https://img.shields.io/clojars/v/respo/inflow-popup.svg)](https://clojars.org/respo/inflow-popup)

```edn
[respo/inflow-popup "0.2.6"]
```

It's like local dropdown menu component in React.js .

```clojure
inflow-popup.comp.dialog/comp-dialog
inflow-popup.comp.dialog/comp-menu-dialog
inflow-popup.comp.dropdown/comp-dropdown
```

```clojure
(if (:show? state)
 (comp-dialog
  (fn [mutate!] (mutate! %cursor (update state :show? not)))
  (div {} (<> "Inside"))))
```

```clojure
(cursor-> :popup comp-popup states
  {:trigger (<> "Launch"),
   :style {:background-color (hsl 0 0 96), :padding "0 8px"}}
  (fn [toggle!]
    (div {}
     (<> "Inside")
     (=< 8 nil)
     (button
      {:style ui/button,
       :inner-text "Close",
       :on-click (fn [e d! m!] (toggle! m!))})))))
```

```clojure
(if (:show-menu? state)
     (comp-menu-dialog
      (fn [result d! m!]
        (println "result" result)
        (m! %cursor (update state :show-menu? not)))
      {:haskell "Haskell",
       :clojure "Clojure",
       :elixir (div
                {:style {}}
                (div {} (<> "Elixir"))
                (div {} (<> "...with an extra line")))})))
```

### Develop

https://github.com/mvc-works/calcit-workflow

### License

MIT
