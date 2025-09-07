(ns jade-moonstones.home-page
  (:require
   ["@chakra-ui/react" :refer [Container Divider Heading HStack Text]]
   [jade-moonstones.components.chakra :refer [separator]]
   [jade-moonstones.events :as events]
   [jade-moonstones.music.views :as music.views]
   [jade-moonstones.subs :as subs]
   [jade-moonstones.visual-artist.views :as va.views]
   [reagent.core :as rg]
   [re-frame.core :as rf]))

(defn page []
  [:div.main_container
   [:> Container {:fluid         "True" 
                  :width         "100%"
                  :max-width     "100%"
                  :centerContent true}
    [:> Heading {:colorScheme "pink"
                 :color       "pink"}  
     @(rf/subscribe [::subs/message])]
    [separator {:text "🍀"}]
    [:> Container {:fluid         "true" 
                   :width         "100%"
                   :max-width     "100%"
                   :centerContent true}
     [:> Heading {:mb 4 :color "teal.400"} "Music section"]
     [music.views/page]] 
    [separator {:text "🍀"}]
    [:> Container {:fluid         "true" 
                   :width         "100%"
                   :max-width     "100%"
                   :centerContent true}
     [:> Heading {:mb 4 :color "purple.400"} "Visuals section"]
     [va.views/page]]]])
