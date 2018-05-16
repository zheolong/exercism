/* 
 * msg_proc.c - RPC Version of printmsg.c
 * 
 * Author:      Ashok Samal
 *              Computer Science Dept.
 *              University of Nebraska-Lincoln
 * Date:        Monday March  26 1996
 */

#include <stdio.h>
#include "msg.h"

/*  Print a message on the console */
/*int * printmessage_1( char ** msg, struct svc_req * req)*/
int * printmessage_1_svc( char ** msg, struct svc_req * req)
/*int * printmessage_1( char ** msg, CLIENT * req)*/
{
    FILE *f;
    static int result;

    f = fopen("/dev/console","w");
    /*f = fopen("/home/forkosh/rpctut1.txt","a");*/

    if (f == (FILE *) NULL) {
        result = 0;
        return(&result);
    }

    fprintf(f,"%s\n",*msg);
    fclose(f);

    result = 1;

    return(&result);
}
