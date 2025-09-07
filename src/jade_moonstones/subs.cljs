(ns jade-moonstones.subs
  (:require
   [re-frame.core :as rf]))

(rf/reg-sub
 ::message
 (fn [db _] (:message db)))
