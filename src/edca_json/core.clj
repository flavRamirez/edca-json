(ns edca-json.core
  (:require [clojure.math.combinatorics :refer :all]
            [clojure.set :refer :all]
            [clojure.string :as str]
            [clj-fuzzy.metrics :as metrics]
            [me.raynes.conch :refer :all]))

(programs ls cp rm)

(defn ls&
  [& dirs]
  (ls (str/join "/" dirs) {:seq true}))

(def data      (ls& "data"))
;(def data-cdn  (ls& "/Users/nex/Desktop/gacm"))
(def jsons     (ls& "json"))
(def jsons-ids (map #(str/replace % ".json" "")
                    jsons))

(def la-differance (difference (set jsons-ids) (set data)))
(def la-differance2 (difference (set data) (set jsons-ids)))

(defn cp-json [id]
  (cp (str "json/" id ".json")
      (str "data/" id)))

(defn cp-jsons []
  (map cp-json jsons-ids))


(defn most-similar-string [s S]
  (first (sort-by (partial metrics/levenshtein s)
                  S)))

(defn pairing-object [s1 s2]
  {:a s1 :b s2 :dist (metrics/levenshtein s1 s2)})

(defn pair-similar-strings [X Y]
  (zipmap X
          (map #(most-similar-string % Y)
               X)))

(defn pair-all [X Y]
  (let [all (sort-by :dist
                     (map (partial apply pairing-object)
                          (cartesian-product X Y)))]
    (loop [out []
           in  all]
      (if (empty? in)
        out
        (let [out (conj out (first in))
              a0  (:a (first in))
              b0  (:b (first in))
              in  (remove #(or (= a0 (:a %))
                               (= b0 (:b %)))
                          (rest in))]
          (recur out in))))))

(def pairing (pair-all la-differance la-differance2))
;;(def pairing (pair-similar-strings la-differance la-differance2))
