import argparse
import numpy as np


def get_args():
    parser = argparse.ArgumentParser()
    parser.add_argument("--file_path", type=str, default="resources/trees.txt")

    return parser.parse_args()


def parse_forest(input):
    input = input.splitlines()
    forest = []
    for line in input:
        row = [int(c) for c in line]
        forest.append(row)

    return forest


def part_one(forest):
    forest = np.array(forest)
    visible = np.zeros(forest.shape, dtype=int).astype(bool)
    visible[0, :], visible[-1, :], visible[:,
                                           0], visible[:, -1] = True, True, True, True

    for i in range(1, len(visible) - 1):
        for j in range(1, len(visible[i]) - 1):
            if isVisible(i, j, forest):
                visible[i, j] = True

    return np.count_nonzero(visible)


def isVisible(i, j, forest):
    val = forest[i, j]
    return max(forest[i, j+1:]) < val or max(forest[i, :j]) < val or max(forest[:i, j]) < val or max(forest[i+1:, j]) < val


def visible_score(i, j, forest):
    val = forest[i, j]
    u = 0
    k = i - 1
    while k >= 0:
        u += 1
        if (forest[k, j] >= val):
            break
        k -= 1
    d = 0
    k = i + 1
    while k < len(forest):
        d += 1
        if (forest[k, j] >= val):
            break
        k += 1
    r = 0
    k = j + 1
    while k < len(forest[i]):
        r += 1
        if (forest[i, k] >= val):
            break
        k += 1
    l = 0
    k = j - 1
    while k >= 0:
        l += 1
        if (forest[i, k] >= val):
            break
        k -= 1

    return l * r * u * d


def part_two(forest):
    forest = np.array(forest)
    scores = []

    for i in range(1, len(forest) - 1):
        for j in range(1, len(forest[i]) - 1):
            scores.append(visible_score(i, j, forest))

    return max(scores)


if __name__ == "__main__":
    args = get_args()
    file = open(args.file_path, mode="r")
    input = file.read()

    forest = parse_forest(input)

    print("The amount of visible trees in the forest is ", part_one(forest))
    print("The maximum visible score in the forest is ", part_two(forest))

