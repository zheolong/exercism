#include<iostream>

class A {
    public:
        A() { std::cout << "constructor" << std::endl; };
        A(const A &rhs) { std::cout << "copy constructor" << std::endl; };
        A & operator=(const A &rhs) { std::cout << "assignment" << std::endl; return *this; };
};

int main()
{
    /* compiler will always choose the easiest way */
    A a; /* output constructor */
    A b(a); /* output copy constructor, if no copy constructor, use the default one */
    A c = a; /* output copy constructor, if no copy constructor, use the default one */
    c = b; /* output assignment */
}
