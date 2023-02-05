import os
import argparse
import numpy as np


def get_args():
    parser = argparse.ArgumentParser()
    parser.add_argument('--file_path', type=str, default='resources/calories.txt')

    args = parser.parse_args()
    return args


def elf_most_calories(input):
    elves = []
    input = input.splitlines()

    s = 0
    for calories in input:
        if calories == "":
            elves.append(s)
            s = 0
            continue 
        s += int(calories)


    return max(elves)

def top_three_elves(input):
    elves = []
    input = input.splitlines()

    s = 0
    for calories in input:
        if calories == "":
            elves.append(s)
            s = 0
            continue 
        s += int(calories)

    elves = np.flip(np.sort(elves))
    return sum(elves[:3])


if __name__ == '__main__':
    args = get_args()
    print(args.file_path)
    file = open(args.file_path, mode='r')

    input = file.read()

    print("The elf who ate the most calories is:", elf_most_calories(input))
    print("The sum of the elves of the top three who ate the most calories is:", top_three_elves(input))

