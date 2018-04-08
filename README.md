Application analyzes the structure of the text.
Sample text files to analyze in the 'resources' directory.
Indicate filenames as an command-line arguments (args table) when running application.

***

to run app:

mvn clean package

java -jar target/text-analysis-1.0-SNAPSHOT.jar <file1> <file2> ...

eg.:

java -jar target/text-analysis-1.0-SNAPSHOT.jar resources/test.txt resources/test2.txt resources/test_malville_moby.txt

***

result output in file: OUTPUT_RESULT.md

nice use,
jq
