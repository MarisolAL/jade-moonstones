(ns jade-moonstones.components.chakra
  (:require 
   ["@chakra-ui/react" :as chakra]
    ["@chakra-ui/react" :refer [Text]]
   [reagent.core :as rg] ))

(def ChakraProvider (rg/adapt-react-class (.-ChakraProvider chakra)))

(defn separator [{:keys [text style]}]
  (let [_ (print text)]
    [:div.separator_container {:style style}
     (when text
       [:div
        [:> Text {:class "separator_text_container"} text]])]))
