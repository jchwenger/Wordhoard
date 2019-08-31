import os
import json
import argparse

def txt_to_json(source_file):
    words = [line.strip() for line in source_file.readlines() if line]
    outdir = os.path.dirname(os.path.abspath(source_file.name))
    outfile = os.path.splitext(os.path.basename(source_file.name))[0]
    outname = os.path.join(outdir, outfile) + '.json'
    with open(outname, 'w') as f:
        json.dump(words, f)
    print('written to file:', outname)

if __name__ == '__main__':

    parser = argparse.ArgumentParser(description='''Turn a line-delimited list
    of word (in plain text) into a json file containing them as an array.''')

    parser.add_argument('-f', '--source_files',
                        type=argparse.FileType('r'),
                        nargs='+',
                        help='''The source file (required).''')

    args = parser.parse_args()

    if args.source_files is not None:
        for source_file in args.source_files:
            print('source file: {}'.format(source_file.name))
            txt_to_json(source_file)
