(ns game.core
	(use 
		arcadia.core
		arcadia.linear))

(defn start-game [o]
	(create-primitive :sphere))

(start-game nil)