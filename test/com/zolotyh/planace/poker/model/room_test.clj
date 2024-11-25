(ns com.zolotyh.planace.poker.model.room-test
  (:require
   [clojure.test :refer [deftest is]]
   [com.zolotyh.planace.poker.model.room :as r]))

(deftest room-for-db
  (= (r/room-for-db {:room-id "room-id" :user-id "user-id" :vote-id "vote-id" :title "Hello"})
     {:xt/id "room-id",
      :room/current-vote "vote-id"
      :room/owner "user-id",
      :room/members ["user-id"],
      :room/title "Hello",
      :room/default-vote-type :natural}))

(deftest vote->with-new-value
  (is
   (= {:vote/result
       [{:user 1 :vote 2}]}
      (r/vote->with-new-value {:vote/result [{:user 1 :vote 3}]} 1 2))
   "should change value for single user"))

(deftest vote->with-new-value-multi
  (is
   (= {:vote/result
       [{:user 1 :vote 2}
        {:user 2 :vote 3}]}
      (r/vote->with-new-value
       {:vote/result [{:user 1 :vote 2}
                      {:user 2 :vote 10}]}
       2 3))
   "should change value for single user"))
