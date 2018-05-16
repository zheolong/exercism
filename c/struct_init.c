#include <unistd.h>
#include <fcntl.h>
#include <sys/stat.h>
#include <stdio.h>
#include <p99_for.h>

#define INIT_ELEMENT(NAME, X, I) [I] = { \
      .index = I,                            \
      .arraytoread = &decimals[I]            \
}
#define INIT_ELEMENTS(N) P99_FOR(, N, P00_SEQ, INIT_ELEMENT, P99_DUPL(N,))

double decimals[2] = {1.0, 2.0};

typedef struct { 
    int index;
    double* arrayToRead;
} my_struct;

my_struct A[] = { INIT_ELEMENTS(2) };

//my_struct[2] = {
//    {0, &decimals[0]},
//    {1, &decimals[1]}
//};

int
main(int argc, char *argv[])
{
}
