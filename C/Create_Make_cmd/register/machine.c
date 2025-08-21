#include <stdio.h>
#include "machine.h"


/*THis function checks is for opcode 16, the memory is valid
 *@param word 32 bit instruction
         opcode 6 first bits 
*/
unsigned short test_valid (Mach_word word, Mach_word opcode) {
 int mem = word & 0xfffd;

 if (opcode == 16 && (mem % 4 != 0))
   return 0;     
 if (opcode == 16 && (mem % 4 == 0))
   return 1;
 return 0;
}


/*This helper function uses a stich statement 
 *to check and print the respective:
 *opcode for 0 to 22
 *register form 0 to 13
 *and possible memory
 *@param -opcode the 6 fisrt bits containing the side effect
         -reg_1 the 4 first bits for the regsiter
         -reg_2 the 4 first second bits for the regsiter
         -reg_3 the 4 first last bits for the regsiter
         -mem memoery containing the last 14  bits
*/
void get_print (int opcode, int reg_1, int reg_2, int reg_3, int mem) {
    switch (opcode) {
       case 0 :
          printf("halt");
          break;
        /*CASE1:
         * Uses all three registers
         */
       case 1 :
          printf("add  R%d R%d R%d", reg_1, reg_2, reg_3);
          break;
       case 2 :
          printf("sub  R%d R%d R%d", reg_1, reg_2, reg_3);
          break;
       case 3 :
          printf("mul  R%d R%d R%d", reg_1, reg_2, reg_3);
          break;
       case 4 :
          printf("div  R%d R%d R%d", reg_1, reg_2, reg_3);
          break;
       case 5 :
          printf("rem  R%d R%d R%d", reg_1, reg_2, reg_3);
          break;
       /*CASE6
        *Uses only 2 registers, but which ones of the 3?
        */
       case 6 :
          printf("neg  R%d R%d", reg_1, reg_2);
          break;
       case 7 :
          printf("and  R%d R%d R%d", reg_1, reg_2, reg_3);
          break;
       case 8 :
          printf("or   R%d R%d R%d", reg_1, reg_2, reg_3);
          break;
       case 9 :
	  printf("not  R%d R%d", reg_1, reg_2);
          break;
       case 10 :
          printf("eql  R%d R%d R%d", reg_1, reg_2, reg_3);
          break;
       case 11 :
          printf("neq  R%d R%d R%d", reg_1, reg_2, reg_3);
          break;
       case 12 :
          printf("lt   R%d R%d R%d", reg_1, reg_2, reg_3);
          break;
       case 13:
          printf("gt   R%d R%d R%d", reg_1, reg_2, reg_3);
          break;
       case 14 :
          printf("le   R%d R%d R%d", reg_1, reg_2, reg_3);
          break;
       case 15 :
          printf("ge   R%d R%d R%d", reg_1, reg_2, reg_3);
          break;
       case 16 :
	  printf("test R%d %05d", reg_1, mem);
         /*mem has to ocupied 5 place*/             
          break;
       case 17 :
	  printf("move R%d R%d", reg_1, reg_2);
          break;
       case 18 :
	  printf("li   R%d %d", reg_1, mem);
          break;
       case 19 :
          printf("lw   R%d %05d", reg_1, mem);
          break;
       case 20 :
          printf("sw   R%d %05d", reg_1, mem);
          break;
       case 21 :
          printf("read R%d", reg_1);
          break;
       case 22 :
          printf("prt  R%d", reg_1);
          break;
    }
}


/*This function validates the input of the instructios
 *@param word - the 32 bit instructions containing
                opcoce, 3 registers and the memory
 *@return 1 if word is valid
 *@return 0 if word id invalid
 */
unsigned short is_valid(Mach_word word) {

  if (word == 0) 
    return 1;
  else {
    /* OUTER ELSE
     *extract and divid the 32 bit word
     *into 6bits opcode, 3 of 4 bit resgisters
     *and 14 bits memory
     */
    int opcode = word >> 26; 
    int reg_1 = (word >> 22) & 0xf;
    int reg_2 = (word >> 18) & 0xf;
    int reg_3 = (word >> 14) & 0xf;
    int mem = word & 0x3fff;

    if (mem > NUM_BYTES)
      return 0;

    /*OPCODE
     *THere is only 23 opcodes available
     *starting from 0 to 22
     */
    if (opcode < 0 || opcode > 22) 
      return 0;
    
    /*TEST, SW, prt
     *They're not modifiers. 
     *The rest modify R1, by storing in it a compulation
     *of either r1 and r2 or just r1 or just r2
     *Therefore, r1 CANNOT be the last register
     */
    if (opcode >= 0 && opcode <= 22) {
       if ((opcode != 16 && opcode != 20 && opcode !=22 ) && 
           (reg_1 == 13))  
         return 0;
    }

    /*REGISTERS
     *There are only 13 registers available
     *starting from 0 to 13
     */
    if ((reg_1 < 0 || reg_1 > 13) || (reg_2 < 0 || reg_2 > 13) || 
        (reg_3 < 0 || reg_3 >13)) 
      return 0;

   /*OPCODES USING ADRESS TO STORE MEMORY
     *thei memory can only accsess by group of 4. 4,8,12..
     */
    if (opcode == 16 || (opcode > 18 && opcode <= 20)) {
      if (mem % 4 != 0)
        return 0; 
     }
  }
  /*if all the above false, the 32 bits word/instruction is valid,1*/
  return 1; 
}


/*THis function prints the conresponding opcode
 *@param instr - the 32 bit instructions containing
                opcoce, 3 registers and the memory
 *@return 1 if word is valid
 *@return 0 if word id invalid
 */
unsigned short print_instruction(Mach_word instr) {

/*Check to see f instruction valid */
 int result = is_valid(instr);

/*OUTER IF instructions valid
 *Extract by 6bits for opcodes, 12 for 3 4bis registers
 *and 14 for the momory*/
  if (result == 1) {
    int opcode = instr >> 26;
    int reg_1 = (instr >> 22) & 0xf;
    int reg_2 = (instr >> 18) & 0xf;
    int reg_3 = (instr >> 14) & 0xf;
    int mem = instr & 0xfffd;
    
/*Print  ocpcode,registers, memomry if instruction valid*/
    get_print(opcode, reg_1, reg_2, reg_3, mem);  

    return result;
    }
    /*Else if instruction invalid return 0*/
 return result;   
}


/*This function prints the memory adress of the instruction 
 *if the instruction is valid
 *@param program[] an array containing the instructions
         max_addr  the adres of the last elemt of program
 * @return 0 if invalid
           1 if valid
 */
unsigned short disassemble(const Mach_word program[],
                           unsigned short max_addr) { 
  int index;
  int opcode;
  int reg_1;
  int reg_2;
  int reg_3;
  int mem;
  int addr = 0;
  int size;
  
  /*OUTER IF
  *Check if max_addr is valid
  *if not return 0
  *if yes get the size of the array
  *prints the memory and the intrsuctions
  *PS: The size of the array was lost when sent
       as a parameter, so I can use sizeof to get
       the size of the array
  */
  if (max_addr <= NUM_BYTES && max_addr >= 0) {
    size = (max_addr / BYTES_PER_WORD) + 1;
    for (index = 0; index < size; index++) {
      if (is_valid(program[index])) {
        opcode = program[index] >> 26;
        reg_1 = (program[index] >> 22) & 0xf;
        reg_2 = (program[index] >> 18) & 0xf;
        reg_3 = (program[index] >> 14) & 0xf;
        mem = program[index] & 0xfffd;
        printf("%04x: ", addr);
        get_print(opcode, reg_1, reg_2, reg_3, mem);
        printf("\n");
        addr += 4;
       }
      else {
        return 0;  
      }			   
   }
  }
  return 0;
}


/*This function chech for the test
 *@param program  contains the intsruction
         max_addr adress of the last instruction
 *@return 1 if intrsuction is 16 and valid
         -1 if max_addr is greater than 16884
         add if opcode 16 and invalid                                                                                        
*/
short check_branches(const Mach_word program[], unsigned short max_addr) {
  int index = 0;
  int opcode;
  int size;
  int count = 0;
  int add = 0; /*adress 0,4,...*/
 
  if (max_addr <= NUM_BYTES && max_addr >= 0) {
    size = max_addr / BYTES_PER_WORD;
    for (index = 0; index < (size - 1); index++, add +=4) { 
       opcode = program[index] >> 26; 
       if (opcode == 16 && test_valid(program[index], opcode))
	 count++;      
       if (opcode == 16 && !test_valid(program[index], opcode))
        return add;
    }
      if (count > 0)
        return 1;  			 
  }
  return -1;
}


