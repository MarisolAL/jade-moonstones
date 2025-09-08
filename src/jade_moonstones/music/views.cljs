(ns jade-moonstones.music.views
  (:require
   [jade-moonstones.music-catalog :as music-cat]
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

(defn song-card [song]
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

(defn page []
  [:div
   [:h2 "🎶 Music findings~"] 
   (for [song music-cat/music-catalog]
     ^{:key (str (:title song) "-" (:name song))} 
     [song-card song])])

