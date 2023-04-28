# Main class of program
# Created by Mario, April 26th 2023


from logger import logging
import regex

class Main():

    def __init__(self) -> None:
        self.max_int_val = 2147483647
        self.min_int_val = -2147483648
        pass


    def start(self):
        logging.info('Logger Started.')
        first_name = None
        last_name = None
        int_A = None
        int_B = None
        input_file_name = None
        output_file_name = None
        password = None
        logging.info("asking for first name.")
        while first_name == None:
            first_name = self.get_first_name()
            logging.info("First name input result after regex: " + first_name)
        while last_name == None:
            last_name = self.get_last_name()
            logging.info("Last name input result after regex: " + last_name)
        while int_A == None:
            int_A = self.get_int()
            logging.info("First integer input: {a}".format(a=int_A))
        while int_B == None:
            int_B = self.get_int()
            logging.info("Second integer input: {b}".format(b=int_B))
        while input_file_name == None:
            input_file_name = self.get_input_file_name()

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
        num = regex.integer_regex(res)
        logging.info("Integer input after regex: " + num)
        if num != None:
            num = int(num)
            if num > self.max_int_val:
                print('Given integer exceeds maximum allowed value.')
                logging.info("{n} exceeds maximum allowed value.".format(n=num))
                num = None
            elif num < self.min_int_val:
                print('Given integer exceeds minimum allowed value.')
                logging.info("{n}exceeds minimum allowed value.".format(n=num))
                num = None
        return num
    
    def multiply(self, int_A, int_B):
        result = int_A * int_B
        logging.info("Multiplied {a} x {b}, result is {r}".format(a=int_A, b=int_B, r=result))
        if result > self.max_int_val:
            result = self.max_int_val
            logging.info("product exceeded maximum allowed value.")
        elif result < self.min_int_val:
            result = self.min_int_val
            logging.info("product exceeded minimum allowed value.")
        return result

    def get_input_file_name(self):
        print("File Name not to exceed 20 symbols. Only alphabet, numbers, undersore accepted. Case insensitive. Only .txt files accepted")
        #TODO: Implement the file name regex





if __name__ == '__main__':
    main = Main()
    main.start()