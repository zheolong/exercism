#include<cstdio>
#include<string>
class SpecialString: public std::string { // bad idea! std::string has a ... // non-virtual destructor
    public:
        SpecialString(const char* str): std::string(str){}
};

int main()
{
     SpecialString *pss = new SpecialString("Impending Doom");
     delete pss;
}
