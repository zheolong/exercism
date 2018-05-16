#include <errno.h>
#include <limits.h>
#include <unistd.h>
#include <stdio.h>
#include <string.h>
#include <sys/resource.h>

int main(int argc, char *argv[])
{
    int a[10] = {0};
    for (int i = 0; i < 10; ++i) printf("%d", i[a]);
    printf("\n");
    for (int i = 0; i < 10; ++i) printf("%d", a[i]);
    printf("\n");
    printf("%lu\n", sizeof(a));
    int *p = a;
    printf("%lu\n", sizeof(p));
    //char *x = "abc";
    //x[0] = 'b';
}
