# Anagram Finder
A simple command line utility for finding anagrams in a specified file

## Software required to run this
* Java 17

## Assumptions
- grouped anagrams words and single words are printed on the console on new line
- special characters are considered as part of anagram i.e b@c and @bc are considered to be anagrams
- words are considered to be case-insensitive for this solution. i,e abc and BAC are considered to be anagrams

## Building and Running the tests
```
./gradlew clean build
```

## Running the program
please run the below command from the root of the project
```
./gradlew clean bootRun --args="data/example2.txt"
```
where example2.txt is the text file that we want to search for anagrams

## Big O Analysis
-  Time - 
    O(n * m)
    where n is the number of words
    and k is the average length of word
- Space - 
  O(n) where n is the number of words

## data structure

- Hashmap - for quick lookup and insert of the words into the respective anagram word group
- ArrayList - used to store collection of anagram words . arraylist provides dynamic resizing and efficient iteration 
- Array - to store finite number of characters for counting and comparison of character occurrences.

## improvements
1) Provision to read more then one file. read all the files present in a directory
2) to handle large file , chunk the file into smaller parts and then load the data into memory to fasten the processing
3) have a look at better implementation for larger files with longer words. 
   current implementation will become slow if the file size increases and also the length of the word increases. 