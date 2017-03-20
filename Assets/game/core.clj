(ns game.core
	(use 
		arcadia.core
		arcadia.linear
		hard.core))

(defn start-game [o]
	(clear-cloned!)
	(clone! :camera)
	(clone! :sun))

(start-game nil)