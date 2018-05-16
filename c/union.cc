#include <unistd.h>
#include <fcntl.h>
#include <sys/stat.h>
#include <stdio.h>
#include <stdlib.h>
#include <string>
#include <iostream>

using namespace std;
class A 
{ 
public:
 virtual void print(void) 
 { 
    cout<<"A::print()"<<endl; 
 } 
};
class B:public A 
{ 
public:
 virtual void print(void) 
 { 
   cout<<"B::print()"<<endl;
 }; 
}; 
class C:public B
{
public:
 virtual void print(void)
 {
  cout<<"C::print()"<<endl;
 }
};
void print(A a) 
{ 
   a.print(); 
} 
int main(void) 
{ 
   A a, *pa,*pb,*pc; 
   B b; 
   C c; 
   
   pa=&a; 
   pb=&b; 
   pc=&c; 
   
   a.print();
   b.print();
   c.print();
   
   pa->print();
   pb->print();
   pc->print();
   
   print(a);
   print(b);
   print(c); 
}
