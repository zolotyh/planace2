(ns com.zolotyh.planace.poker.ui.cards)

(defn card [value]
  (if (= value 3)
    [:div {:class "has-background-primary-25 has-text-primary-25-invert box aspect-[63/88] cursor-pointer max-w-23 text-4xl flex items-center justify-center font-extralight -translate-y-2"} value]
    [:div {:class "box aspect-[63/88] cursor-pointer max-w-23 text-4xl flex items-center justify-center font-extralight"} value]))

(defn cards []
  [:div {:class "grid 2xl:grid-cols-16 xl:grid-cols-16 md:grid-cols-12 sm:grid-cols-4 xs:grid-cols-1 gap-4 -translate-y-20 -mb-8"}
   (map card (range 16))])
