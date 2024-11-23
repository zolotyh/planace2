(ns com.zolotyh.planace.poker.url-utils
  (:require
   [reitit.core :as r]))

(defn reverse-url [{:keys [reitit.core/router path-params]} item]
  (merge {:url (r/match-by-name router ::room-item (merge path-params item))} item))
