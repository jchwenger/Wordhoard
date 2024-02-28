import os
import string
import argparse


def order_by_freq(dict_file, args):
    out_dir, in_fname = os.path.split(dict_file.name)
    freq_fname = os.path.splitext(os.path.split(args.freq_ordered_dict.name)[1])[0]
    out_fname = f"{os.path.splitext(in_fname)[0]}.ordered-by-{freq_fname}.txt"
    out_path = os.path.join(out_dir, out_fname)

    orig_dictionary = set()
    for word in dict_file:
        # the main use case is the google lists (no caps, no punctuation)
        orig_dictionary.add(
            word.lower().translate(str.maketrans("", "", string.punctuation))
        )

    print(f"{len(orig_dictionary)} words found in {in_fname}")

    ordered_dictionary = []
    for word in args.freq_ordered_dict:
        if word in orig_dictionary:
            ordered_dictionary.append(word)

    ordered_set = set(ordered_dictionary)

    print(f"done, checking for words not found in the freq dictionary")

    # ordered_dictionary.append("-------\n") # to check the boundary

    orig_words_not_found = []
    for word in orig_dictionary:
        if not word in ordered_set:
            orig_words_not_found.append(word)

    print(
        f"{len(orig_words_not_found)} words not found in ordered dictionary, appending them at the end"
    )

    ordered_dictionary.extend(orig_words_not_found)

    print(f"writing ordered dictionary to {out_fname}")

    with open(out_fname, "w") as o:
        o.write("".join(ordered_dictionary))


if __name__ == "__main__":

    parser = argparse.ArgumentParser(
        description="""Reorder an alphabetic dictionary file using a
        frequence-ordered dictionary file."""
    )

    parser.add_argument(
        "freq_ordered_dict",
        metavar="FREQ_SOURCE",
        type=argparse.FileType("r"),
        help="""The source file (required).""",
    )

    parser.add_argument(
        "processed_dicts",
        metavar="SOURCES",
        type=argparse.FileType("r"),
        nargs="+",
        help="""The source file (required).""",
    )

    args = parser.parse_args()

    for dictionary in args.processed_dicts:
        print(f"processing dict: {dictionary.name}")
        order_by_freq(dictionary, args)
