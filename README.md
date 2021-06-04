What smartExpander does?
smartExpander is a tool for suggesting whether abbreviations are used properly, i.e., whether they should be replaced with corresponding full terms. 

How to obtain BugBuilder?
BugBuilder can be obtained from https://github.com/jiangyanjie/smartExpander (This repository is permanently stored and available).

How to get started?
/MCParser: The Java source code implementing smartExpander.
Sample.csv: The example to run smartExpander

How to use smartExpander?
1.Preparation
  git clone git@github.com:jiangyanjie/smartExpander.git
2.Run smartExpander
--cd smartExpander\MCParser\out\production\MCParser
--java FindCasualNames.main.smartExpander `abbr` `identifier` `sameLine identifiers` `property` `expansion` `project name`
For example:

java FindCasualNames.main.smartExpander rem remAuxFile rem#aux#file methodName remove drjava
  
Output:
“True”: the abbreviation should be expanded
“False”: the abbreviation should not be expanded