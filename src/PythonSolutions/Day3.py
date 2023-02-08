import numpy as np
import argparse

def getArgs():
    parser = argparse.ArgumentParser()
    parser.add_argument("--file_path", type = str, default = "resources\packages.txt")

    return parser.parse_args()

def part_one(input):
    sum = 0
    input = input.splitlines()
    for line in input:
        visited = []
        p1 = line[:len(line)//2]
        p2 = line[len(line)//2:]
        for c in p1:
            if (c in p2 and c not in visited):
                if(c.isupper()): 
                    sum+= ord(c) - 65 + 27
                if(c.islower()): 
                    sum+= ord(c) - 96
                visited.append(c)
    return sum
def part_two(input):
    sum = 0
    input = input.splitlines()
    for line1, line2, line3 in zip(*[iter(input)]*3):
        #print(line1, "\n", line2, "\n", line3, "\n")
        visited = []
        for c in line1:
            if (c in line2 and c in line3 and c not in visited):

                if(c.isupper()): 
                    sum+= ord(c) - 65 + 27
                if(c.islower()): 
                    sum+= ord(c) - 96
                visited.append(c)
    return sum



if __name__ == "__main__":

    args = getArgs()
    file = open(args.file_path, mode = 'r')


    input = file.read()

    print("The sum of all of the shared items between bags is ", part_one(input) )
    print("The sum of all of group tags is ", part_two(input) )
