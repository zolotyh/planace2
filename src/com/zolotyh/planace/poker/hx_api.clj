(ns com.zolotyh.planace.poker.hx-api)

(defn mock-users []
  (->> (range 10)
       (map #(hash-map :id %1))
       (map #(merge % {:name (str (random-uuid))}))))

(defn vote-list [_]
  [:div "Votes"])

(defn task-list [_]
  [:div "Tasks"])

(defn card-list [_]
  [:div "Cards"])

(defn room-list []
  [:div "room-list"])
