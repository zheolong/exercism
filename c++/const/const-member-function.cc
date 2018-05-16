#include<stdio.h>
class A 
{
    static const int a;
    int b;
    void foo() const { b = 5; }
};

const int A::a = 1;

int main()
{
   static const int a = 5;
   int b[a] = {0};
   printf("%d",b[0]);
}
