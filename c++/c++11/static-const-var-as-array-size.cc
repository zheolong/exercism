#include<stdio.h>
int main()
{
   static const int a = 5;
   int b[a] = {0};
   printf("%d",b[0]);
}
