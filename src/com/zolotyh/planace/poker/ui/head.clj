(ns com.zolotyh.planace.poker.ui.head
  (:require
   [com.zolotyh.planace.poker.ui.utils :as u]))

(def styles
  [:<>
   [:link {:rel "stylesheet", :href (u/static-path "/css/inter.css")}]
   [:link {:rel "stylesheet", :href (u/static-path "/css/bulma.min.css")}]
   [:link {:rel "stylesheet", :href (u/static-path "/css/main.css")}]])

(def scripts
  [:<>
   [:script {:src (u/static-path "/js/main.js")}]
   [:script {:src (u/static-path "/js/htmx.min.js")}]
   [:script {:src (u/static-path "/js/alpine.min.js")}]
   [:script {:src (u/static-path "/js/ws.js")}]])

(defn head [{:keys [title base-url meta-description]}]
  [:head
   [:meta {:charset "utf-8"}]
   [:meta {:name "viewport", :content "width=device-width, initial-scale=1"}]
   [:title title]
   styles
   scripts
   [:meta {:name "description", :content meta-description}]
   [:meta {:property "og:title", :content title}]
   [:meta {:property "og:type", :content "website"}]
   [:meta {:property "og:url", :content base-url}]
   [:meta {:property "og:image", :content "icon.png"}]
   [:meta {:property "og:image:alt", :content ""}]
   [:link {:rel "icon", :href "/favicon.ico", :sizes "any"}]
   [:link {:rel "icon", :href "/icon.svg", :type "image/svg+xml"}]
   [:link {:rel "apple-touch-icon", :href "icon.png"}]
   [:link {:rel "manifest", :href "site.webmanifest"}]
   [:meta {:name "theme-color", :content "#fafafa"}]])
