# Tests for
# Created by Mario, April 26th 2023

import regex
import unittest

class TestRegexes(unittest.TestCase):

    #Valid First Name Regex
    def test_valid_first_name_regex(self):
        test_case ='Mario'
        self.assertEqual(test_case, regex.first_name_regex(test_case))

    def test_valid_first_name_regex_max_length(self):
        test_case ='abcdefghijklmnopqrsabcdefghijaaabcdefghijklmnopqrs'
        self.assertEqual(test_case, regex.first_name_regex(test_case))

    #Invalid First Name Regex
    def test_invalid_first_name_regex_max_length(self):
        test_case ='abcdefghijklmnopqrsabcdefghijaaabcdefghijklmnopqrsaw'
        self.assertEqual(None, regex.first_name_regex(test_case))

    def test_invalid_first_name_regex_empty(self):
        test_case =''
        self.assertEqual(None, regex.first_name_regex(test_case))

    #Valid Last Name Regex
    def test_valid_last_name_regex(self):
        test_case ='Flores'
        self.assertEqual(test_case, regex.last_name_regex(test_case))

    def test_valid_last_name_regex_space(self):
        test_case ='Flores Vences'
        self.assertEqual(test_case, regex.last_name_regex(test_case))

    #Invalid Last Name Regex

    def test_invalid_last_name_regex(self):
        test_case ='abcdefghijklmnopqrsabcdefghijaaabcdefghijklmnopqrsaw'
        self.assertEqual(None, regex.last_name_regex(test_case))

    #Valid Integer regex
    def test_valid_integer_regex(self):
        test_case = '123456'
        self.assertEqual(test_case, regex.integer_regex(test_case))

    def test_valid_integer_regex_base_case(self):
        test_case = '0'
        self.assertEqual(test_case, regex.integer_regex(test_case))

    def test_valid_integer_regex_max_digits(self):
        test_case = '1234567890'
        self.assertEqual(test_case, regex.integer_regex(test_case))

    def test_valid_integer_regex_max_digits_negative(self):
        test_case = '-1234567890'
        self.assertEqual(test_case, regex.integer_regex(test_case))

    #Invalid Integer regex
    def test_invalid_integer_regex_max(self):
        test_case = '12345678900'
        self.assertEqual(None, regex.integer_regex(test_case))

    def test_invalid_integer_regex_empty(self):
        test_case = ''
        self.assertEqual(None, regex.integer_regex(test_case))




if __name__ == '__main__':
    unittest.main()



