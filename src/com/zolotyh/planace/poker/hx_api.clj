(ns com.zolotyh.planace.poker.hx-api)

(defn mock-users []
  (->> (range 10)
       (map #(hash-map :id %1))
       (map #(merge % {:name (str (random-uuid))}))))

(defn voters [ctx]
  [:div "Test"])
