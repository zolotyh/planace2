(ns com.zolotyh.planace.poker.model.user
  (:require
   [xtdb.api :as xt]))

(defn get-id-from-context [{:keys [session]}]
  (:uid session))

(defn get-user-from-context [{:keys [session biff/db]}]
  (xt/entity db (get-id-from-context {:session session})))

