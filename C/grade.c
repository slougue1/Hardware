/*
*This script check student grade, drop the lowest before computing
*the mean and standard daviation
*/

#include <stdio.h>
#include <math.h>
int MAX_ASSIGNS = 50;
void drop_assign(int grade[], int numb, int drop);
double score_assign(int grade[], int numb, int late, int drop);
double mean (int grade[], int assign_late, int assign_numb);
double standard_deviation (int grade[], int assign_late, int assign_numb);
 void get_print_out (int grade[], int assign_numb, 
                     int assign_late, int assign_drop, char stats);

int main() 
{
   int x = 0,weight_count=0;
   int point_penalty, assign_drop, assign_numb;
   int grade[MAX_ASSIGNS*4];
   char stats;

   scanf(" %d %d %c",&point_penalty, &assign_drop, &stats );
   scanf(" %d",&assign_numb);

   while (x < (assign_numb * 4)) 
   {
      scanf(" %d, %d, %d, %d", &grade[x], &grade[x+1], &grade[x+2], &grade[x+3]);
      weight_count+=grade[x+2];
      x +=4;
   }
   if (weight_count != 100){
        printf("ERROR: Invalid values provided\n");
    }
    else{
        get_print_out(grade, assign_numb, point_penalty, assign_drop, stats);
    }
   
   
   return 0;
}
/* This method print out the dired strings */

/*This function checks the lowest grade and drop it 
 *before computing the mean and standard deviation*/
void drop_assign(int grade[], int numb, int drop) 
{
   int index =0, value=0, count = 1, drop_index = 0;

   /* This block drops the lowest grade*/
   while (count <= drop) 
   {
      int lowest = grade[1] * grade[2];
      while ( index < (numb * 4)) 
      {
	     value = grade[index+1] * grade[index+2];
	     if (grade[index]>0) 
         {
            if (lowest> value || (lowest == value && index < drop_index)) 
            {
               lowest  = value;
               drop_index = index;
            }
	      }
         else{
          drop_index+=4;
         }
      }
      grade[drop_index + 2] = 0;
      count++;
   }
}


 
/* This function calculate the numeric score by dropping assignments  and also  
 * the course grade based on assigns/weights/late penalties */
 double score_assign(int grade[], int numb, int late, int drop) 
{
   int x, assign_late;
   double sum_weight = 0, score = 0;

   /* drop "x number" of lower scores, if drop > 0 
   if (drop > 0) {
      drop_assign(grade, numb, drop);
   }*/

   /* get the weight sum, in case assignments were dropped */
   for (x = 0; x < (numb*4); x+=4) {
	 sum_weight += grade[x+2];
   }

   /* check that the weight sum isn't over 100 */
   if (sum_weight > 100.0) {
      printf("ERROR: Invalid values provided\n");
      return -1;
   }
   else
   {
      /* calculate each numeric score */
      for (x = 0; x < (numb * 4); x += 4)
      {
         assign_late = grade[x + 1] - grade[x + 3] * late;
         if (assign_late > 0)
         {
            score += assign_late * (grade[x + 2] / sum_weight);
         }
      }
   }
   return score;
}

/*This methods calculate the mean of all assigments 
 *by dividing the sum of scores by the number of assigments.
 *Also, the moethod has to deduct the late assigments*/
 double mean (int grade[], int assign_late, int assign_numb) 
 {
   int x=0; /*An indicator*/
   /*double mean_1 =0.0;*/
   int score_penal=0;
   double score =0;
 while (x < (assign_numb * 4)) 
   {
       /*Why x+1? beceause assigment 1 has index 4, assigment 2 ->index 8, and so for*/
        score_penal = grade[x+1] - grade[x+3] * assign_late;
       if (score_penal >0)
       {
            score+=score_penal;
       }
      x+=4;     /*+4 because one assigment as 4 lines,
                 *so to skip to the next assignment I have to skip 4 lines*/
   }
   return (score / assign_numb);
}

/* This method calculates the devuation
 * The standar deviation is how wide the gades are distibuted (mean and devuation are related)
 * It is proportinal to the assignment number */
 double standard_deviation (int grade[], int assign_late, int assign_numb) 
{
   int x=0;
   int score; 
   double sum = 0; /*Set the accumulator to 0*/
   double mean_sd = mean (grade, assign_late, assign_numb); /*mean st not to cunfused with functin mean*/
   double standard_deviation;
   while (x < (assign_numb * 4)) 
   {
      score = grade[x+1] - (grade[x+3] * assign_late);
      if (score >0){
         sum += pow((score - mean_sd),2);
      }
      x+=4;  
   }

   /*The sum/assign_numb is the variance */
   return standard_deviation =  sqrt(sum / assign_numb);
   
}


 void get_print_out (int grade[], int assign_numb, 
                     int assign_late, int assign_drop, char stats) 
 {
   int x=0, count = 1;
   int grade_2[MAX_ASSIGNS *4],j=0;
   double score=0, mean_1 = 0, standard_deviat = 0;
   
   
   while ( x < (assign_numb * 4)) 
   {
      grade_2[j] = grade[x];
      grade_2[j+1] = grade[x+1];
      grade_2[j+2] = grade[x+2];
      grade_2[j+3] = grade[x+3];
      j+=4;
      x+=4;
   }
   if (assign_drop < assign_numb)
   {
      score = score_assign(grade_2, assign_numb, assign_late, assign_drop);
   }

   printf("Numeric Score: %5.4f\n", score);
   printf("Points Penalty Per Day Late: %d\n", assign_late);
   printf("Number of Assignments Dropped: %u\n", assign_drop);
   printf("Values Provided: \n");
   printf("Assignment, Score, Weight, Days Late\n");
   x=0;
   while (count <= assign_numb) 
   {
     
      while ( x < (assign_numb * 4)) 
      {
	     if (grade[x] == count) 
        {
	      printf("%d, %d, %d, %d\n", grade[x], grade[x+1], grade[x+2], grade[x+3]);
	     }
        x+=4;
      }
      count++;
   }  
   if (stats == 'Y' || stats == 'y') 
   {
      mean_1 = mean(grade, assign_late, assign_numb);
      standard_deviat = standard_deviation(grade, assign_late, assign_numb);
      printf("Mean: %5.4f, Standard Deviation: %5.4f\n", mean_1, standard_deviat);
   }
}




grade_sa.c
Displaying grade_sa.c.
