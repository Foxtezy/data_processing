(defn is-correct-word [word]
  (not= (get word (- (count word) 1)) (get word (- (count word) 2))))


(defn concat-letter [words letter]
  (map (fn [word] (str word letter)) words))

(defn extend-words [words alphabet]
  (reduce
    (fn [extended-words letter] (concat extended-words (concat-letter words letter)))
    []
    alphabet))

(defn extend-correct-words [words alphabet]
  (filter is-correct-word (extend-words words alphabet)))

(defn create-language [length alphabet]
    (reduce (fn [words, _] (extend-correct-words words alphabet)) alphabet (range (- length 1))))

(create-language 3 ["a" "b" "c"])