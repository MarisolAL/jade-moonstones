(ns jade-moonstones.music.views
  (:require
   ["@chakra-ui/icons" :refer [BsThreeDotsVertical SearchIcon]]
   ["@chakra-ui/react" :refer [Box Card CardBody CardFooter CardHeader Flex Heading IconButton Text Wrap WrapItem]]
   [jade-moonstones.components.colors :refer [colors]]
   [jade-moonstones.components.icon :as j-icon]
   [jade-moonstones.music.music-catalog :as music-cat]
   [reagent.core :as rg] 
   [re-frame.core :as rf]))

(defn youtube-embed
  "Returns the embeded track component of a song in Youtube"
  [song]
  (let [allow-map {:accelerometer      true
                   :autoplay           true
                   :clipboard-write    true
                   :gyroscope          true
                   :picture-in-picture true
                   :web-share          true}
        yt-link   (str "https://www.youtube.com/embed/" (:youtube-link song))]
    [:iframe {:width           560
              :height          315
              :src             yt-link
              :title           (:name song)
              :frameBorder     0
              :allow           allow-map
              :allowFullScreen true
              :referrerPolicy  "strict-origin-when-cross-origin"}]))

(defn spotify-embed
  "Returns the embeded track component of a song in Spotify"
  [song]
  (let [allow-map    {:autoplay           true
                      :clipboard-write    true
                      :fullScreen         true 
                      :picture-in-picture true}
        spotify-link (str "https://open.spotify.com/embed/track/" (:spotify-link song)
                          "?utm_source=generator")]
    [:iframe {:style           {:border-radius "12px"} 
              :src             spotify-link
              :width           560
              :height          315
              :frameBorder     0
              :allowFullScreen ""
              :allow           allow-map
              :loading         :lazy}]))

(defn song-card-1 [song]
  (let
      [show-yt?      (rg/atom false)
       show-spotify? (rg/atom false)]
    (fn [song]
      (let [has-yt?      (not (clojure.string/blank? (:youtube-link song)))
            has-spotify? (not (clojure.string/blank? (:spotify-link song)))]
        [:div {:style
               {:border "5px aliceblue"}}
         [:p (:name song)]
         [:p (:artist song)]
         (if has-yt?
           [:div
            [:button {:on-click #(swap! show-yt? not)} "Show YT!"]
            (if @show-yt?
              (youtube-embed song))])
         (if has-spotify?
           [:div
            [:button {:on-click #(swap! show-spotify? not)} "Show Spotify!"]
            [:div
             (if @show-spotify?
               (spotify-embed song))]])]))))

(defn song-card [{:keys [name artist youtube-link spotify-link] :as song}]
  [:> Card {:variant :filled
            :colorScheme "pink"
            :background-color "#F6E0CA"}
   [:> CardHeader
    [:> Flex {:spacing        4
              :justifyContent "space-around"}
     [:> Box
      [:> Heading name]
      [:> Text artist]]
     [:> Box
      (when spotify-link
        [:> IconButton {:variant     "ghost"
                        :colorScheme "green"
                        :aria-label  "Spotify"
                        :icon        (rg/as-element
                                      [j-icon/spotify-icon {:style {:color (:keppel colors)}}])
                        :on-click    #(println "something")}])
      (when youtube-link
        [:> IconButton {:variant     "ghost"
                        :colorScheme "green"
                        :aria-label  "Youtube"
                        :icon        (rg/as-element
                                      [j-icon/youtube-icon {:fill  true
                                                            :style {:color (:indian-red colors)}}])
                        :on-click    #(println "something")}])]]]])

(defn page []
  (let [song (music-cat/music-catalog 1)]
    [:div.music_ui_container
     [:> Heading "🎶 My special findings"]
     [:> Wrap {:spacing "30px"
               :justify "center"}
      (for [song music-cat/music-catalog]
        ^{:key (str (:title song) "-" (:name song))} 
        [:> WrapItem
         [song-card song]])]]))

