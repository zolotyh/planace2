(ns com.zolotyh.planace.poker.poker-ui)

(defn nav-item [{:keys [name url]}]
  [:li [:a {:hx-get (:path url)
            :hx-target "#content"
            :hx-replace-url (:path url)
            :href (:path url)} name]])

(defn nav [ctx items]
  [:ul (map nav-item items)])

(defn loading [] "loading")
