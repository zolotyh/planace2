(ns com.zolotyh.planace.poker.poker-ui)

(defn room-list [{:keys [items room-url-fn] :as ctx}]
  [:ul
   (map (fn [e] [:li [:a {:href (room-url-fn (merge ctx {:room-id e}))} (str "комната" e)]]) items)])

(defn game-item [{:keys [path-params game-url-fn] :as ctx} item]
  (let [room-id (:room-id path-params)]
    [:li [:a {:href (game-url-fn
                     (merge ctx
                            {:game-id item
                             :room-id room-id}))}
          (str "game-item" item)]]))

(defn game-list [{:keys [items] :as ctx}]
  [:<>
   [:ul
    (map (partial game-item ctx) items)]])

(defn loading [] "loading")
