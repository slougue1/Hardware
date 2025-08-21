#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <unistd.h>
#include <pthread.h>

#define LINE_SZ 256
/**GREP_THREAD**/
/*data used by each thread */
struct thread_data
{
    char *search;
    char *filename;
    int  sum;
};

/*This function ignores file with erro
 *@param data data to grep
 *@void retun
*/
void *grep(void *data)
{
    FILE *fp;
    char line[LINE_SZ];
    struct thread_data *td;
    
    td= (struct thread_data *) data;
    td->sum= 0;
    fp = fopen(td->filename, "r");
    if (fp != NULL) {
        while (fgets(line, 
               LINE_SZ, fp) != NULL)
            if (strstr(line, 
                td->search) != NULL)
                td->sum++;
        fclose(fp);
    }
    pthread_exit(NULL);
}

/*This is the main function
 *it allocates memeory
 *create and end the threats
 @param   argc
 @param   argv
 @return  0
*/
int main(int argc, char *argv[])
{
    int sum= 0;
    int n_threads = 0;
    pthread_t *threads;
    struct thread_data *td;
    char *search;
    int i; /*index*/

    if (argc > 2) {
        search= argv[1];
        n_threads = argc - 2;
        /* allocate thread structures*/
        threads = malloc(n_threads * sizeof(pthread_t));    
        if (threads == NULL)
        {
            fprintf(stderr, "Error allocating threads\n");
            return EXIT_FAILURE;
        }
        /* allocate thread data*/
        td = malloc(n_threads * sizeof(struct thread_data));    
        if (td == NULL)
        {
          free(threads);
          fprintf(stderr, "Error allocating thread data\n");
          return EXIT_FAILURE;
        }

        /* create threads */
        for(i = 0; i < n_threads; i++) {
 /* filename from command line argument */
            td[i].filename = argv[i + 2]; 
            td[i].search = search;
            pthread_create(&threads[i], NULL, 
                           grep, (void *)&td[i]);
        }
        
        /* wait for threads to end */
        /* add total from thread */
        for(i = 0; i < n_threads; i++) {
            pthread_join(threads[i], NULL);
            sum += td[i].sum; 
        }

        /* free allocated thread data */
         /* free allocated threads */
        printf("%d\n", sum);
        free(td);       
        free(threads); 
    }

    return 0;
}


/**GREP**/
######
