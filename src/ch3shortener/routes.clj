(ns ch3shortener.routes
  (:require [ch3shortener.handler :as handler]
            [ch3shortener.middleware :as mw]
            [compojure.core :refer :all]
            [compojure.route :as route]))


(defroutes app-routes
  (GET "/" [] "Hello World")
  (route/not-found "Not Found"))