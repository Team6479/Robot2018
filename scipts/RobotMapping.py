#!/usr/bin/python

import sys
import re

if len(sys.argv) != 3:
	print("Must supply two arguments, the java file to parse and the output file name")
	sys.exit(1)

types = { "DS", "PWM", "PCM", "DIO", "ANALOG", "RELAY" }

input = open(sys.argv[1], "r")

output = open(sys.argv[2], "w")
output.write("# Robot Wiring")

fullText = input.read()

#search for types
for type in types:
	regex = "\/\/{0}([\s\S]*)\/\/END {0}".format(type)
	# regex search for that type
	match = re.search(regex, fullText)
	if match:
		# write this section to the file
		output.write("\n\n## {0}".format(type))
		# make a table
		output.write("\n\n| Port | Device |")
		output.write("\n|:--- |:--- |")
		values = match.group(1).splitlines()
		for value in values:
			# if the line is a constant
			lineMatch = re.search("public static final int (\w+) = (\d+);", value)
			if lineMatch:
				output.write("\n| {0} | {1} |".format(lineMatch.group(1), lineMatch.group(2)))
				

