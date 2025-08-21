
#include <stdio.h>
#include <string.h>
#include <ctype.h>
#include "document.h"
#define MAX_LEN 1024

static int blank_line(char *line);
int file (Document *doc, const char *filename);
int save_doc(Document *document, char *filename);

/*
   This method checks first to see if the document or the name is NULL.
   If it is, it is a fail. If not, sets document to have 0 paragraph, and return a success
*/ 
int init_document(Document *doc, const char *name){
  
  if (doc == NULL || name == NULL ||
      strlen(name) > MAX_STR_SIZE) {
    return FAILURE;
  }

  doc->number_of_paragraphs = 0;
  strcpy(doc->name, name);
   
  return SUCCESS;
}

/*
  This function is a bit like the above but it reset the number os paragarph to 0. 
  If the document is NULL it is a failure. Else, set the document to 0 and it is a SUCCESSets.
*/
int reset_document(Document *doc){

  if (doc != NULL) {
      doc->number_of_paragraphs = 0;
      return SUCCESS;
   }
   return FAILURE;
}

/*
  This functions prints the  name, number of paragraphs, followed
  by the paragraphs. Note that Each paragraph should be separated by an empty line 
*/
int print_document(Document *doc){
  int row, col;
 /* int next_line = 0;*/

/*if (doc == NULL)
return FAILURE;
*/
  if (doc == NULL){
  return FAILURE;}


  printf("Document name: \"%s\"\n", doc->name);
  printf("Number of Paragraphs: %d\n", doc->number_of_paragraphs);

  /*This blcok prints the lines by going through each paragraph*/
  for (row = 0; row < doc->number_of_paragraphs; row++)
  {
    if (doc->paragraphs[row].number_of_lines>0) {
    for (col = 0; col < doc->paragraphs[row].number_of_lines; col++)
    {
       /*The paragraph lines has to be non 0*/
    /* if (doc->paragraphs[row].lines[col] != '\0'){*/

        printf("%s\n", doc->paragraphs[row].lines[col]);
    }
       if ( (row ) <( doc->number_of_paragraphs -1))
       {
          printf ("\n");
       }
    }
  }

}
/*
  This function adds a paragraph right after the specified paragraph number that start at 1.
  If it is 0, add the paragraph at the beginning of the document parameter. 
*/
int add_paragraph_after(Document *doc, int paragraph_number){
  int index;

  if (doc == NULL || doc->number_of_paragraphs == (MAX_PARAGRAPHS) ||
   doc->number_of_paragraphs  < paragraph_number) 
   {
    return FAILURE;
  }
  
  /*This block adds the end of the paragraph*/
  if (paragraph_number == doc->number_of_paragraphs) 
  {
    doc->paragraphs[paragraph_number].number_of_lines = 0;
    doc->number_of_paragraphs++;
  }

  /*Otherwise, this block add it in th the lines of the paragraph*/
  else if (doc->number_of_paragraphs > paragraph_number) 
  {
    for (index = doc->number_of_paragraphs - 1; index >=
	   paragraph_number ; index--)
    {
      doc->paragraphs[index + 1] = doc->paragraphs[index];
    }
    doc->paragraphs[paragraph_number].number_of_lines = 0;
    doc->number_of_paragraphs++;
  }

  return SUCCESS;
}



/*
  Adds a new line after the line with the speci?ed line 
  number. Line numbers start at 1. If the line number is 0,
  the line will be added at the beginning of the paragraph. 
*/
int add_line_after(Document *doc, int paragraph_number,
		   int line_number, const char *new_line){
  int ;
  
  /*if (doc != NULL && new_line != NULL) {*/
  if (doc == NULL ||
      doc->paragraphs[paragraph_number-1].number_of_lines
      == MAX_PARAGRAPH_LINES
      || doc->number_of_paragraphs< paragraph_number - 1 ||
      doc->paragraphs[paragraph_number - 1].number_of_lines
      < line_number || new_line == NULL ) 
 {
    
    return FAILURE;  
  }
  
  /*Thid block adds a line to the end of the paragraph*/
  if (line_number == doc->paragraphs[paragraph_number - 1].number_of_lines)
  {
    strcpy(doc->paragraphs[paragraph_number - 1].lines[line_number],
	   new_line);
    doc->paragraphs[paragraph_number - 1].number_of_lines++;
  }
  /*else, this one is responsible of adding it in the lines o paragths*/
  else  if (doc->paragraphs[paragraph_number - 1].number_of_lines
	   > line_number) 
   {
    for (index = doc->paragraphs[paragraph_number - 1].number_of_lines - 1;
	 index>=line_number; index--) 
    {
      strcpy(doc->paragraphs[paragraph_number - 1].lines[index + 1],
	     doc->paragraphs[paragraph_number - 1].lines[index]);
    }
    strcpy(doc->paragraphs[paragraph_number - 1].lines[line_number],
	   new_line);
    doc->paragraphs[paragraph_number - 1].number_of_lines++;
   
  }
 
  return SUCCESS;
}

/*
  this returns the number of lines in a paragraph
*/
int get_number_lines_paragraph(Document *doc,
			       int paragraph_number, int *number_of_lines){
  int numb_lines = 0;

   if (doc != NULL && paragraph_number <= doc->number_of_paragraphs) 
   { 
      
	 numb_lines = doc->paragraphs[paragraph_number].number_of_lines;
	 *number_of_lines = numb_lines;
	 return SUCCESS;
   
   }
   return FAILURE;
}

/*
  this function appends a new line to the speci?ed paragraph.
*/ 
int append_line(Document *doc, int paragraph_number,
		const char *new_line){
 
  if(doc==NULL || doc->paragraphs[paragraph_number-1].number_of_lines
     ==MAX_PARAGRAPH_LINES || doc->number_of_paragraphs<
     paragraph_number-1 || new_line==NULL )
     {
    
    return FAILURE;
  }
  strcpy(doc->paragraphs[paragraph_number - 1].
	 lines[doc->paragraphs[paragraph_number - 1]
	       .number_of_lines],new_line);
  doc->paragraphs[paragraph_number-1].number_of_lines++;
    
  return SUCCESS;
}

/*
  It removes the specificf line from the paragraph*/
int remove_line(Document *doc, int paragraph_number, int line_number){
  int index;
  
  if (doc == NULL || doc->number_of_paragraphs < paragraph_number - 1
      || doc->paragraphs[paragraph_number - 1].number_of_lines <
      line_number) {
    
    return FAILURE;
    
  }
  
  /*Removes if one line*/
  if (doc->paragraphs[paragraph_number - 1].number_of_lines == 1) {
    *doc->paragraphs[paragraph_number-1].lines[0]=0; 
  }
  
  /*Removes otherwise*/
  else 
  {
    for (index = line_number; index < doc->paragraphs[paragraph_number - 1].number_of_lines; index++) 
    {
      strcpy(doc->paragraphs[paragraph_number - 1].lines[index - 1],
	     doc->paragraphs[paragraph_number - 1].lines[index]);
    }
   
    *doc->paragraphs[paragraph_number - 1].
      lines[doc->paragraphs[paragraph_number - 1].number_of_lines - 1]=0;
  }

  doc->paragraphs[paragraph_number - 1].number_of_lines--;

  return SUCCESS;
}

/*
  This function adds the first data lines number of lines from the 
  data array to the document 
*/
int load_document(Document *doc, char data[][MAX_STR_SIZE + 1], int data_lines){
  int index;
  
  if (doc == NULL || data == NULL|| data_lines == 0)
  {
    return FAILURE;
  }

  add_paragraph_after(doc, number_of_paragraphs);

  for (index = 0; index < data_lines; index++)
  {
    /* This block add a new paragraph at data */
    if (strcmp(data[index], "") == 0) {
      number_of_paragraphs++;
      add_paragraph_after(doc, number_of_paragraphs);
    }

    else {  
      append_line(doc, number_of_paragraphs+1 ,data[index]);
    }
  }
  
  return SUCCESS;   
}

/*
  This function replace target with replaceme all in the document
*/ 
int replace_text(Document *doc, const char *target, const char *replacement)
{
  int row, col, position;
  char tmp[MAX_STR_SIZE + 1], *tmp2;
  
  if (doc == NULL || target == NULL || replacement == NULL) 
  {
    return FAILURE;
  }
  for (row = 0; row < doc->number_of_paragraphs; row++) 
  {
    for (col = 0; col < doc->paragraphs[row].number_of_lines; col++)
    {
      tmp2 = strstr(strcpy(tmp, doc->paragraphs[row].lines[col]), target);
      if(tmp2 != NULL)
      {
        position = tmp2 - tmp;
      }
     
      while (tmp2){
	/*Find the beginning*/
	doc->paragraphs[row].lines[col][position] = '\0';

	/*Add the replacement*/
	strcat(doc->paragraphs[row].lines[col], replacement);

	tmp2 += strlen(target);

	/*add the remaining part*/
	strcat(doc->paragraphs[row].lines[col], tmp2);

	tmp2 = strstr(tmp2, target);

	/*Set position */
	if (tmp2 != NULL) 
  {
	  position = strlen(doc->paragraphs[row].lines[col]) - strlen(tmp2);
	}
    }
   }
  }
  return SUCCESS;
}

/*
  This function highlight the text associated with target all in the document
*/
int highlight_text(Document *doc, const char *target)
{
  char result[MAX_STR_SIZE + 1] = "";
   
   if (doc != NULL && target != NULL) 
   {
      /*The hihglited text should be replaced */
      strcat(result, HIGHLIGHT_START_STR);
      strcat(result, target);
      strcat(result, HIGHLIGHT_END_STR);
      replace_text(doc, target, result);
      return SUCCESS;
   }
   return FAILURE;
}

/*
  This function removes the text target everywhere in the document
*/
int remove_text(Document *doc, const char *target)
{
  if (doc != NULL && target != NULL) 
  {
      replace_text(doc, target, "");
      return SUCCESS;
   }
   return FAILURE;
}

/*
 This function is like the load document, but data will be loaded from a file instead
 of using an array. 
*/
int file (Document *doc, const char *filename) 
{
  FILE *file = fopen(filename, "r");
  char line[MAX_LEN + 1];
  int paragraph = 0, index=0;

  if (doc == NULL || filename == NULL || file == NULL) 
  {
    return FAILURE;
  }

  if (index == 0) 
  {
    index++;
    add_paragraph_after(doc, paragraph);
    
    fgets(line, MAX_LEN + 1, file);

    while( !feof(file) ) {

      line[strlen(line)-1] = '\0';
   
      if (blank_line(line) == SUCCESS) {
	paragraph++;
	add_paragraph_after(doc, paragraph);
      }

      
      else {
	
	append_line(doc, paragraph+1, line);
      }
      fgets(line, MAX_LEN + 1, file);
    }
  }

  if (index == 1) {
    fgets(line, MAX_LEN + 1, file);
      while( !feof(file) ) {
   
      if (blank_line(line) == SUCCESS) {
	add_paragraph_after(doc, 0);
      }

      
      else {
	line[strlen(line)-1] = '\0';
	append_line(doc, 1, line);
      }
      fgets(line, MAX_LEN + 1, file);
    }
  }
  
  fclose(file);
  return SUCCESS;   
}

/*
  This function print the paragraphs associated with a document. 
*/
int save_doc(Document *document, char *filename) {
 FILE *file = fopen(filename, "w");
 int row, col, nextline_printed = 0;
  

  if(document == NULL || filename == NULL || file == NULL){
    return FAILURE;
  }

  
  for (row = 0; row < document->number_of_paragraphs; row++) 
  {
    for (col = 0; col < document->paragraphs[row].number_of_lines; col++) 
    {
	fputs(document->paragraphs[row].lines[col], file);
	fputs("\n", file);
    }

    /*Should the next line character be printed or not*/
    if (nextline_printed == 1 && row + 1 < document->number_of_paragraphs &&
	 document->paragraphs[row + 1].number_of_lines != 0) 
      {
	fputs("\n", file);
      }
    
    if (nextline_printed == 0) 
    {
      nextline_printed++;
      if (document->paragraphs[0].number_of_lines != 0 &&
	  document->number_of_paragraphs > 1) 
    {
	fputs("\n", file);
      }
    }
  }
  fclose(file);
  return SUCCESS;

}

/* This function checks for blank lines */
static int blank_line(char *line)
{
  int index, length= strlen(line);

  if(*line=='\0' || *line=='\n') 
  {
    return SUCCESS;
  }

index =0;
  while ( index < length)
  {
    if(isspace(line[index])==0)
    {
      return FAILURE;
    }
    index++;
  }
  
  return SUCCESS;
}

