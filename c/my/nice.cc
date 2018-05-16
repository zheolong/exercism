#include <errno.h>
#include <limits.h>
#include <unistd.h>
#include <stdio.h>
#include <string.h>
#include <sys/resource.h>

void print_priority(pid_t pid);

int main(int argc, char *argv[])
{
    // nice 
    pid_t pid = getpid();
    print_priority(pid);

    int incr = 1;
    printf("incr: %d\n", incr);
    nice(incr);
    print_priority(pid);

    incr = 2 * NZERO - 1;
    printf("incr: %d\n", incr);
    nice(incr);
    print_priority(pid);

    // set_priority
    int prio_val = 4;
    printf("prio_val: %d\n", prio_val);
    printf("%d\n", setpriority(PRIO_PROCESS, pid, prio_val));
    printf("%s\n", strerror(errno));
    print_priority(pid);
}

void print_priority(pid_t pid)
{
    printf("priority of %d: %d\n", pid, getpriority(PRIO_PROCESS, pid));
}
