prime(2).
prime(3).

prime(N) :-
    N > 3,
    N mod 2 =\= 0,
    \+ divisible(N, 3).

divisible(N, D) :-
    N mod D =:= 0.

divisible(N, D) :-
    D * D =< N,
    D2 is D + 2,
    divisible(N, D2).

composite(N) :-
    N > 1,
    \+ prime(N).

prime_divisors(1, []).
prime_divisors(X, [X]) :-
	prime(X), !.
prime_divisors(N, [Head | Tail]) :-
	N =\= 1,
	minDiv(N, 2, Head),
	X is N // Head,
	prime_divisors(X, Tail).

square_divisors(1, []).

square_divisors(N, D) :-
    N > 1,
    N1 is N * N,
    prime_divisors(N1, D).

minDiv(N, D, D) :-
    N mod D =:= 0,
    !.

minDiv(N, D, R) :-
    D * D < N,
    D1 is D + 1,
    minDiv(N, D1, R).
