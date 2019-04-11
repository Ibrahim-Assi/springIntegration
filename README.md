# springIntegration
Spring Integration with Eclipse Using Maven
We have Rss feed from AL Aljazeera : https://www.aljazeera.net/aljazeerarss/a7c186be-1baa-4bd4-9d80-a84db769f779/73d0e1b4-532f-45ef-b135-bfdff8b8cab9 

 

We need to build Java Spring Integration project as Maven project to create integration flow doing below things : 

Read RSS.
Split news per items. 
Save each item as separate XML file with name <guid> value with .xml extension like "a5fd3a88-5f54-4212-8612-d34658585e1c.xml"
Folders structure to save items files must be as below:
Folder per day from <pubDate> with format like  yyyy-MM-dd.
Each date must contains sub folder with category name from <category> and save item file inside like
2019-04-01
سياسة
a5fd3a88-5f54-4212-8612-d34658585e11.xml
a5fd3a88-5f54-4212-8612-d34658585e12.xml
اقتصاد
a5fd3a88-5f54-4212-8612-d34658585e13.xml
2019-04-02
رياضة
a5fd3a88-5f54-4212-8612-d34658585e14.xml
 
log item link from <link> as log as final step on the flow.
Root folder for these files must be configurable.

Please note :
1. You must build one Integration flow to do this Exercise.
2. DSL code style, don’t use XML for spring configuration beans.
3. EIP pattern required for this flow.
4. ThreadPool with size 5 for saving files after splitting items.
5. Spring integration File to save files required.
