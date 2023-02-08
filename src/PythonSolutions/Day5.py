import numpy as np
import argparse


def get_args():
    parser = argparse.ArgumentParser()
    parser.add_argument("--file_path", type = str, default = "resources\stacks.txt")

    return parser.parse_args()

def process_stacks(input):
    lines = input.splitlines()
    numStacks = 0
    num_lines = 0
    for line in lines:
        if "[" not in line:
            numStacks = int(line[-1])
            break
        num_lines += 1
    stacks = [[] for i in range(numStacks)]
    j = num_lines
    while j > 0:
        curr = lines[j-1]
        for i in range(len(curr)):
            if i % 4 == 1 and curr[i] != " ":
                stacks[i//4].append(curr[i])
        j-=1
    return stacks, num_lines

def part_one(stacks, input, num_lines):
    input = input.splitlines()

    for operation in input[num_lines + 2:]:
        amount = int(operation[5:operation.index("from")-1])
        src =  int(operation[operation.index("from") + 5: operation.index(" to")])
        dst = int(operation[operation.index("to") + 2: ])
        for i in range(amount):
            stacks[dst-1].append(stacks[src-1].pop())

    top = "".join(stack[-1] for stack in stacks)
    
    return top

def part_two(stacks, input, num_lines):
    input = input.splitlines()

    for operation in input[num_lines + 2:]:
        amount = int(operation[5:operation.index("from")-1])
        src =  int(operation[operation.index("from") + 5: operation.index(" to")])
        dst = int(operation[operation.index("to") + 2: ])
        stacks[dst-1]+= stacks[src-1][-amount:]
        stacks[src-1] = stacks[src-1][:len(stacks[src-1])-amount]

    top = "".join(stack[-1] for stack in stacks)
    
    return top



if __name__ == "__main__":
    args = get_args()

    file = open(args.file_path, mode = 'r')
    input = file.read()

    stacks, num_lines = process_stacks(input)
    print("The result from moving one at a time is: ", part_one(stacks, input, num_lines), "\n ")
    stacks, num_lines = process_stacks(input) #resetting stacks
    print("The result from moving several at a time is: ", part_two(stacks, input, num_lines))

    

