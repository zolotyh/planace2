(ns com.zolotyh.planace.poker.poker-ui
  (:require
   [com.zolotyh.planace.poker.ui.head :as head]))

(defn nav-item [{:keys [name url]}]
  [:li [:a {:hx-get (:path url)
            :hx-target "#content"
            :hx-replace-url (:path url)
            :href (:path url)} name]])

(defn nav [ctx items]
  [:ul (map nav-item items)])

(defn loading [] "loading")

(def logo
  [:h1
   [:a {:href "/"} "Planace"]])

(defn main-template [_]
  [:main "main"])

(defn footer-template [_]
  [:footer "footer"])

(defn header-template [_]
  [:header logo])

(defn container [& content]
  [:html
   [:head
    (head/head {:title "title"})]
   [:body
    content]])

(defn layout [{:keys [header footer main]}]
  (container
   (header-template header)
   (main-template main)
   (footer-template footer)))
