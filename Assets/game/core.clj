(ns game.core
	(use 
		arcadia.core
		arcadia.linear
		hard.core
		hard.input
		tween.core))

(def pspeed 6)
(def intesity 1)

(defn handle-input [o]
	(if (key? "a") (rotate! o (v3 0 (- pspeed) 0)))
	(if (key? "d") (rotate! o (v3 0 pspeed 0))))

(defn ring-update [o]
	(local-scale! o (v3* (local-scale o) 1.05)))

(defn make-ring [pattern]
	(let [pattern (if (nil? pattern) [0 0 0 0 0 0] pattern)
				ring (clone! :empty)]
		(dorun
			(map-indexed 
				(fn [i s]
					(if (= s 1)
						(parent! 
							(rotate! (clone! :segment)
								(v3 0 (* i 60) 0))
							ring)))
				pattern))
		(hook+ ring :update #'game.core/ring-update)))

(defn start-game [o]
	(clear-cloned!)
	(clone! :camera)
	(clone! :sun)
	(let [player (clone! :player)]
		(hook+ player :update #'game.core/handle-input)))

(start-game nil)

(def level1
	[nil
	[1 0 1 0 1 0]
	nil
	[0 1 1 1 0 0]
	[1 0 1 0 1 0]])

(timeline* :loop
	(wait (* intesity 0.33))
	#(do (make-ring (rand-nth level1)) nil))

; (parent! (.transform (the sun)) (the camera))

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
