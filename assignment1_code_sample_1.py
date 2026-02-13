import os
import re
import subprocess
import pymysql  # type: ignore[import-untyped]
from urllib.request import urlopen

# A02: Use environment variables instead of hardcoded credentials
db_config = {
    'host': os.environ.get('DB_HOST', 'mydatabase.com'),
    'user': os.environ.get('DB_USER', ''),
    'password': os.environ.get('DB_PASSWORD', '')
}

def get_user_input():
    # A06: Validate and sanitize user input - limit length, allow only safe characters
    user_input = input('Enter your name: ').strip()
    if len(user_input) > 100:
        raise ValueError('Input too long')
    if not re.match(r'^[\w\s\-\.]+$', user_input):
        raise ValueError('Invalid characters in name')
    return user_input

def send_email(to, subject, body):
    # A05: Avoid command injection - use subprocess with list args, no shell
    subprocess.run(
        ['mail', '-s', subject, to],
        input=body.encode(),
        capture_output=True,
        timeout=10,
        check=False
    )

def get_data():
    # A04: Use HTTPS for encrypted transmission
    url = 'https://insecure-api.com/get-data'
    data = urlopen(url).read().decode()
    return data

def save_to_db(data):
    # A05: Use parameterized query to prevent SQL injection
    query = "INSERT INTO mytable (column1, column2) VALUES (%s, %s)"
    connection = pymysql.connect(**db_config)
    cursor = connection.cursor()
    cursor.execute(query, (data, 'Another Value'))
    connection.commit()
    cursor.close()
    connection.close()

if __name__ == '__main__':
    user_input = get_user_input()
    data = get_data()
    save_to_db(data)
    send_email('admin@example.com', 'User Input', user_input)
