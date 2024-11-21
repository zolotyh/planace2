(ns com.zolotyh.planace.poker.ui.header)

(defn header-template [_]
  [:header.hero
   [:div.hero-body
    [:h1 {:class "text-2xl mb-0 font-normal"} "Planace"]
    [:p {:class "text-xs font-extralight opacity-80"} "Collaboratively estimate tasks with your team"]]])
