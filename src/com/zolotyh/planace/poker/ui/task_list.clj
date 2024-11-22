(ns com.zolotyh.planace.poker.ui.task-list)

(defn task-list []
  [:div.panel.text-xs
   [:a.panel-block {:href "test"}
    [:span.tag.is-dark.mr-2 "WTE-12345"] "Test"]
   [:a.panel-block {:href "test"}
    [:span.tag.is-dark.mr-2 "WTE-12345"] "Test"]
   [:a.panel-block {:href "test"}
    [:span.tag.is-dark.mr-2 "WTE-12345"] "Test"]])
