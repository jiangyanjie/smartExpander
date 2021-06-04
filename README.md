# How to get started? 

/MCParser: The Java implementing of smartExpander.

Sample.csv: Testing data to replicate the evaluation in the paper

# How to use smartExpander? 
1. Download the implementation and testing data:

  git clone git@github.com:jiangyanjie/smartExpander.git
  
2. Switch to the folder where .class files are:

For example, “cd smartExpander\MCParser\out\production\MCParser”

3. Test smartExpander with a single abbreviation:

java FindCasualNames.main.smartExpander `abbr` `identifier` `sameLine identifiers` `property` `expansion` `project name`

For example:

java FindCasualNames.main.smartExpander rem remAuxFile rem#aux#file methodName remove drjava
 
Output:

“True”: the abbreviation should be expanded

“False”: the abbreviation should not be expanded
