#This script prints a different string related to a theme related to Shakespearean insults

import random

col1 = ["artless","bavdy", "bawdy", "beslubbering", "bootless", "bootless",
"craven","cockered", "currish","clouted", "dankish", "dissembling","subtle"]
col2 = ["base-court", "bat-fowling", "beetle-headed","beef-witted ","boil-brained",
"clapper-clawed ","crook-pated", "doghearted", "dankish","fat-kidneyed"]
col3 = ["apple-john","baggage", "clack-dish","foot-licker", "fustilarian",
"miscreant","pigeon-egg","varlot", "vassal","whey-face", "nut-hook", "lewdster"]
insult = "Thou " + random.choice(col1) + " " + \
random.choice(col2) + " " + random.choice(col3)

print(insult)
