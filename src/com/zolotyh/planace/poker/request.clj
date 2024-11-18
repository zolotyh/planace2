(ns com.zolotyh.planace.poker.request)

(defn htmx? [{:keys [headers]}]
  (boolean (get headers "hx-request")))



