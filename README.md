Java program that 

1.	Selects a text file from your computer using a Java Swing JFileChooser, which starts out showing the files in the same directory from which the program was executed.   
2.	Reads the selected text file.  The text file for my test will consist of a number of rows, each row containing two double values, separated by spaces. The two values represent the x and coordinates of a given point in the two dimensional space
3.	Writes the two closest points to the output file.  Specifically, the output text file will be in the same directory as the input text file, where the naming convention is as follows:  If the input file is abc.txt, the output file is abc_out.txt.

Sample:
The input file on the left should look like the following:
11.5	3.1
-2.0	-1.1
0	0
10.0	-3.0
The output file should include the x and y coordinates of the two closest points from the input file. 
In the above example the output file should have:
0	0
-2.0	-1.1
