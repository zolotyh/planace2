(ns com.zolotyh.planace.poker.model.room
  (:require
   [com.biffweb :as biff]
   [com.rpl.specter :as s]
   [com.zolotyh.planace.poker.model.user :as u]))

(defn user->with-new-room [user room-id]
  (let [rooms (:user/rooms user)]
    (merge user {:db/doc-type :user,
                 :db/op :update,
                 :user/rooms (if (vector? rooms)
                               (distinct (conj rooms room-id))
                               [room-id])})))

(defn vote->with-new-value [old-vote user-id value]
  (s/setval
   [:vote/result
    #_{:clj-kondo/ignore [:unresolved-var]}
    s/ALL
    (fn [v] (= user-id (:user v)))] {:user user-id :vote value} old-vote))

(defn room-for-db [{:keys [room-id vote-id user-id title]}]
  {:xt/id room-id
   :room/current-vote vote-id
   :room/owner user-id
   :room/members [user-id]
   :room/title title
   :room/default-vote-type :natural})

(defn vote-for-db [{:keys [vote-id room-id user-id]}]
  {:xt/id vote-id
   :vote/room room-id
   :vote/open false
   :vote/type :natural
   :vote/title "New"
   :vote/result [{:user user-id}]})

(defn create-room [{:keys [params] :as ctx}]
  (let [vote-id (random-uuid)
        room-id (random-uuid)
        user (u/get-user-from-context ctx)
        user-id (u/get-id-from-context ctx)
        title (:title params)]

    (biff/submit-tx ctx [(room-for-db {:room-id room-id
                                       :vote-id vote-id
                                       :user-id user-id
                                       :title title})
                         (vote-for-db {:room-id room-id
                                       :vote-id vote-id
                                       :user-id user-id})
                         (user->with-new-room user room-id)])))

;     (biff/submit-tx ctx [(merge {:db/doc-type :room} room-data)
;                          (merge {:db/doc-type :vote} vote-data)
;                          (merge user {:db/doc-type :user,
;                                       :db/op :update,
;                                       :user/rooms (if (vector? rooms)  (conj rooms room-id) [room-id])})])
     ; (room-for-db {:vote-id vote-id}
     ;              :room-id room-id
     ;              :user-id user-id
     ;              :title title)))

; (defn on-room-create [{:keys [params] :as ctx}]
;   (let [room (db/create-room ctx {:room/title (:title params)})
;         redirect-path (str "http://localhost:8080/app/room/view/" (:uuid room))]
;     {:status 200
;      :headers {"HX-Location" (cheshire/generate-string {:path redirect-path :swap "innerHTML swap:0.1s settle:0.3s transition:true" :targer "#root"})
;                ; "hx-location" redirect-path
;                "location" redirect-path}}))
; (defn default-room-data [{:keys [room-id vote-id user-id]}]
;   {:xt/id room-id
;    :room/default-vote-type :natural
;    :room/members [user-id]
;    :room/owner user-id
;    :room/current-vote vote-id})

; :room/id :uuid
   ; :room [:map {:closed true}
   ;        [:xt/id :room/id]
   ;        [:room/title :string]
   ;        [:room/current-vote :vote/id]
   ;        [:room/owner :user/id]
   ;        [:room/default-vote-type :vote/vote-type]
   ;        [:room/members [:vector :user/id]]]})

; (create-room
  ;  (with-dump-session (get-context))
  ;  {:room/title "Hello"
  ;   :room/current-vote #uuid "173d0f48-f9ef-4e50-8f9b-63ccc71ec016"
  ;   :room/owner #uuid "282df800-7c52-425c-8242-2ba4d5aa8ec7"
  ;   :room/members [#uuid "282df800-7c52-425c-8242-2ba4d5aa8ec7"]}))

; (defn add-user-vote [old-vote user-id value]
;   (spec/set-val
;    [:vote/result
;     #_{:clj-kondo/ignore [:unresolved-var]}
;     s/ALL
;     (fn [v] (= user-id (:user v)))] {:user user-id :vote value} old-vote))
;
; (defn create-new-vote [room vote]
;   (merge
;    vote
;    {:vote/room (:xt/id room)
;     :vote/result
;     (reduce
;      #(conj %1 {:user %2})
;      []
;      (:room/members room))}))
;
; (def default-room-data {:room/default-vote-type :natural
;                         :room/members []})
;
; (def default-vote-data {:vote/open false
;                         :vote/title "New Vote"
;                         :vote/type (:room/default-vote-type default-room-data)})
;
; (defn create-room [{:keys [session biff/db] :as ctx} room-data]
;   (let [user (xt/entity db (:uid session))
;         user-id (:uid session)
;         vote-id (random-uuid)
;         room-id (random-uuid)
;         room-data (merge {:room/current-vote vote-id
;                           :room/owner user-id
;                           :room/members [user-id]
;                           :xt/id room-id}
;                          default-room-data
;                          room-data)
;         vote-data (create-new-vote room-data (merge default-vote-data {:xt/id vote-id}))
;         rooms (:user/rooms user)]
;
;     (biff/submit-tx ctx [(merge {:db/doc-type :room} room-data)
;                          (merge {:db/doc-type :vote} vote-data)
;                          (merge user {:db/doc-type :user,
;                                       :db/op :update,
;                                       :user/rooms (if (vector? rooms)  (conj rooms room-id) [room-id])})])
;     {:uuid room-id}))
