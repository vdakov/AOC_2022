import os 
import numpy as np
import argparse

def get_args():
    parser = argparse.ArgumentParser()
    parser.add_argument("--file_path", type = str, default = "resources\gameRPS.txt")

    return parser.parse_args()

def rock_paper_scissors(input):
    d = {"X": 1, "Y":2, "Z":3, "AX":3, "AY":6,"AZ":0, "BX": 0, "BY":3, "BZ":6
              , "CX":6, "CY":0, "CZ":3}
    input = input.splitlines()

    s = 0
    for turn in input: 
        line = turn.split(" ")
        s+=d[line[1]]
        s+=d[line[0] + line[1]]

    return s


def rock_paper_scissors_strat(input):
    win = {"A": 2 , "B": 3, "C" : 1 }
    draw={"A": 1 , "B": 2, "C" : 3 }
    loss = {"A": 3 , "B": 1, "C" : 2 }
    input = input.splitlines()

    s = 0
    for turn in input: 
        line = turn.split(" ")
        if(line[1] == "X"): s += loss[line[0]]
        if(line[1] == "Y"): s += 3 + draw[line[0]]
        if(line[1] == "Z"): s += 6 + win[line[0]]

    return s


if __name__ == '__main__':
    args = get_args()
    file = open(args.file_path, mode = 'r')

    input = file.read()

    print("The score of all turns is:", rock_paper_scissors(input))
    print("The score of all turns (with strategies) is:", rock_paper_scissors_strat(input))


