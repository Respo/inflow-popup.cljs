
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
