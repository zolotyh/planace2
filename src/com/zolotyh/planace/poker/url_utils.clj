(ns com.zolotyh.planace.poker.url-utils
  (:require
   [clojure.string]
   [reitit.core :as r]))

(defn reverse-url [{:keys [reitit.core/router path-params]} item]
  (merge {:url (r/match-by-name router ::room-item (merge path-params item))} item))

(defn generate-public-id []
  (->>
   (range 16)
   (map (fn [_] (rand-int 9)))
   (clojure.string/join "")))

(defn beatify-public-id [id]
  (re-seq #".{1,4}" id))
