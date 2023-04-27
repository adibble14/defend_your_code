# Tests for Regexes
# Created by Mario, April 26th 2023

import regex
import unittest

class TestRegexes(unittest.TestCase):

    def test_valid_first_name_regex(self):
        test_case ='Mario'
        self.assertEqual(test_case, regex.Regexes.first_name_regex(test_case))


if __name__ == '__main__':
    unittest.main()



