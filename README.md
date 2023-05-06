# defend_your_code
Write a program in Java and Python that does the following.

prompts for and reads the user's first name, then last name -- each should be at most 50 characters -- decide what is legitimate for characters for first and last name
You must make it clear to the user what is expected for input (describe range, acceptable characters, and anything else you feel is important)

prompts for and reads in two int values from the user (range of values are those of a 4 byte int)
Once again, make clear to user what is expected. Note that an int can be from -2,147,...,... to 2,147,...,...

prompts for reads the name of an input file from the user

prompts for reads the name of an output file from the user


prompts the user to enter a password, store the password, then ask the user to re-enter the password and verify that it is correct
password should be hashed using a salt and written to a file
to validate, grab hash from file and compare it to hash from second user entry for password
as long as passwords don't match/follow your password specifications, re-prompt the user

opens the output file and
writes the user's name
writes the result of adding the two integer values (no overflow should occur)
writes the result of multiplying the two integer values (no overflow should occur),
writes the contents of the input file
Each thing written should be clearly labeled (e.g. First name, Last name, First Integer, Second Integer, Sum, Product, Input File Name, Input file contents)
NOTE: it is ok to echo output to the screen as you wish

Your program should do whatever you feel is necessary to ensure the above information is properly obtained (meaning you must get valid input from the user) and subsequently written to the output file -*without error*.  Any error that does arise should be reported to an error log file.

Your program should keep running until it gets proper input from the user.  You must mandate proper input is entered and must not allow your program to crash.
