#!/bin/bash
echo "Computing and extracting the list of words ..."
tail -n +59 ./donn* | iconv -f ISO8859-1 -t UTF8 | grep -o "^\S\+.*$" | sort | uniq > all-utf8.txt
wc all-utf8.txt
cat all-utf8.txt | grep -o "^\S\+." > all-words-utf8.txt
wc all-words-utf8.txt
cat all-words-utf8.txt | iconv -f UTF8 -t ASCII//TRANSLIT  | grep -o "^[a-z]\+" | sort | uniq > all-words-ascii.txt
wc all-words-ascii.txt
cat all-utf8.txt | iconv -f UTF8 -t ASCII//TRANSLIT  | sort | uniq > all-ascii.txt
wc all-ascii.txt
cat all-ascii.txt | awk '{ print $2 }' | grep -o "^[a-z]\+" | sort | uniq > all-root-ascii.txt
wc all-root-ascii.txt

