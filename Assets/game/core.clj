(ns game.core
	(use 
		arcadia.core
		arcadia.linear
		hard.core
		hard.input))


(defn handle-input [o]
	(if (key? "a") (log "left")))

(defn start-game [o]
	(clear-cloned!)
	(clone! :camera)
	(clone! :sun)
	(let [player (clone! :player)]
		(hook+ player :update #'game.core/handle-input)))

(start-game nil)