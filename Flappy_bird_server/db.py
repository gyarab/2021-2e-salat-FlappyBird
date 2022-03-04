import sqlite3
import json
import socket


def writeScore():
    top = '{' + '"1st"' + ':' + f'{lis[0][0]}' + ','
    top += '"2nd"' + ':' + f'{lis[1][0]}' + ','
    top += '"3rd"' + ':' + f'{lis[2][0]}' + ','
    top += '"4th"' + ':' + f'{lis[3][0]}' + ','
    top += '"5th"' + ':' + f'{lis[4][0]}' + ','
    top += '"6th"' + ':' + f'{lis[5][0]}' + ','
    top += '"7th"' + ':' + f'{lis[6][0]}' + ','
    top += '"8th"' + ':' + f'{lis[7][0]}' + ','
    top += '"9th"' + ':' + f'{lis[8][0]}' + ','
    top += '"10th"' + ':' + f'{lis[9][0]}' + '}'
    fd = open("list.json", "w")
    fd.write(top)
    fd.close()


def readID():
    o = open('id.json')
    dat = json.load(o)["id"]
    o.close()
    return dat


def writeID():
    with open("id.json", "w") as f:
        dic = {
            "id": id + 1
        }
        json.dump(dic, f)


serv = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
serv.bind(('localhost', 10000))
serv.listen(5)
while True:
    id = readID()
    con = sqlite3.connect('Database.db')
    conn, addr = serv.accept()
    string = ''
    data = conn.recv(4096)
    string += data.decode()
    jsn = json.loads(string)
    name = (jsn['username'])
    score = (jsn['score'])
    cur = con.cursor()
    try:
        cur.execute('insert into users ( id, name, score) values ( ?, ?, ?)', (id, name, score))
        con.commit()
        try:
            writeID()
        except:
            print("file is broken")
    except:
        print("user exists")
        update = """Update users set score = ? where name = ?"""
        data = (score, name)
        cur.execute(update, data)
        con.commit()
        print('user updated')
    cur.execute("SELECT * from users ORDER BY score DESC ;")
    lis = cur.fetchall()
    writeScore()
    con.commit()
    con.close()
    print('done')
