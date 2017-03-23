(ns game.core
	(use 
		arcadia.core
		arcadia.linear
		hard.core
		hard.input))

(def pspeed 6)

(defn handle-input [o]
	(if (key? "a") (rotate! o (v3 0 (- pspeed) 0)))
	(if (key? "d") (rotate! o (v3 0 pspeed 0))))

(defn make-ring [pattern]
	(map-indexed 
		(fn [i s]
			(if (= s 1)
				(rotate! (clone! :segment)
					(v3 0 (* i 60) 0))))
		pattern))

(defn start-game [o]
	(clear-cloned!)
	(clone! :camera)
	(clone! :sun)
	(let [player (clone! :player)]
		(hook+ player :update #'game.core/handle-input)))

(start-game nil)

(clone! :segment)

(make-ring [1 0 1 0 1 0])