
;;Добавление след. числа после n в решето
(defn add-to-sieve [sieve n step]
  (let [m (+ n step)]
    (if (sieve m)
      (add-to-sieve sieve m step)
      (assoc sieve m step))))

;;Проверка числа n на простоту (лежит ли оно в решете),
;;если оно в решете, то достаем его оттуда. Затем добавляем в решето следущее за ним
(defn next-sieve [sieve n]
  (if-let [step (sieve n)] 
    (add-to-sieve (dissoc sieve n) n step)
    (add-to-sieve sieve n (+ n n))))

;;Основная функция
(defn next-primes [sieve n]
  (if (sieve n)
    (next-primes (next-sieve sieve n) (+ 2 n))
    (cons n 
      (lazy-seq (next-primes (next-sieve sieve n) (+ 2 n))))))

;;Последовательность
(defn primes []
  (concat [2] (next-primes {} 3)))

(use 'clojure.test)
(deftest primes-test
          (is (= (nth (primes) 0) 2))
          (is (= (nth (primes) 10) 31))
          (is (= (nth (primes) 100) 547))
          (is (= (nth (primes) 1000) 7927))
          (is (= (nth (primes) 10000) 104743)))