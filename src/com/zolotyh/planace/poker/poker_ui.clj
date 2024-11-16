(ns com.zolotyh.planace.poker.poker-ui)

(defn room-list [{:keys [items room-url-fn] :as ctx}]
  [:ul
   (map (fn [e] [:li [:a {:href (room-url-fn (merge ctx {:room-id e}))} (str "привет" e)]]) items)])

(defn loading [] "loading")
