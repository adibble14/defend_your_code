import sys
from logger import logging


def create_file(name, text):
    try:
        file = open(name, 'w')
        file.write(text)
        file.close()
    except OSError:
        logging.critical(f"Failed to create {name}")
        sys.exit(-1)
    return 1

def read_file(name):
    try:
        file = open(name, 'r')
        text = ''
        for line in file:
            text = text + line
        file.close()
        return text
    except OSError:
        logging.critical(f"Failed to read from {name}")
        sys.exit(-1)