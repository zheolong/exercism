#include<cstdio>
#include<cstdlib>

int main(int argc, char *argv[])
{
    int data = 0;
    int *restrict p = &data;
    *p = 1;
    data = 2;
    printf("%d", data);
    *p = 3;
    printf("%d", data);
}
