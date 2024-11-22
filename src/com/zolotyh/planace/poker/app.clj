(ns com.zolotyh.planace.poker.app
  (:require
   [com.zolotyh.planace.middleware :as mid]
   [com.zolotyh.planace.poker.hx-api :as hx]
   [com.zolotyh.planace.poker.ui :as ui]
   [com.zolotyh.planace.poker.ui.nav :as nav]
   [reitit.core :as r]))

(defn generate-items []
  (->> (range 10)
       (map (fn [id] {:id id
                      :name (str
                             (random-uuid))}))))

(defn reverse-url [{:keys [reitit.core/router path-params]} item]
  (merge {:url (r/match-by-name router ::room-item (merge path-params item))} item))

(defn dummy [ctx]
  (ui/htmx ctx [:p "game-item"]))

(defn room-list [ctx]
  (ui/htmx ctx
           (nav/nav ctx
                    (map (partial reverse-url ctx) (generate-items)))))

(defn game-list [ctx]
  (ui/htmx ctx
           [:div
            [:div "game list"]
            (nav/nav ctx
                     (map (partial reverse-url ctx) (generate-items)))]))

(defn poker [_]
  (ui/layout {:header "header" :footer "footer" :main "main"}))

(def module {:routes ["/poker" {:middleware [mid/wrap-signed-in]}
                      ["" {:get poker}]
                      ["/room"
                       ["" {:get room-list :name ::room-list}]
                       ["/:id"
                        ["" {:get game-list :name ::room-item}]
                        ["/voters" {:get hx/voters}]
                        ["/game"
                         ["" {:get dummy :name ::game-list}]
                         ["/:game-id" {:get dummy :name ::game-item}]]]]]})

