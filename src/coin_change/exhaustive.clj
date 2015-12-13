(ns coin-change.exhaustive
  (:use [multiset.core]
        [clojure.pprint]
        [clojure.tools.trace]))

(defn make-coin-machine [hash-of-coin-amounts] hash-of-coin-amounts)

(defn dec-coin [coin-machine amount]
  (into {}
        (filter
         (comp not zero? second)
         (update-in coin-machine [amount] dec))))

(defn find-matching-denoms
  "Given a coin machine and an amount return a list of amounts and coin machines with the amount deducted"
  [coin-machine amount]
  (if (zero? amount)
    []
    (for [[machine-amount coins] coin-machine
          :when (<= machine-amount amount)]
      [machine-amount (dec-coin coin-machine machine-amount)])))

(defn denest[xs] (mapcat #(if (multiset? %) [%] %) xs))

(defn change-for
  ([coin-machine amount]
   (set (change-for coin-machine amount (multiset))))
  ([coin-machine amount coins]
   (if (zero? amount)
     coins
     (denest
      (for [[coin-amount new-coin-machine] (find-matching-denoms coin-machine amount)]
        (change-for new-coin-machine (- amount coin-amount) (conj coins coin-amount)))))))



(comment

  (def test-machine (make-coin-machine {200 2, 100 2, 50 2, 20 3, 5 10}))
  (change-for test-machine 5)
  (change-for test-machine 60)

  )
