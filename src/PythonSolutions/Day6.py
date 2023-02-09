import argparse

def get_args():
    parser = argparse.ArgumentParser()
    parser.add_argument("--file_path", type = str, default= "resources\packetProcessor.txt")

    return parser.parse_args()

def packetParser(input, numChars):
    set = []
    j = 0
    for c in input:
        if c in set:
            set = set[set.index(c)+1:]
        set.append(c)
        j+=1
        if len(set) >= numChars:
            break

    return j


if __name__ == "__main__":
    args = get_args()

    file = open(args.file_path, mode = "r")
    input = file.read()

    print("It takes ", packetParser(input, 4), " characters to get to the first start-of-packet marker")
    print("It takes ", packetParser(input, 14), " characters to get to the first start-of-message marker")
