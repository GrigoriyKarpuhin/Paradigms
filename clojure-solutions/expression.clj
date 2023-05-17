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
(defn div [a b] (/ (double a) b))
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

(def mapOp {'+      add,
            '-      subtract,
            '*      multiply,
            '/      divide,
            'negate negate,
            'exp    exp,
            'ln     ln}
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

;~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

(load-file "proto.clj")

(def getFirstArgument (field :firstArgument))
(def getSecondArgument (field :secondArgument))
(def getValue (field :value))
(def evaluate (method :evaluate))
(def toString (method :toString))
(def getOperator (field :operator))
(def getOpStr (field :strValOp))

(def binPrototype
  {:evaluate (fn [expr, vars] ((getOperator expr) (evaluate (getFirstArgument expr) vars) (evaluate (getSecondArgument expr) vars))),
   :toString (fn [expr] (str "(" (getOpStr expr) " " (toString (getFirstArgument expr)) " " (toString (getSecondArgument expr)) ")"))
   }
  )

(def unPrototype
  {:evaluate (fn [expr, vars] ((getOperator expr) (evaluate (getFirstArgument expr) vars))),
   :toString (fn [expr] (str "(" (getOpStr expr) " " (toString (getFirstArgument expr)) ")"))
   }
  )

(def constantObj {
                  :evaluate (fn [expr, vars] (getValue expr)),
                  :toString (fn [expr] (str (getValue expr)))
                  })

(defn Constant [value]
  (assoc constantObj :value value)
  )

(def variableObj {
                  :evaluate (fn [expr, vars] (vars (getValue expr))),
                  :toString (fn [expr] (str (getValue expr)))
                  })

(defn Variable [name]
  (assoc variableObj :value name)
  )

(defn Add [firstArgument secondArgument]
  (assoc binPrototype :firstArgument firstArgument :secondArgument secondArgument :operator + :strValOp "+")
  )

(defn Subtract [firstArgument secondArgument]
  (assoc binPrototype :firstArgument firstArgument :secondArgument secondArgument :operator - :strValOp "-")
  )

(defn Multiply [firstArgument secondArgument]
  (assoc binPrototype :firstArgument firstArgument :secondArgument secondArgument :operator * :strValOp "*")
  )

(defn Divide [firstArgument secondArgument]
  (assoc binPrototype :firstArgument firstArgument :secondArgument secondArgument :operator div :strValOp "/")
  )

(defn Negate [argument]
  (assoc unPrototype :firstArgument argument :operator - :strValOp "negate")
  )

(defn Sin [argument]
  (assoc unPrototype :firstArgument argument :operator (fn [x] (Math/sin x)) :strValOp "sin")
  )

(defn Cos [argument]
  (assoc unPrototype :firstArgument argument :operator (fn [x] (Math/cos x)) :strValOp "cos")
  )

(def mapObj {'+      Add,
             '-      Subtract,
             '*      Multiply,
             '/      Divide,
             'negate Negate,
             'sin    Sin,
             'cos    Cos
             }
  )

(defn parseObj [expression]
  (cond
    (number? expression) (Constant expression)
    (seq? expression) (apply (mapObj (first expression)) (map parseObj (rest expression)))
    :else (Variable (str expression))
    )
  )

(defn parseObject [expression]
  (parseObj (read-string expression))
  )

