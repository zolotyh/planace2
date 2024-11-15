(ns com.zolotyh.planace.poker.app
  (:require
   [com.zolotyh.planace.middleware :as mid]
   [com.zolotyh.planace.poker.ui :refer [base]]))

(defn main [ctx]
  (base ctx [:b "main"]))

(defn room-list [ctx]
  (base ctx [:b "room-list"]))

(defn room-item [ctx]
  (base ctx [:b "room-item"]))

(defn game-list [ctx]
  (base ctx [:b "game-list"]))

(defn game-item [ctx]
  (base ctx [:b "game-item"]))

(def module
  {:routes ["/poker" {:middleware [mid/wrap-signed-in]}
            ["" {:get main}]
            ["/room"
             ["" {:get room-list}]
             ["/:id" {:get room-item}
              ["/game"
               ["" {:get game-list}]
               ["/:id" {:get game-item}]]]]]})

