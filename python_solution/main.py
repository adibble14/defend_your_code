# Main class of program
# Created by Mario, April 26th 2023


import model
from logger import logging

def main():
    logging.info("Logging initiated.")
    MODEL = model.Model()
    logging.info("Created Model obj.")
    while MODEL.first_name == None:
        MODEL.set_first_name()
    while MODEL.last_name == None:
        MODEL.set_last_name()
    while MODEL.multiply() == MODEL.max_int_val:
        MODEL.int_A = None
        MODEL.int_B = None
        handle_ints(MODEL)
    while MODEL.input_file_name == None:
        MODEL.set_input_file_name()
    while MODEL.output_file_name == None:
        MODEL.set_output_file_name()
    while MODEL.password_set == False:
        MODEL.set_password()
    passwords_match = False
    while passwords_match == False:
        passwords_match = MODEL.check_password()

def handle_ints(MODEL):
    while MODEL.int_A == None:
        MODEL.set_first_int()
    while MODEL.int_B == None:
        MODEL.set_second_int()
    



if __name__ == '__main__':
    main()