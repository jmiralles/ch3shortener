(ns ch3shortener.routes
  (:require [ch3shortener.handler :as handler]
            [ch3shortener.middleware :as mw]
            [compojure.core :refer :all]
            [compojure.route :as route]))

(defn shortener-routes
  [stg]
  (-> (routes
       (POST "/links/:id" [id :as request] (handler/create-link stg id request))
       (PUT "/links/:id" [id :as request] (handler/update-link stg id request))
       (DELETE "/links/:id" [id] (handler/delete-link stg id))
       (GET "/links/:id" [id] (handler/get-link stg id))
       (GET "/links" [] (handler/list-links stg))
       (route/not-found "Not Found"))
      (wrap-routes mw/wrap-slurp-body)))
