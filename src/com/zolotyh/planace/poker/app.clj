(ns com.zolotyh.planace.poker.app
  (:require
   [com.zolotyh.planace.middleware :as mid]
   [com.zolotyh.planace.poker.hx-api :as hx]
   [com.zolotyh.planace.poker.ui :as ui]
   [com.zolotyh.planace.poker.ui.cards :as c]
   [com.zolotyh.planace.poker.ui.nav :as nav]
   [com.zolotyh.planace.poker.ui.votes :as v]
   [com.zolotyh.planace.poker.url-utils :as url]))

;TODO: room creation form
;TODO: generate-random-numbers for public id for room / check if those number are not equal to previous 
;TODO: vote process
;TODO: update votes by ws

(defn generate-items []
  (->> (range 10)
       (map (fn [id] {:id id
                      :name (str
                             (random-uuid))}))))

(defn room-list [_]
  (ui/layout {:header "header"
              :footer (c/cards)
              :main (v/results)}))

(defn game-item [_]
  (ui/layout {:header "header"
              :footer (c/cards)
              :main (v/results)}))

(defn game-list [ctx]
  (ui/htmx ctx
           [:div
            [:div "game list"]
            (nav/nav ctx
                     (map (partial url/reverse-url ctx) (generate-items)))]))

(defn poker [_]
  (ui/layout {:header "header" :footer "footer" :main "main"}))

(def module {:routes ["/poker" {:middleware [mid/wrap-signed-in]}
                      ["" {:get poker}]
                      ["/room"
                       ["" {:get room-list :name ::room-list}]
                       ["/:id"
                        ["" {:get game-list :name ::room-item}]
                        ["/votes" {:get hx/vote-list}]
                        ["/cards" {:get hx/card-list}]
                        ["/tasks" {:get hx/task-list}]
                        ["/game"
                         ["" {:get game-list :name ::game-list}]
                         ["/:game-id" {:get game-item :name ::game-item}]]]]]})

