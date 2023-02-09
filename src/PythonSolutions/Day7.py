import argparse
from collections import defaultdict
import numpy as np

def get_args():
    parser = argparse.ArgumentParser()
    parser.add_argument("--file_path", type = str, default = "resources/terminal.txt")

    return parser.parse_args()

def process_files(input):
    input = input.splitlines()
    input = [command for command in input if not command == "$ ls"] #strips ls lines 
    dict = defaultdict(int)
    filepath = []

    for command in input:
        if command.startswith("$ cd"):
            match command:
                case "$ cd /":
                    filepath.clear()
                    filepath.append("/")
                case "$ cd ..":
                    filepath.pop()
                case _:
                    dir = command.split("cd ")[1]
                    filepath.append(dir)

        else: #we have file listing/size
            size = command.split(" ")[0] #since the file size is before the rest of them
            if size.isdigit():
                size = int(size)
                for i in range(len(filepath)):
                    dir = '/'.join(filepath[:i+1]).replace("//", "/") #gets a filepath variable in the dictionary 
                    dict[dir] += size

    return dict

def part_one(system):
    sum = 0
    for dir in system.keys():
        if(system[dir] <= 100000): sum+= system[dir]
    return sum
def part_two(system, total_disk_space, update_space):
    free_space = total_disk_space - system["/"]
    if(free_space >= update_space): return None, None

    needed_space = update_space - free_space

    names = []
    sizes = []
    for dir in system.keys():
        if system[dir] >= needed_space:
            names.append(dir)
            sizes.append(system[dir])
    i = np.argmin(sizes)

    return names[i], sizes[i]


if __name__ == "__main__":
    args = get_args()

    file = open(args.file_path, mode = "r")
    input = file.read()

    dict = process_files(input)

    print("The sum of the size of all directories at most 100 000 is: ", part_one(dict))

    total_disk_space = 70000000
    update_space = 30000000

    name, size = part_two(dict, total_disk_space, update_space)

    print("The directory that will need to be deleted is ", name," of size " ,size )



