(defn operation [f]
  (fn [& args]
    (apply mapv f args)))

(defn opArr [f arr val]
  (mapv #(f % val) arr))

(def v+ (operation +))
(def v- (operation -))
(def v* (operation *))
(def vd (operation /))
(defn scalar [v1, v2] (reduce + (v* v1 v2)))
(defn vect [[a_x, a_y, a_z] [b_x, b_y, b_z]] [(- (* a_y b_z) (* a_z b_y)), (- (* a_z b_x) (* a_x b_z)), (- (* a_x b_y) (* a_y b_x))])
(defn v*s [v s] (opArr * v s))

(defn m*s [m s] (opArr v*s m s))
(def m+ (operation v+))
(def m- (operation v-))
(def m* (operation v*))
(def md (operation vd))
(defn m*v [m v] (opArr scalar m v))
(defn transpose [m] (apply mapv vector m))
(defn m*m [m1 m2]
  (let [m2_tr (transpose m2)]
    (mapv #(m*v m2_tr %) m1)))

(defn op [f x y]
  (cond
    (vector? x) (mapv #(op f %1 %2) x y)
    (vector? y) (mapv #(op f %1 %2) x y)
    :else (f x y)))

(def s+ (partial op +))
(def s- (partial op -))
(def s* (partial op *))
(def sd (partial op /))