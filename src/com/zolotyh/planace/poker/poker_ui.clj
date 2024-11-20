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
  [:main.grid.is-col-min-4.px-20.is-column-gap-4.flex-grow
   [:div {:class "box aspect-[63/88] max-w-40"} "test"]
   [:div {:class "box aspect-[63/88] max-w-40"} "test"]
   [:div {:class "box aspect-[63/88] max-w-40"} "test"]
   [:div {:class "box aspect-[63/88] max-w-40"} "test"]])

(defn footer-template [_]
  [:footer.footer
   [:div.content.has-text-centered "test"]])

(defn header-template [_]
  [:header.hero
   [:div.hero-body
    [:h1 {:class "title"} "Planace"]
    [:p {:class "subtitle"} "Collaboratively estimate tasks with your team"]]])

(defn container [& content]
  [:html
   [:head
    (head/head {:title "hello"})]
   [:body.flex.flex-col.h-screen.justify-between.min-h-screen

    content]])

(defn layout [{:keys [header footer main]}]
  (container
   (header-template header)
   (main-template main)
   (footer-template footer)))
