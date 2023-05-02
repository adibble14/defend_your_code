from logger import logging
import regex
import hasher




class Model():

    def __init__(self) -> None:
        self.max_int_val = 2147483647
        self.min_int_val = -2147483648
        self.first_name = None
        self.last_name = None
        self.int_A = None
        self.int_B = None
        self.input_file_name = None
        self.output_file_name = None
        self.password = None

    def set_first_name(self):
        print("First Name must consist of only alphabetical symbols. Upper and lower case is fine. Maximum 50 symbols.")
        res = input("Enter First Name: ")
        logging.info("User First Name Input: " + res)
        self.first_name = regex.first_name_regex(res)
        return self.first_name
    
    def set_last_name(self):
        print("Last Name must consist of only alphabetical symbols and spaces. Upper and lower case is fine. Maximum 50 symbols.")
        res = input("Enter Last Name: ")
        logging.info("User Last Name Input: " + res)
        self.last_name = regex.last_name_regex(res)
        return self.last_name
    
    def set_first_int(self):
        self.int_A = self.set_int_helper()

    def set_second_int(self):
        self.int_B = self.set_int_helper()


    def set_int_helper(self):
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
    
    def multiply(self):
        result = self.int_A * self.int_B
        logging.info("Multiplied {a} x {b}, result is {r}".format(a=self.int_A, b=self.int_B, r=result))
        if result > self.max_int_val:
            result = self.max_int_val
            logging.info("product exceeded maximum allowed value.")
        elif result < self.min_int_val:
            result = self.min_int_val
            logging.info("product exceeded minimum allowed value.")
        return result
    
    def set_input_file_name(self):
        logging.info("Setting input file name.")
        self.input_file_name = self.file_name_helper()

    def set_output_file_name(self):
        logging.info("Setting output file name.")
        res = self.output_file_name()
        if self.input_file_name == self.output_file_name:
            logging.info("output file name is same as input file name. Asking user again for output file name.")
            self.set_output_file_name()
        self.output_file_name = res

    def file_name_helper(self):
        print("File Name not to exceed 20 symbols. Only alphabet, numbers, undersore accepted. Case insensitive. Only .txt files accepted")
        res = input("Enter file name: ")
        logging.info("Given file name: " + res)
        return regex.file_name_regex(res)
    
    def set_password(self):
        print("Password should contain at least one uppercase letter, at least one lowercase letter, at least one digit, at least one symbol, and no more than three consecutive lowercase letters.")
        res = input("Enter password: ")
        logging.info("Given password: " + res)
        self.password = regex.password_regex(res)
        return self.password