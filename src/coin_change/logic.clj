(ns coin-change.logic
  (:use
   loco.core
   loco.constraints))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; Coin machine
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(def desired-change 60)

(def change-equal-constraint
  ($= desired-change
      ($+ ($* 50 :fifties)
          ($* 20 :twenties)
          ($* 10 :tens)
          ($* 5 :fives))))

(solutions [($in :fifties 0 1)
            ($in :twenties 0 3)
            ($in :tens 0 0)
            ($in :fives 0 9)
            change-equal-constraint]
           :minimize ($+ :fifties :twenties :tens :fives))
