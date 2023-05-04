(defn operation [f]
  (fn [& args]
    (fn [vars]
      (apply f (mapv #(% vars) args))
      )
    )
  )

(def add (operation +))
(def subtract (operation -))
(def multiply (operation *))
(defn div [a b]
  (/ (double a) b)
  )
(def divide (operation div))
(def negate (operation -))
(def exp (operation (fn [x] (Math/exp x))))
(def ln (operation (fn [x] (Math/log x))))

(defn constant [value]
  (fn [vars]
    value
    ))

(defn variable [name]
  (fn [vars]
    (vars name)
    )
  )

(def mapOp {'+ add,
            '- subtract,
            '* multiply,
            '/ divide,
            'negate negate,
            'exp exp,
            'ln ln}
  )

(defn parse [expression]
  (cond
    (number? expression) (constant expression)
    (seq? expression) (apply (mapOp (first expression)) (map parse (rest expression)))
    :else (variable (str expression))
    )
  )

(defn parseFunction [expression]
  (parse (read-string expression))
  )
