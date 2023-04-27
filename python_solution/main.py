# Main class of program
# Created by Mario, April 26th 2023


from logger import logging
import regex

class Main():

    def __init__(self) -> None:
        self.max_int_val = '2147483647'
        self.min_int_val = '-2147483648'
        pass


    def start(self):
        logging.info('Logger Started.')
        first_name = None
        last_name = None
        int_A = None
        int_b = None
        logging.info("asking for first name.")
        while first_name == None:
            first_name = self.get_first_name()
            logging.info("First name input result after regex: " + first_name)
        while last_name == None:
            last_name = self.get_last_name()
            logging.info("Last name input result after regex: " + last_name)
        while int_A == None:
            int_A = self.get_int()

    def get_first_name(self):
        print("First Name must consist of only alphabetical symbols. Upper and lower case is fine. Maximum 50 symbols.")
        res = input("Enter First Name: ")
        logging.info("User First Name Input: " + res)
        return regex.first_name_regex(res)
    
    def get_last_name(self):
        print("Last Name must consist of only alphabetical symbols and spaces. Upper and lower case is fine. Maximum 50 symbols.")
        res = input("Enter Last Name: ")
        logging.info("User Last Name Input: " + res)
        return regex.last_name_regex(res)
    
    def get_int(self):
        print("Integers can be positive or negative.")
        res = input("Enter an integer: ")
        logging.info("User integer input: " + res)
        return regex.integer_regex(res)
        






if __name__ == '__main__':
    main = Main()
    main.start()