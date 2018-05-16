#include<iostream>
#include<string>
typedef bool (*cmp2)(const std::string &, const std::string &);
bool cmp1(const std::string &, const std::string &)
{
    return true;
}

int __attribute__((warn_unused_result)) func();
class A {};
A operator+(A const &, A const &);

struct S {
  int operator,(int) { return 0; }
};

int main()
{
    struct timeval tv = {
        .tv_sec = 1;
        .tv_usec = 0;
    };
    select(0, NULL, NULL, NULL, &tv);
    bool (*cmp)(const std::string &, const std::string &) = cmp1;
    std::cout << cmp("1","2") << std::endl;
    cmp2 f = cmp1;
    std::cout << f("1","2") << std::endl;
    int a = 10;
    int b = 1;
    int c = printf("%*c%*c",a,'\r',b,'\r');
    std::cout<<c<<std::endl;
    int d = 'ABCD';
    std::cout<< std::hex << d<<std::endl;
    A aa;
    (void)func();
std::cout << std::dec << (S(), 42) << '\n';           // prints '0'
std::cout << std::dec << ((void) S(), 42) << '\n';    // prints '42'
if("NOTREACHED")
    std::cout<< '1'<<std::endl;
}

int func()
{
    return 1;
}
