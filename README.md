
## Carbon Emission Tracker
This is a simple Java Application which uses OOP principles to calculate the carbon emissions of a household over a specified period of time. (ie. a week). It uses their food consumption, transportation, electricity usage, and other aspects of their daily activities to find the total emissiosn of the household for the week.

Features
1. Creating a user profile
2. Enter the weekly emissions
3. Generate a weekly report
4. Calculate the Total Emissions for the household
5. GUI based on Swing for user interaction

## Running instructions
The main application is `main.java', to run this you need the jvm, using IntelliJ or any Java runtime.
Using Visual Studio Code:

Run from the command line using the command:

`'C:\Program Files\Eclipse Adoptium\jdk-21.0.8.9-hotspot\bin\java.exe' '-XX:+ShowCodeDetailsInExceptionMessages' '-cp' 'C:\Users\HP\AppData\Roaming\Code\User\workspaceStorage\3f552485ba976b4ab9e69cdbe6d10d23\redhat.java\jdt_ws\Project_cd137bff\bin' 'Main'`




## Dependencies
The java Dependencies we used are listed below:

```
java.io.IOException;
java.util.Scanner;
java.util.InputMismatchException;
javax.swing.*;
java.awt.*;
java.awt.event.*;
java.io.BufferedReader;
java.io.BufferedWriter;
java.io.File;
java.io.FileReader;
java.io.FileWriter;
java.io.FilenameFilter;
java.util.TreeMap;
java.io.*;
java.util.Random;

```

## Assumptions
The main assumptions made were:
1. The user inputs the carbon emissions of all members of the family.
2. The user inputs numbers for the expenses rather than letters.
3. The user inputs the values of the expenses in the corresponding unit (eg. kilograms)
4. The user inputs the weight of their food so the emission can be calculated.
