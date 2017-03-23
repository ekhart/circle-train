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
	(let [ring (clone! :empty)]
		(dorun
			(map-indexed 
				(fn [i s]
					(if (= s 1)
						(parent! 
							(rotate! (clone! :segment)
								(v3 0 (* i 60) 0))
							ring)))
				pattern))))

(defn start-game [o]
	(clear-cloned!)
	(clone! :camera)
	(clone! :sun)
	(let [player (clone! :player)]
		(hook+ player :update #'game.core/handle-input))
	(make-ring [1 0 1 0 1 0]))

(start-game nil)


; (clone! :segment)

; (rotate! (clone! :segment) (v3 0 (* 0 60) 0))
; (rotate! (clone! :segment) (v3 0 (* 1 60) 0))
; (log "hej")
; (log [1 0 1 0 1 0])
; (log (str 0 " " 1))
; (log map-indexed)
; ; map-indexed is Lazy - dorun?
; (log (map-indexed #(log (str %1 " " %2)) [1 0 1 0 1 0]))
; ; now its working!
; (dorun (map-indexed #(log (str %1 " " %2)) [1 0 1 0 1 0]))
