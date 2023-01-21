(ns app.heroes)

(def heroes
  [{:name "Clark" :surname "Kent"  :hero "Superman"}
   {:name "Bruce" :surname "Wayne"  :hero "Batman"}
   {:name "James" :surname "Logan"  :hero "Wolverine"}
   {:name "Steve" :surname "Rogers" :hero "Captain America"}
   {:name "Bruce" :surname "Banner" :hero "Hulk"}])


(defn get-heroes [_]
  {:status 200 :body heroes})

(defn get-hero
  [{{:keys [hero]} :path-params
    {:keys [extended]} :query-params}]
  (if-let [hero (->> heroes
                     (filter #(= hero (:hero %)))
                     (first))]
    {:status 200 :body (if extended hero (dissoc hero :hero))}
    {:status 404}))