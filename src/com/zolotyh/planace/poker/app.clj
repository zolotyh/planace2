(ns com.zolotyh.planace.poker.app
  (:require
   [reitit.core :as r]
   [com.biffweb :refer [render]]
   [com.zolotyh.planace.middleware :as mid]
   [com.zolotyh.planace.poker.poker-ui :as ui]
   [com.zolotyh.planace.poker.ui :refer [base]]))

(defn poker [ctx]
  (base ctx [:div {:hx-boost "true" :hx-get "/poker/room" :hx-trigger "load"} (ui/loading)]))

(defn room-url-fn [{:keys [room-id reitit.core/router]}]
  (->
   (r/match-by-name router ::room-item {:room-id room-id})
   :path))

(defn game-url-fn [{:keys [game-id room-id reitit.core/router]}]
  (->
   (r/match-by-name router ::game-item {:room-id room-id
                                        :game-id game-id})
   :path))

(defn room-list [ctx]
  (render
   (ui/room-list
    (merge ctx
           {:items (range 10)
            :room-url-fn room-url-fn}))))

(defn game-list [ctx]
  (render
   (ui/game-list
    (merge ctx
           {:items (range 10)
            :game-url-fn game-url-fn}))))

(defn room-item [ctx]
  (render
   (ui/game-list
    (merge ctx
           {:items (range 10)
            :game-url-fn game-url-fn}))))

(defn game-item [ctx]
  (base ctx [:b "game-item"]))

(def module {:routes ["/poker" {:middleware [mid/wrap-signed-in]}
                      ["" {:get poker}]
                      ["/room"
                       ["" {:get room-list :name ::room-list}]
                       ["/:room-id"
                        ["" {:get room-item :name ::room-item}]
                        ["/game"
                         ["" {:get game-list :name ::game-list}]
                         ["/:game-id" {:get game-item :name ::game-item}]]]]]})
