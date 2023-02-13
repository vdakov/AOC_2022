import argparse
import math
import numpy as np


def get_args():
    parser = argparse.ArgumentParser()
    parser.add_argument("--file_path", type=str,
                        default="resources/ropePuzzle.txt")

    return parser.parse_args()


def solve_puzzle(input, numNodes=2):
    moves = input.splitlines()
    nodes = [(0, 0)]*(numNodes)
    visited = set()
    visited.add(nodes[-1])
    for move in moves:
        direction = move.split(" ")[0]
        steps = int(move.split(" ")[1])
        for _ in range(steps):
            if direction == "U":
                nodes[0] = (nodes[0][0], nodes[0][1] + 1)
            if direction == "D":
                nodes[0] = (nodes[0][0], nodes[0][1] - 1)
            if direction == "L":
                nodes[0] = (nodes[0][0] - 1, nodes[0][1])
            if direction == "R":
                nodes[0] = (nodes[0][0] + 1, nodes[0][1])

            for k in range(numNodes - 1):
                diff_x = nodes[k][0] - nodes[k + 1][0]
                diff_y = nodes[k][1] - nodes[k + 1][1]
                if not is_touching(nodes[k], nodes[k+1]):
                    nodes[k+1] = (nodes[k+1][0] + np.sign(diff_x),
                                  nodes[k+1][1] + np.sign(diff_y))
                visited.add(nodes[-1])

    return len(visited)


def is_touching(start, tail):
    return math.dist(start, tail) <= np.sqrt(2)


if __name__ == "__main__":
    args = get_args()

    file = open(args.file_path, mode="r")
    input = file.read()

    print("The result for a single head and tail is:", solve_puzzle(input))
    print("The result for a single head and nine tails is:",
          solve_puzzle(input, 10))
