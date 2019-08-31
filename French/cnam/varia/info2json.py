import os
import json
from collections import defaultdict

source_file = 'cnam-info-utf8.txt'

with open(source_file) as t:
    data = [x for x in t.read().split('\n') if x]

worddict = {}
rootdict = defaultdict(list)

for d in data:
    word, root, form = d.split('\t')

    # first dict: word -> root + grammatical form
    worddict[word] = {
                        'root': root,
                        'form': form
                        }
    # second dict: root -> word + grammatical form
    rootdict[root].append({'word': word, 'form': form})

# writing to file
outdir = os.path.dirname(os.path.abspath(source_file))
outfile = os.path.splitext(os.path.basename(source_file))[0]
outname = os.path.join(outdir, outfile) + '.json'
outname_r = os.path.join(outdir, outfile) + '-roots.json'
print('source file: {}'.format(source_file))
with open(outname, 'w') as f:
    json.dump(worddict, f)
print('json dictionary word > root+form written: {}'.format(outname))
with open(outname_r, 'w') as f:
    json.dump(rootdict, f)
print('json dictionary root > word+form written: {}'.format(outname_r))
