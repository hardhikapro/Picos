#db.py
import os
import pymysql
from flask import jsonify

db_user = os.environ.get('picos-sqldev')
db_password = os.environ.get('Picos-23')
db_name = os.environ.get('CLOUD_SQL_DATABASE_NAME')
db_connection_name = os.environ.get('deep-span-387504:asia-southeast2:picos-sqldev')

#generate an open connection to the database function
def open_connection():
    unix_socket = '/cloudsql/{}'.format(db_connection_name)
    conn = pymysql.connect(user=db_user, password=db_password, unix_socket=unix_socket, db=db_name) # type: ignore
    return conn


#generate a function to get the list of picos user results from the database
def get_list():
    conn = open_connection()
    with conn.cursor() as cursor:
        cursor.execute('SELECT * FROM ')
        songs = cursor.fetchall()
        songs = jsonify(songs)
    conn.close()
    return songs
def add_list(song):
    conn = open_connection()
    with conn.cursor() as cursor:
        cursor.execute('INSERT INTO songs (title, artist, genre) VALUES(%s, %s, %s)', (song["title"], song["artist"], song["genre"]))
    conn.commit()
    conn.close()
