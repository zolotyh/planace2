(ns com.zolotyh.planace.poker.app
  (:require
   [reitit.core :as r]
   [com.zolotyh.planace.middleware :as mid]
   [com.zolotyh.planace.poker.poker-ui :as ui]
   [com.zolotyh.planace.poker.ui :as bui]))

(defn generate-items []
  (->> (range 10)
       (map (fn [id] {:id id
                      :name (str
                             (random-uuid))}))))

(defn reverse-url [{:keys [reitit.core/router path-params]} item]
  (merge {:url (r/match-by-name router ::room-item (merge path-params item))} item))

(defn dummy [ctx]
  (bui/htmx ctx [:p "game-item"]))

(defn poker [ctx]
  (bui/htmx ctx [:div {:hx-get "/poker/room" :hx-trigger "load"} (ui/loading)]))

(defn room-list [ctx]
  (bui/htmx ctx
            (ui/nav ctx
                    (map (partial reverse-url ctx) (generate-items)))))

(defn game-list [ctx]
  (bui/htmx ctx
            [:div
             [:div "game list"]
             (ui/nav ctx
                     (map (partial reverse-url ctx) (generate-items)))]))

(def module {:routes ["/poker" {:middleware [mid/wrap-signed-in]}
                      ["" {:get poker}]
                      ["/room"
                       ["" {:get room-list :name ::room-list}]
                       ["/:id"
                        ["" {:get game-list :name ::room-item}]
                        ["/game"
                         ["" {:get dummy :name ::game-list}]
                         ["/:game-id" {:get dummy :name ::game-item}]]]]]})

