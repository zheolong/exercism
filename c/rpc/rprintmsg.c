/* 
 * rprintmsg.c - Remote version of "printmsg.c"
 * 
 * Author:      Ashok Samal
 *              Computer Science Dept.
 *              University of Nebraska-Lincoln
 * Date:        Monday March  26 1996
 */
#include <stdio.h>
#include <rpc/rpc.h>
#include "msg.h"

main(int argc, char ** argv)
{
    CLIENT * client;
    int * result;
    char * server;
    char * message;

    if (argc != 3) {
    fprintf(stderr, "Usage : %s <host> <message>\n",argv[0]);
    exit(1);
    }
    server = argv[1];
    message = argv[2];

    /* Create the client handle used for calling MESSAGEPROG on the server */

    /*client= clnt_create(server,MESSAGEPROG,PRINTMESSAGEVERSION,"visible");*/
    client = clnt_create(server, MESSAGEPROG, PRINTMESSAGEVERSION, "tcp");

    /* Check if connection is established to the server */
    if (client == NULL) {
    clnt_pcreateerror(server);
    exit(1);
    }

    /* Call the remote procedure */
    result = printmessage_1(&message, client);
    if (result == (int *) NULL) {
    /* Error occurred while calling the server */
    clnt_perror(client, server);
    exit(1);
    }
    /* RPC worked; check if the message was delivered */
    if (*result == 0) {
    fprintf(stderr,"%s : Coult not print your message\n",argv[0]);
    exit(1);
    };
    /* Everything (RPC, message delivery) worked */
    printf("Message delivered to %s\n",server);
    clnt_destroy(client);
    exit(0);
}

#if 0
/*  Print a message on the console */
printmessage( char * msg)
{
    FILE *f;
    f = fopen("/dev/console","w");
    if (f == NULL) {
    return(0);
    }
    fprintf(f,"%s\n",msg);
    pclose(f);
    return(1);
}
#endif
