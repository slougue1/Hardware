#include <stdio.h>
 float cashier(int print_info) {
   int nber_item;
   char type_item;
   double price,sum_price=0;
   char each[4];
   while (scanf(" %d %c %lf %s", &nber_item, &type_item, &price, each)>0 && nber_item != '\0'){
       sum_price += (nber_item * price);
       if (print_info == 1){
           printf("%d items of type %c\n", nber_item, type_item);
       }
   }
   return sum_price;
 }
 int print_powers(int limit){
    char character;
    int i=0;
    printf("Enter f (float) or (integer) : ");
    scanf(" %c",&character);
    if(character == 'f'){
        double power=1, incre=1;
        for(i=1; i<=limit; i++){
            printf("%lf, %lf \n",incre,power);
            incre =incre+0.5;
            power = incre * incre;
        }
    } else if(character == 'i'){
        int power=1, incre=1;
        for(i=1; i<=limit; i++){
            printf("%d, %d \n",incre,power);
            incre =incre+2;
            power = incre * incre;
        } 
    }
 }

 void compute(int sum_flag, int print_flag){
     int user_val,sum_val=0,prod_val=1;
    do {
        printf("Enter Value: ");
        scanf(" %d",&user_val);
        sum_val+=user_val;
        prod_val*=user_val;
     }while(user_val != -1);
     
     if(sum_flag == 1 && print_flag ==1){
        printf("Result: %d\n",sum_val+1);
     }else if(sum_flag == 0 && print_flag ==1){
        printf("Result: %d\n",prod_val*-1);
     }
     
 }
 int phone_password(int three_digits, int four_digits, int error_message){
     int thre_digit,fou_digit,count=0;
     do{
        printf("Enter phone in XXX-YYYY format:");
        scanf(" %d-%d",&thre_digit,&fou_digit);
        count++;
        if (error_message == 1 && thre_digit!=three_digits && four_digits!=fou_digit)
        {
            printf("Invalid phone\n");
        }
     }while(thre_digit!=three_digits && four_digits!=fou_digit);
     printf("Number of attempt(s) : %d\n",count);
 }
float get_taxes(int print_values){
    char character;
    float salary;
    if(scanf("%c-%f",&character,&salary)==2){
        if(print_values == 1){
            printf("Values provided: %c, %f \n",character,salary);
        }
        if(character == 'H'){
            return 0.0;
        }else if(character == 'F'){
            return salary*0.2;
        }
    }else{
        printf("Invalid input\n");
        return -1.0;
    }
}
int main(){
    printf("%f\n", get_taxes(1));
    //phone_password(333,4444,1);
    //compute(1,1);
    //print_powers(3);
    //printf("%lf",cashier(0));
    return 0;
}
cashier.c
Displaying Worksheet1.pdf. Page 1 of 3
