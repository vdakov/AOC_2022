import os
import argparse


def get_args():
    parser = argparse.ArgumentParser()
    parser.add_argument('--file_path', type=str, default='../../resources/calories.txt')

    args = parser.parse_args()
    return args

if __name__ == '__main__':
    args = get_args()
    print(args.file_path)
    file = open(args.file_path, mode='r')

    print(file.read())