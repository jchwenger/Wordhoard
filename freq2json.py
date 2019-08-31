import os
import json
import argparse
from collections import OrderedDict

def freq_to_json(source_file):
    lines = [line.strip() for line in source_file.readlines() if line]
    ddict, rdict = OrderedDict(), OrderedDict()
    for l in lines:
        w, c = l.split('\t')
        ddict[w] = c
        rdict[c] = w
    outdir = os.path.dirname(os.path.abspath(source_file.name))
    outfile = os.path.splitext(os.path.basename(source_file.name))[0]
    outname = os.path.join(outdir, outfile) + '.json'
    outname_r = os.path.join(outdir, outfile) + '-reverse.json'
    with open(outname, 'w') as f:
        json.dump(ddict, f)
    print('written to file:', outname)
    with open(outname_r, 'w') as f:
        json.dump(rdict, f)
    print('written to file:', outname_r)

if __name__ == '__main__':

    parser = argparse.ArgumentParser(description='''Turn a line-delimited list
     of words followed by their frequency (tab-delimited) (all in plain text) 
     into two json files containing the data as a dictionary, once in the
     original order {word: count}, the other reversed {count: word}.''')

    parser.add_argument('-f', '--source_files',
                        type=argparse.FileType('r'),
                        nargs='+',
                        help='''The source file (required).''')

    args = parser.parse_args()

    if args.source_files is not None:
        for source_file in args.source_files:
            print('source file: {}'.format(source_file.name))
            freq_to_json(source_file)
