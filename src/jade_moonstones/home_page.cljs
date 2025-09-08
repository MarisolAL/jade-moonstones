(ns jade-moonstones.home-page
  (:require
   ["@chakra-ui/react" :refer [ Container Flex Heading HStack Text]]
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
    [:> Flex {:direction :column
              :justify   :center
              :width     "85%"
              :align     :center
              :padding "10px"}
     [:> Text {:fontSize "18"}
      "Jade & Moonstones is my little corner to share hidden gems, music and visual artists I’ve discovered along the way. These are creators who may not be widely known, but whose work shines with authenticity, beauty, and soul."]
     [:> Text {:fontSize "18"}
      "The name comes from my love for jade and moonstones. They may not have the fame or market value of diamonds, but to me they’re just as precious, unique, subtle, and quietly radiant. The same way, the artists featured here may not be in the spotlight, but they carry a beauty worth noticing."]
     [:> Text {:fontSize "18"}
      "This space is dedicated to celebrating those overlooked treasures~ The songs that stay with you, the artworks that move you, and the creators who deserve to be seen ✨."]]
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
