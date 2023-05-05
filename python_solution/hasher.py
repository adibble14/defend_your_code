# adds salt and hashes a password
# technique from https://docs.python.org/3/library/secrets.html
import secrets
import string
import hashlib

''' Sometimes returns None

def get_salt():
    alphabet = string.ascii_letters + string.digits
    while True:
        salt = ''.join(secrets.choice(alphabet) for i in range(10))
        if(any(c.isupper() for c in salt)
            and any(c.isupper() for c in salt)
            and sum(c.isdigit() for c in salt) >= 3):
            break
        return salt
'''

def get_salt():
    alphabet = string.ascii_letters + string.digits
    return ''.join(secrets.choice(alphabet) for i in range(10))
    
def hash(str):
    p = str
    return hashlib.sha256(p.encode()).hexdigest()


