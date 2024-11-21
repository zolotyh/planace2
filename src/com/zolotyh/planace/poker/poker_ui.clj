(ns com.zolotyh.planace.poker.poker-ui
  (:require
   [com.zolotyh.planace.poker.ui.footer :refer [footer-template]]
   [com.zolotyh.planace.poker.ui.head :as head]
   [com.zolotyh.planace.poker.ui.header :refer [header-template]]
   [com.zolotyh.planace.poker.ui.votes :as v]))

(defn nav-item [{:keys [name url]}]
  [:li [:a {:hx-get (:path url)
            :hx-target "#content"
            :hx-replace-url (:path url)
            :href (:path url)} name]])

(defn nav [ctx items]
  [:ul (map nav-item items)])

(defn main-template [_]
  [:main.flex-grow.px-12.pb-40
   (v/results)])

(defn container [& content]
  [:html
   [:head
    (head/head {:title "hello"})]
   [:body.flex.flex-col.h-screen.justify-between.min-h-screen
    content]])

(defn layout [{:keys [header main]}]
  (container
   (header-template header)
   (main-template main)
   (footer-template)))
