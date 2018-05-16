/* 
 * msg.x - Remote message protocol file
 * 
 * Author:      Ashok Samal
 *              Computer Science Dept.
 *              University of Nebraska-Lincoln
 * Date:        Monday March  26 1996
 */

program MESSAGEPROG {
    version PRINTMESSAGEVERSION {
        int PRINTMESSAGE(string) = 1;
    } = 1;
} = 0x20000001;
