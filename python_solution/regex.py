# Regexes used to sanitize user input
# Created by Mario, April 26th 2023

import re



class Regexes():
    
    def first_name_regex(str):
        regex = '^[A-Za-z]{1,50}$'
        result = re.search(regex, str)
        return result.group(0)

    def last_name_regex(str):
        regex = '^([A-Za-z] ?){1,50}$'
        result = re.search(regex, str)
        return result.group(0)