(ns ch3shortener.handler
  (:require [ring.util.request :as req]
            [ring.util.response :as res]
            [ring.middleware.json :refer [wrap-json-response]]
            [ch3shortener.storage :as st]))


(defn get-link
  [stg id]
  (if-let [url (st/get-link stg id)]
    (res/redirect url)
    (res/not-found "Sorry, that link doesn't exist.")))

(defn create-link
  [stg id {url :body}]
  (if (st/create-link stg id url)
    (res/response (str "/links/" id))
    (-> (format "The id %s is already in use." id)
        res/response
        (res/status 422))))

(defn update-link
  [stg id {url :body}]
  (if (st/update-link stg id url)
    (res/response (str "/links/" id))
    (-> (format "There is no link with the id %s." id)
        res/not-found)))

(defn delete-link
  [stg id]
  (st/delete-link stg id)
  (-> (res/response "")
      (res/status 204)))

(defn list-links
  "Returns a handler! Call the handler if you want a response."
  [stg]
  (wrap-json-response
   (fn [_] ;; we don't need the request at this point
     (res/response (st/list-links stg)))))