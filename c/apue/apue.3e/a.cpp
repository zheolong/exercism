#include<stdio.h>
class A
{
    public:
        virtual void func(){}
    private:
        int num;
};
int main()
{
    A* a = NULL;
    a->func();
    return 0;
}
