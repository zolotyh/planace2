(ns com.zolotyh.planace.poker.ui.footer
  (:require
   [com.zolotyh.planace.poker.ui.cards :refer [cards]]))

(defn footer-template []
  [:footer.footer.h-20.px-12
   (cards)])
