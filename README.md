# Archived version on archiving repository (Zenodo)

https://zenodo.org/badge/latestdoi/373793704

[![DOI](https://zenodo.org/badge/DOI/10.5281/zenodo.4899710.svg)](https://doi.org/10.5281/zenodo.4899710)

# What smartExpander does?

smartExpander is a tool for suggesting whether abbreviations are used properly, i.e., whether they should be replaced with corresponding full terms. 

# What is included by this Github repository? 

***/MCParser***: The Java implementing of smartExpander.

***testDataAndCommand.csv***: Testing data to replicate the evaluation in the paper

# How to use smartExpander as a reusable and independent tool?
***1. Download the implementation and testing data:***

    git clone git@github.com:jiangyanjie/smartExpander.git
  
 **2. Switch to the folder where *.class files are:***

      cd Path2Github\smartExpander\MCParser\out\production\MCParser
      
      where `Path2Github` refers to the folder where smartExpander is located.  

***3. Test smartExpander with a single abbreviation:***

   java FindCasualNames.main.smartExpander `abbr` `identifier` `contexts` `typeOfIdentifer` `expansion` `projectName`

   where `abbr` is the abbreviation to be tested; `identifier` is the identifer containing the abbreviation; `contexts` is the contexts of the abbreviations (i.e., identifiers sourrounding the abbreviations; `typeOfIdentifer` is the type of the enclosing identifier (e.g., variable name or method name);  `expansion` is the full terms of the abbreviation; `projectName` is the name of the enclosing Java project. 
  
  An illustrating example:   java FindCasualNames.main.smartExpander nf nf file#jsmooth#model#persistency#make#path#relative#if#possible#root variableName new file davmail
  
 ***4. Expected Output for the execution:***

     “True”: if the abbreviation should be expanded

     “False”: if the abbreviation should not be expanded
     
# How to replicate the evaluation?

***1. Open testDataAndCommand.csv***

    Each row of the table  (except for the first row) represents a testing item (abbreviations).
    
***2.  To test the abbreviation on the `i`th row, copy the testing command on the last row "`Java Command 2 Test This Item`",  execute the commond*** (referring to the section "`How to use smartExpander as a reusable and independent tool?`"), ***and check the results against the value on row "`Necessity4Expansion(Prediction)`"***

![image](https://user-images.githubusercontent.com/10864327/120813783-cf56f380-c580-11eb-97df-7a03a06af20e.png)

     

     
