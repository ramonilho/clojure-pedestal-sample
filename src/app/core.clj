(ns app.core
  (:require [io.pedestal.http :as http]
            [environ.core :refer [env]]
            [app.heroes :as heroes]))

(defn hello-world [] {})

(def routes
  #{["/"
     :get hello-world
     :route-name :hello-world]

    ["/heroes/:hero"
     :get heroes/get-hero
     :route-name :get-hero]

    ["/heroes"
     :get heroes/get-heroes
     :route-name :get-heroes]})

(def service-map
  (->
   {::http/routes routes
    ::http/type :immutant
    ::http/host "0.0.0.0"
    ::http/join? false
    ::http/port (Integer. (or (env :port) 5000))}
   http/default-interceptors
   (update ::http/interceptors into [http/json-body])))

(defn -main [_]
  (-> service-map http/create-server http/start))

(comment
  (do
    (require 'app.core)
    (app.core/-main nil)))