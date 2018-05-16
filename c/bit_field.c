#include<stdio.h>
#include<unistd.h>
void f()
{
}

int main(void)
{
struct rec_int {
        unsigned int a:1;
        unsigned int b:1;
        unsigned int c:1;
        unsigned int d:1;
};
printf("%d", (int)sizeof(struct rec_int));
while(1) {
    f();
    sleep(5);
}
}

