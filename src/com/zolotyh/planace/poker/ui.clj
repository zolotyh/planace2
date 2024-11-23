(ns com.zolotyh.planace.poker.ui
  (:require
   [clojure.java.io :as io]
   [com.biffweb :as biff]
   [com.zolotyh.planace.poker.request :as request]
   [com.zolotyh.planace.settings :as settings]
   [ring.util.response :as ring-response]
   [com.zolotyh.planace.poker.ui.footer :refer [footer-template]]
   [com.zolotyh.planace.poker.ui.head :as head]
   [com.zolotyh.planace.poker.ui.header :refer [header-template]]
   [com.zolotyh.planace.poker.ui.votes :as v]))

(defn static [path]
  (if-some [last-modified (some-> (io/resource (str "public" path))
                                  ring-response/resource-data
                                  :last-modified
                                  (.getTime))]
    (str path "?t=" last-modified)
    path))

(defn base [{:keys [::recaptcha] :as ctx} & body]
  (apply
   biff/base-html
   (-> ctx
       (merge #:base{:title settings/app-name
                     :lang "en-US"
                     :icon "/img/glider.png"
                     :description (str settings/app-name " Description")
                     :image "https://clojure.org/images/clojure-logo-120b.png"})
       (update :base/head (fn [head]
                            (concat [[:link {:rel "stylesheet" :href (static "/css/water.css")}]
                                     [:link {:rel "stylesheet" :href (static "/css/main.css")}]
                                     [:script {:src (static "/js/main.js")}]
                                     [:script {:src (static "/js/htmx.min.js")}]
                                     [:script {:src (static "/js/alpine.min.js")}]
                                     [:script {:src (static "/js/ws.js")}]
                                     (when recaptcha
                                       [:script {:src "https://www.google.com/recaptcha/api.js"
                                                 :async "async" :defer "defer"}])]
                                    head))))
   body))

(defn with-header [ctx & body]
  (base ctx
        [:<>
         [:h1 "Planace"]
         [:input {:type "text"}]
         [:div {:hx-boost "true" :id "content" :hx-swap "innerHTML"}
          body]]))

(defn htmx [ctx & body]
  (if (request/htmx? ctx) (biff/render [:div {:id "content"} body]) (with-header ctx body)))

(defn main-template [main]
  [:main.flex-grow.px-12.pb-40
   main])

(defn container [& content]
  [:html
   [:head
    (head/head {:title "hello"})]
   [:body.flex.flex-col.h-screen.justify-between.min-h-screen
    content]])

(defn layout [{:keys [header main footer]}]
  (container
   (header-template header)
   (main-template main)
   (footer-template footer)))
