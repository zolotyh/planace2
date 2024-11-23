(ns com.zolotyh.planace.poker.hx-api)

(defn mock-users []
  (->> (range 10)
       (map #(hash-map :id %1))
       (map #(merge % {:name (str (random-uuid))}))))

(defn votes [_]
  [:div "Votes"])

(defn tasks [_]
  [:div "Tasks"])

(defn cards [_]
  [:div "Cards"])
