#include<stdio.h> 
#include<iostream> 

using namespace std;

int main(int argc, char *argv[]) 
{ 
    int a=2,b=1,c=0;
    int n=atoi(argv[1]);
    if (n<3) {
        cout<<n<<endl;
        return EXIT_SUCCESS;
    }
    while(n-->=3)
    {
        int t =a+2*b+3*c;
        c=b;
        b=a;
        a=t;
    }

    cout<<a<<endl;
    return EXIT_SUCCESS;
}
