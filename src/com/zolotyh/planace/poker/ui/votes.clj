(ns com.zolotyh.planace.poker.ui.votes
  (:require
   [com.zolotyh.planace.poker.ui.task-list :as tl]))

(defn vote-results []
  (->> (range 10)
       (map #(hash-map :id %1 :name (str (random-uuid)) :isActive (< (rand) 0.5)))))

(defn vote [{:keys [name isActive]}]
  [:span {:class (str "panel-block " (when (not isActive) "opacity-50"))}
   [:span.panel-icon
    [:i {:class (str  "fas fa-user fa-solid" (if isActive " has-text-primary" " test2"))}]]
   name])

(defn vote-result []
  [:<>
   [:h4.mb-0.text-xl.pb-0.leading-4.font-normal "Result"]
   [:p.leading-1
    (str "current result is: ")
    [:span.text-xl.has-text-warning 5]]])

(defn votes-widget []
  [:div.col-start-0.col-span-5
   [:div {:class "panel text-xs"}
    (map vote (vote-results))]
   [:br]
   (vote-result)])

(defn task-widget []
  [:div.col-start-6.col-span-6
   [:h2.text-2xl
    "Test"
    [:br]
    [:span.tag.is-primary "WTE-12345"]
    [:br]
    [:br]]
   [:p "Typescript - сложный язык, но большинству разработчиков не нужно знать все его тонкости, чтобы быть эффективными в своей работе.
      Это то, что я считаю минимальным набором знаний по тайпскрипту для эффективной разработки продукта на React.
      Основные принципы, которые мы здесь используем:
      Типизируйте входные данные, предсказывай результат
      Минимизируйте шум в кодовой базе
      Ошибки должны отображаться как можно ближе к коду, который их вызвал
"]
   [:br]
   (tl/task-list)])

(defn results []
  [:div.grid.grid-cols-12.gap-8
   (votes-widget)
   (task-widget)])

