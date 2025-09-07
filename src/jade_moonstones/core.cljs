(ns jade-moonstones.core
  (:require [jade-moonstones.components.hero-ui :as hero-ui]
            [jade-moonstones.events :as events]
            [jade-moonstones.subs :as subs]
            [reagent.core :as rg]
            [reagent.dom :as rd]
            [reagent.dom.client :as rdc]
            [re-frame.core :as rf]))

 
(defn home-page []
  [:div.main_container 
   [:h1 {:style {:color "hotpink"}} @(rf/subscribe [::subs/message])]
   #_[:div {:class "dark"}
    [hero-ui/button {:color "primary"} "Click me!"]
    [hero-ui/button {:color "secondary"} "Click me!"]]
   ])

(defonce root (delay (rdc/create-root (.getElementById js/document "app"))))

;; start is called by init and after code reloading finishes
(defn ^:dev/after-load start []
  (rdc/render @root [home-page]))

(defn init []
  ;; init is called ONCE when the page loads
  ;; this is called in the index.html and must be exported
  ;; so it is available even in :advanced release builds
  (js/console.log "init")
  (rf/dispatch-sync [::events/initialize-app])
  (start))

;; this is called before any code is reloaded
(defn ^:dev/before-load stop []
  (js/console.log "stop"))
