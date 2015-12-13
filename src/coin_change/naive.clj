(ns coin-change.naive)

(defn find-highest-denom[machine amount]
  (first (filter (fn[[denom coin-count]]
                   (and (not (zero? coin-count))
                        (>= (- amount denom) 0))) machine)))

(defn coin-changer
  ([machine amount] (coin-changer machine amount []))
  ([machine amount change]
   (let [[denom coin-count] (find-highest-denom machine amount)]
     (if (or (<= amount 0)
             (not denom))
       [change machine]
       (recur (assoc machine denom (dec coin-count))
              (- amount denom)
              (conj change denom)
              )))))
