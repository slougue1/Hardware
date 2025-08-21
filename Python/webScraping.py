
# The url below is a link to a json file that contains data about people currently in space. Load in the url above as a json file and extract the total number of people currently in space.
# <br> Link: http://api.open-notify.org/astros.json 

# In[16]:


# import required libraries
import urllib.request, urllib.parse, urllib.error
import requests
import json


# Use urllib.request and urlopen to open the url
# Read and decode the file
url = "http://api.open-notify.org/astros.json"
response = urllib.request.urlopen(url).read().decode("utf-8")

# Load in the file as json
data = json.loads(response)
print(response)

# extract the number of people
count = 0
for i in data["people"]:
    if i["craft"] == "ISS":
        count += 1


# ### Q2 (2 pts) Extract names of people in space
# Using the same json file, print out the names all six people in space.

# In[25]:


# Extract the name of the 6 people currently in space
for x, i in enumerate(data["people"], 1):
    if i["craft"] == "ISS":
        print("Person " + str(x) + ": "+ i["name"])
# Print out the names


# ## Q3 (3 pts) Open these URLs in your browser:
# - https://api.umd.io/v0/courses?dept_id=INST
# - https://api.umd.io/v0/courses?dept_id=INST&gen_ed=DSSP
# 
# What is displayed each time?

# In[ ]:


#Your Comparison here
#The first link displays a dictionary with data and information like course_id, name, semester of INST courses: INST126, INST123, INST327...
#However, the second  displays an empty list


# In[ ]:


# Adapted from UMD Courses team
import urllib.request, urllib.parse, urllib.error, json

# base URL
url = 'https://api.umd.io/v0/courses?'

# parameters for the request
url += 'dept_id=INST'
#url += '&gen_ed=DSSP'

fh = urllib.request.urlopen(url)
data = json.loads(fh.read().decode())

data


# ### 3B (4 pts) Modify the code to print each:
# <ol><li>Course name
# <li>Course ID
# <li>Prerequisites - analyze the code carefully
# <li>Sections</ol>
# 
# Test each change before you make the next one - step-by-step, incremental development!

# In[35]:


# Adapted from UMD Courses team
import urllib.request, urllib.parse, urllib.error, json

# base URL
url = 'https://api.umd.io/v0/courses?'

# parameters for the request
url += 'dept_id=INST'
#url += '&gen_ed=DSSP'

fh = urllib.request.urlopen(url)
data = json.loads(fh.read().decode())

for i in data:
    print("Course name: " + i["name"])
    print("Course ID: " + i["course_id"])
    #for x in i["relationships"]:
        #print("Prerequisites: " + x["prereqs"])
    print("Prerequisites: " + str(i["relationships"]["prereqs"]))
    print("Sections: " + str(i["sections"]) + "\n")        


# ### Q4 (1 pt) Retrieve all the words starting with ‘b’ or ‘B’ from the following text.

# In[39]:


import re
text = """Betty bought a bit of butter, But the butter was so bitter, 
So she bought some better butter, To make the bitter butter better."""

b_words = re.findall("[Bb]\w+", text)
print(b_words)


# ### Q5 (2 pts) Clean up the following tweet so that it contains only the user’s message. That is, remove all URLs, hashtags, mentions, punctuations, RTs and CCs.

# In[86]:


import re
tweet = '''Good advice! RT @TheNextWeb: What I would do differently if I was learning to code today 
http://t.co/lbwej0pxOd cc: @garybernhardt #rstats'''

replace = re.sub("([@#][A-Za-z0-9]+)|(RT|cc)|([^0-9A-Za-z \t])|(\w+:\/\/\S+)"," ",tweet)
replace = replace.split()
' '.join(replace)
#desired_output = 'Good advice What I would do differently if I was learning to code today'


# ### Q6 (6 pts)  Given the file 'usa.txt' which contains a large list of American English words, create a crossword helper function that takes as its argument a string with some known letters and underscores for unknown letters (Example inputs: "c_ol___t___", "ca___l__ge", or "cis____") and outputs a list of possible matches.

# In[128]:



with open('usa.txt') as dict_file:
        dictionary = dict_file.read().split()

def checkWord (testWord):
    for word in dictionary:
        char_count = len(inputWord)-inputWord.count('_')
        count = 0
        match_count = 0
        match_word = None
        my_list = list()
        if len (word) == len (inputWord):
            for char in inputWord:
                if char == '_':
                    count += 1
                elif char == word[count]:
                    count +=1
                    match_count +=1
                else:
                    count += 1
            if match_count == char_count:
                print(word)
    return 

inputWord = input ("Enter Cross word: ")
checkWord (inputWord.lower())


# In[ ]:





# In[ ]:





# In[ ]:





# In[ ]:




