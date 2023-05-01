# Regexes used to sanitize user input
# Created by Mario, April 26th 2023

import re



def first_name_regex(str):
    regex = '^[A-Za-z]{1,50}$'
    result = re.search(regex, str)
    if result == None:
        return None
    return result.group(0)

def last_name_regex(str):
    regex = '^([A-Za-z] ?){1,50}$'
    result = re.search(regex, str)
    if result == None:
        return None
    return result.group(0)

def integer_regex(str):
    regex = '^-?\d{1,10}$'
    result = re.search(regex, str)
    if result == None:
        return None
    return result.group(0)

def file_name_regex(str):
    regex = '^\w{1,20}\.txt$'
    result = re.search(regex, str)
    if result == None:
        return None
    return result.group(0)

def password_regex(str):
    regex = '^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-])(?![a-z]{4,}).{10,}$'
    result = re.search(regex, str)
    if result == None:
        return None
    return result.group(0)

    