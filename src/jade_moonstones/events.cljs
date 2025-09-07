(ns jade-moonstones.events
  (:require
   [re-frame.core :as rf]))

(rf/reg-event-fx
 ::initialize-app
 (fn [{db :db} _]
   {:db (assoc db :init true
               :message "Hello ✨ from Jade&Moonstones!")}))
