%% ---
%%  Excerpted from "Seven Languages in Seven Weeks",
%%  published by The Pragmatic Bookshelf.
%%  Copyrights apply to this code. It may not be used to create training material, 
%%  courses, books, articles, and the like. Contact us if you are in doubt.
%%  We make no guarantees that this code is fit for any purpose. 
%%  Visit http://www.pragmaticprogrammer.com/titles/btlang for more book information.
%%---
%
sum(0, []).
sum(Sum, [Head | Tail]) :- sum(TailSum, Tail),  Sum is Head + TailSum.

count(0, []).
count(Count, [Head | Tail]) :- count(TailCount, Tail),  Count is 1 + TailCount.

average(Average, List) :- sum(Sum, List), count(Count, List), Average is Sum / Count.
