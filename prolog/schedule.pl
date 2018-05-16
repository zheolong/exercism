%% ---
%%  Excerpted from "Seven Languages in Seven Weeks",
%%  published by The Pragmatic Bookshelf.
%%  Copyrights apply to this code. It may not be used to create training material, 
%%  courses, books, articles, and the like. Contact us if you are in doubt.
%%  We make no guarantees that this code is fit for any purpose. 
%%  Visit http://www.pragmaticprogrammer.com/titles/btlang for more book information.
%%---
member_team(qiaojunlong, qbus).
member_team(liuwei3-s, qbus).
member_team(ouyangliduo, qbus).
member_team(yanguotao, qbus).

member_team(yuanzhewei, qconf).
member_team(wangkang, qconf).

arrange(Team, Primary, Secondary) :- member_team(Primary, Team), member_team(Secondary, Team), \+(Primary = Secondary).

naive_sort(List,Sorted):-perm(List,Sorted),is_sorted(Sorted).

is_sorted([]).
is_sorted([_]).
is_sorted([ [H1,T1], [H2,T2] | T ]):- \+(H1 = H2), \+(H1 = T2), \+(H2 = T1), is_sorted([ [H2,T2] | T ]).


perm(List,[H|Perm]):-mydelete(H,List,Rest),perm(Rest,Perm).
perm([],[]).

mydelete(X,[X|T],T).
mydelete(X,[H|T],[H|NT]):-mydelete(X,T,NT).

schedule(Team, FinalList) :- 
    findall([One, Two], arrange(Team, One, Two), ArrangeList),
    naive_sort(ArrangeList, FinalList).

main :-
    schedule(qbus, FinalList),
    write(FinalList),
    halt.
