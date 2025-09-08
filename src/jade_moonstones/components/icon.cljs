(ns jade-moonstones.components.icon
  (:require
   ["@chakra-ui/icons" :refer [BsThreeDotsVertical SearchIcon]]
   ["@chakra-ui/react" :refer [Icon]]
   ["react-icons/md" :refer [MdSettings]]
   ["react-icons/pi" :refer [PiSpotifyLogoFill PiSpotifyLogo
                             PiYoutubeLogo PiYoutubeLogoFill]]
   [reagent.core :as rg] 
   ))

(def search-icon (rg/adapt-react-class SearchIcon))


(defn react-icon [{:keys [react-icon-name style]}]
  [:> Icon (merge {:as      react-icon-name 
                   :boxSize "6"
                   :color   "blue.500"}
                  style)])

(defn spotify-icon [{:keys [style fill]}]
  [:> Icon (merge {:as      (if fill
                              PiSpotifyLogoFill
                              PiSpotifyLogo)
                   :boxSize "6"
                   :color   "blue.500"}
                  style)])

(defn youtube-icon [{:keys [style fill]}]
  [:> Icon (merge {:as      (if fill
                              PiYoutubeLogoFill
                              PiYoutubeLogo)
                   :boxSize "6"
                   :color   "blue.500"}
                  style)])
