(defn p-filter-finite
  ([pred coll]
   (let [chunk-size (int (Math/ceil (Math/sqrt (count coll)))),
      parts (partition-all chunk-size coll)]
   (->> parts
        (map (fn [coll1]
               (future (doall (filter pred coll1)))))
        (doall)
        (mapcat deref)))))


(defn p-filter
  [pred coll]
  (mapcat (fn [c] (p-filter-finite pred c)) (partition-all 100 coll)))


(use 'clojure.test)
(deftest eg-tests
  (is (= (reduce + (p-filter odd? (range 0 101))) (reduce + (filter odd? (range 0 101)))))
  (is (= (reduce + (take 1000 (p-filter odd? (range)))) (reduce + (take 1000 (filter odd? (range))))))
  (is (= (reduce + (take 1001 (p-filter odd? (range)))) (reduce + (take 1001 (filter odd? (range)))))))


(defn long-odd [num]
  (Thread/sleep 1)
  (odd? num))

(time (->> (range 101)
           (p-filter long-odd)
           (reduce +)))
(time (->> (range 101)
           (filter long-odd)
           (reduce +)))


(time (reduce + (take 1001 (filter long-odd (range)))))
(time (reduce + (take 1001 (p-filter long-odd (range)))))




