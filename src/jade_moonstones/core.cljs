(ns jade-moonstones.core
  (:require
   [jade-moonstones.components.chakra :refer [ChakraProvider]]
   [jade-moonstones.events :as events]
   [jade-moonstones.home-page :as home-page]  
   [reagent.core :as rg] 
   [reagent.dom.client :as rdc]
   [re-frame.core :as rf]))

(defn main-page []
  (let []
    [ChakraProvider
     [home-page/page]]))

(defonce root (delay (rdc/create-root (.getElementById js/document "app"))))

;; start is called by init and after code reloading finishes
(defn ^:dev/after-load start []
  (rdc/render @root [main-page]))

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
