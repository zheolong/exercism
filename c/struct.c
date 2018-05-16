#include <unistd.h>
#include <fcntl.h>
#include <sys/stat.h>
#include <stdio.h>

void foo(void);
void f(char x[]);
int
main(int argc, char *argv[])
{
    int fd = 3;
    struct stat p;
    fstat(0, &p);
    printf("%lld", (long long)p.st_ino);
    fstat(1, &p);
    printf("%lld", (long long)p.st_ino);
    fstat(2, &p);
    printf("%lld", (long long)p.st_ino);
    fstat(3, &p);
    printf("%lld", (long long)p.st_ino);
    dup2(fd, 0);
    dup2(fd, 1);
    dup2(fd, 2);
    if (fd > 2)
        close(fd);
    fstat(0, &p);
    printf("%lld", (long long)p.st_ino);
    fstat(1, &p);
    printf("%lld", (long long)p.st_ino);
    fstat(2, &p);
    printf("%lld", (long long)p.st_ino);
    fstat(3, &p);
    printf("%lld", (long long)p.st_ino);
#define N 3
#define Y(x) ((N+1)*x)
    printf("%d", 2*(N+Y(4+1)));
    char x[] = "abcd";
    char y[] = {'a','b','c','d'};
    char *z = "abcd";
    char *q = NULL;
    printf("%d", (int)sizeof x);
    printf("%d", (int)sizeof y);
    printf("%d", (int)sizeof z);
    printf("%d", (int)sizeof q);
    foo();
    char a[100];
    char *b = a;
    char *c = "abc";
    printf("%d\n", (int)sizeof a);
    printf("%d\n", (int)sizeof b);
    printf("%d\n", (int)sizeof c);
    f(a);
    f(b);
    f(c);
    struct var{
        char c[6];
        void *p;
        short b;
        double d;
        int i;
    };
    printf("%d", (int)sizeof(struct var));
}

void foo(void)
{
    unsigned int a = 6;
    int b = -20;
    (a+b>6)?puts(">6"):puts("<=6");
}

void f(char x[])
{
    printf("%d\n",(int)sizeof((char*)x));
}
