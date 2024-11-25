(ns com.zolotyh.planace.poker.pages.main
  (:require
   [com.zolotyh.planace.poker.ui :as ui]
   [com.zolotyh.planace.poker.ui.room :refer [room-create-form]]))

(defn content [_]
  (ui/layout {:main "test"}))
