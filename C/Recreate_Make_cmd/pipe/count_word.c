#include <stdio.h>
#include <stdlib.h>
#include <assert.h>
#include <sys/types.h> 
#include <unistd.h>
#include <err.h>
#include <sysexits.h>
#include "safe-fork.h"

int main () {
  int fd[2]; /*this always has to be 2 alemt, 0 and 1*/
  int count; /*to count the number of word*/
  pid_t pid; /*not an integer on veery machine so su pid_t, like size_t*/

  assert(pipe(fd) != -1);
  pid = safe_fork();

  if (pid > 0) { /*Parentds*/
   /* char data[200];*/
    
      assert(dup2(fd[0], STDIN_FILENO) != -1);
      assert(close(fd[0]) != -1);/*Closed one at the time*/
      assert(close(fd[1]) != -1);

      /*ERROR
       *count cannot be negative
       */
      if (scanf("%d", &count) < 0) {
         exit(1);
      }
      /*LONG
       *The text is long
       */ 
      if (count >= 200) {
          printf("Long enough!\n");
          exit(0);
      }
      /*SHORT text*/   
      if (count >= 0 && count < 200) {
          printf("Too short!\n");
          exit(1);
      }
   } 
  
  /*!!EROOR!! fork failed children didn't create*/
  if (pid < 0)
      exit(1);

  if (pid == 0) { /*children id*/
      assert(dup2(fd[1], STDOUT_FILENO) != -1);
      assert(close(fd[0]) != -1);
      assert(close(fd[1]) != -1);
      execlp("/usr/bin/wc", "wc", "-w", NULL);

      exit(1);
   }
  return 0;     
}
