#!/usr/bin/python

import sys
import re

if len(sys.argv) != 2:
	print("Must supply an argument, the log file to parse")
	sys.exit(1)

input = open(sys.argv[1], "r")

fullText = input.read()

format = "Velocity Left: (\\d+) Velocity Right: (\\d+) Distance Left: (\\d+) Distance Right: (\\d+)"

maxLeftV = 0
maxRightV = 0

# regex search for that type
for match in re.finditer(format, fullText):
	leftV = int(match.group(3))
	rightV = int(match.group(4))
	if(maxLeftV < leftV):
		maxLeftV = leftV
	if(maxRightV < rightV):
		maxRightV = rightV

