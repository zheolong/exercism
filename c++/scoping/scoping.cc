#include <stdio.h>
int x;

 void h() {
   printf("%d\n",x);  
 }

 void f() {
   int x = 13;
   h();
 }

 void g() {
   int x = 12;
   h();
 }

 int main() {
   x = 14;
   f(); 
   g();
 }
