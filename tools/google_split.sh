#!/bin/bash

if [ $# -lt 1 ]
then
  echo "usage: ./split.sh fnames"
  exit
fi

mkdir -p results

pat='\(_ADJ \|_ADP \|_ADV \|_CONJ \|_DET \|_NOUN \|_NUM \|_PRON \|_PRT \|_ROOT \|_VERB \|_X \)' 

for f in "$@"
do

  pos_name="results/${f%.raw.txt}-pos.txt"
  echo "saving POS entries to '$pos_name'"
  cat "$1" \
    | grep -e "$pat" \
    >  "$pos_name"
  sed -i 's/ /\t/' "$pos_name"

  count_name="results/${f%.raw.txt}-count.txt"
  echo "saving regular entries to '$count_name'"
  cat "$f" \
    | grep -ve "$pat" \
    >  "$count_name"
  sed -i 's/ /\t/' "$count_name"

  reg_name="results/${f%.raw.txt}.txt"
  echo "saving regular entries without count to '$reg_name'"
  cat "$count_name" \
    | cut -f 1 \
    > "$reg_name"
done
