(defn is-correct-word [word]
  (reduce
    (fn [flag1 i] (if flag1 (reduce
                                    (fn [flag2 j] (if flag2 (or (not= (get word i) (get word j)) (= i j)) flag2))
                                    true
                                    (range (count word))) flag1))
    true
    (range (count word))))

(defn concat-letter [words letter]
  (map (fn [word] (str letter word)) words))

(defn extend-words [words alphabet]
  (reduce
    (fn [extended-words letter] (concat extended-words (concat-letter words letter)))
    []
    alphabet))

(defn extend-correct-words [words alphabet]
  (filter is-correct-word (extend-words words alphabet)))

(defn create-language [length alphabet]
    (reduce (fn [words, _] (extend-correct-words words alphabet)) alphabet (range (- length 1))))