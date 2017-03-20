(ns game.core
	(use 
		arcadia.core
		arcadia.linear
		hard.core))

(defn handle-input [o]
	(log "handle-input"))

(defn start-game [o]
	(clear-cloned!)
	(clone! :camera)
	(clone! :sun)
	(let [player (clone! :player)]
		(hook+ player :update #'game.core/handle-input)))

(start-game nil)