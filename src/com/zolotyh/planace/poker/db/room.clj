(ns com.zolotyh.planace.poker.db.room
  (:require
   [clojure.string :as s]
   [com.biffweb :as biff]
   [xtdb.api :as xt]))

(defn add-user-vote [old-vote user-id value]
  (s/setval
   [:vote/result
    #_{:clj-kondo/ignore [:unresolved-var]}
    s/ALL
    (fn [v] (= user-id (:user v)))] {:user user-id :vote value} old-vote))

(defn create-new-vote [room vote]
  (merge
   vote
   {:vote/room (:xt/id room)
    :vote/result
    (reduce
     #(conj %1 {:user %2})
     []
     (:room/members room))}))

(def default-room-data {:room/default-vote-type :natural
                        :room/members []})

(def default-vote-data {:vote/open false
                        :vote/title "New Vote"
                        :vote/type (:room/default-vote-type default-room-data)})

(defn create-room [{:keys [session biff/db] :as ctx} room-data]
  (let [user (xt/entity db (:uid session))
        user-id (:uid session)
        vote-id (random-uuid)
        room-id (random-uuid)
        room-data (merge {:room/current-vote vote-id
                          :room/owner user-id
                          :room/members [user-id]
                          :xt/id room-id}
                         default-room-data
                         room-data)
        vote-data (create-new-vote room-data (merge default-vote-data {:xt/id vote-id}))
        rooms (:user/rooms user)]

    (biff/submit-tx ctx [(merge {:db/doc-type :room} room-data)
                         (merge {:db/doc-type :vote} vote-data)
                         (merge user {:db/doc-type :user,
                                      :db/op :update,
                                      :user/rooms (if (vector? rooms)  (conj rooms room-id) [room-id])})])
    {:uuid room-id}))
