#include <unistd.h>
#include <fcntl.h>
#include <sys/stat.h>
#include <stdio.h>
#include <string>

struct struct_foo {
    char c;
    short d;
    char e;
};

class class_foo {
    char c;
    int d;
    char e;
    virtual char x();
    virtual char z();
    char y();
};

union union_foo {
    char a[14];
    char c;
    int d;
};

int main(int argc, char *argv[])
{
    printf("struct foo: %ld\n",sizeof(struct struct_foo));
    printf("class foo: %ld\n",sizeof(struct class_foo));
    printf("union foo: %ld\n",sizeof(union union_foo));
    printf("union foo: %ld\n",sizeof(std::string));
}
