#!/bin/bash

cat $1 | jq --raw-output '.[] | [.[0], (.[1]|tostring)] | join(" ")' > $2
