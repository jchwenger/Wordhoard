#!/bin/bash
echo "Computing and extracting the list of words ..."
tail -n +59 ./donn* | iconv -f ISO8859-1 -t UTF8 | grep -o "^\S\+.*$" | sort | uniq > cnam-info-utf8.txt
wc -l cnam-info-utf8.txt

cat cnam-info-utf8.txt | grep -o "^\S\+." > cnam-utf8.txt
wc -l cnam-utf8.txt

cat cnam-info-utf8.txt | awk '{ print $2 }' | sort | uniq > cnam-roots-utf8.txt
wc -l cnam-roots-utf8.txt

cat cnam-info-utf8.txt | iconv -f UTF8 -t ASCII//TRANSLIT  | sort | uniq > cnam-info-ascii.txt
wc -l cnam-info-ascii.txt

cat cnam-info-utf8.txt | iconv -f UTF8 -t ASCII//TRANSLIT  | grep -o "^[a-z]\+" | sort | uniq > cnam-ascii.txt
wc -l cnam-ascii.txt

cat cnam-info-utf8.txt | iconv -f UTF8 -t ASCII//TRANSLIT  | awk '{ print $2 }' | grep -o "^[a-z]\+" | sort | uniq > cnam-roots-ascii.txt
wc -l cnam-roots-ascii.txt

