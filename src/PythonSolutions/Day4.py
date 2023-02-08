import numpy as np 
import argparse 

def getArgs():
    parser = argparse.ArgumentParser()
    parser.add_argument("--file_path", type=str, default="resources\pairs.txt")
    return parser.parse_args()

def part_one(input):
    input = input.splitlines()
    sum = 0
    for line in input:
        e1, e2 = [[int(num) for num in e.split("-")] for e in line.split(",")]
        sum += 1 if (complete_overlap(e1,e2) or complete_overlap(e2,e1)) else 0
    return sum

def part_two(input):
    input = input.splitlines()
    sum = 0
    for line in input:
        e1, e2 = [[int(num) for num in e.split("-")] for e in line.split(",")]
        sum += 1 if any_overlap(e1,e2) else 0
    return sum

def complete_overlap(e1, e2):
    return e1[0] <= e2[0] and e1[1]>=e2[1]

def any_overlap(e1, e2):
    low_bound = max(e1[0], e2[0])
    up_bound = min(e1[1], e2[1])
    return low_bound <= up_bound



if __name__ == "__main__":
    args = getArgs()

    input = open(args.file_path, mode = 'r')
    input = input.read()

    print("Amount of proper subset pairs:", part_one(input))
    print("Amount of pairs with any intersection:", part_two(input))

