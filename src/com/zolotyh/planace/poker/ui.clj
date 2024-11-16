(ns com.zolotyh.planace.poker.ui
  (:require
   [clojure.java.io :as io]
   [com.biffweb :as biff]
   [com.zolotyh.planace.settings :as settings]
   [ring.util.response :as ring-response]))

(defn css-path [path]
  (if-some [last-modified (some-> (io/resource "public/css/main.css")
                                  ring-response/resource-data
                                  :last-modified
                                  (.getTime))]
    (str path "?t=" last-modified)
    path))

(defn js-path [path]
  (if-some [last-modified (some-> (io/resource "public/js/main.js")
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
                            (concat [[:link {:rel "stylesheet" :href (css-path "/css/mvp.css")}]
                                     [:link {:rel "stylesheet" :href (css-path "/css/main.css")}]
                                     [:script {:src (js-path "/js/main.js")}]
                                     [:script {:src (js-path "/js/htmx.min.js")}]
                                     [:script {:src (js-path "/js/alpine.min.js")}]
                                     [:script {:src (js-path "/js/ws.js")}]
                                     (when recaptcha
                                       [:script {:src "https://www.google.com/recaptcha/api.js"
                                                 :async "async" :defer "defer"}])]
                                    head))))
   body))
