(ns com.zolotyh.planace.poker.ui.room
  (:require
   [com.biffweb :as biff]))

(defn room-create-form []
  (biff/form {:hx-swap "innerHTML transition:true"
              :hx-target "#main"
              :hx-post "/app/room/create"
              :hx-indicator "#spinner"
              :class "col-span-12 rounded-sm border border-stroke bg-white px-5 pb-5 pt-7.5 shadow-default dark:border-strokedark dark:bg-boxdark sm:px-7.5 xl:col-span-8 text-slate-900"}
             [:label.block {:for "room"} "Room name"
              [:.h-1]
              [:.flex [:input.w-full#room {:type "text", :name "title", :value ""}]
               [:.w-3] [:button {:type "submit" :class "w-full cursor-pointer rounded-lg border border-primary bg-slate-900 p-4 font-medium text-white transition hover:bg-opacity-90"} "Create"]]]
             [:div#spinner.htmx-indicator "loading"]))
