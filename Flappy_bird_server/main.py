from fastapi import FastAPI, Request
from fastapi.responses import HTMLResponse
from fastapi.templating import Jinja2Templates
import sqlite3
import json

app = FastAPI()
templates = Jinja2Templates(directory="templates")


@app.get("/", response_class=HTMLResponse)
async def root(request: Request):
    con = sqlite3.connect('Database.db')
    cur = con.cursor()
    cur.execute('select id, name, score from users;')
    f = open('list.json')
    data = json.load(f)
    fetch = cur.fetchall()
    p1 = fetch[data['1st'] - 1]
    p2 = fetch[data['2nd'] - 1]
    p3 = fetch[data['3rd'] - 1]
    p4 = fetch[data['4th'] - 1]
    p5 = fetch[data['5th'] - 1]
    p6 = fetch[data['6th'] - 1]
    p7 = fetch[data['7th'] - 1]
    p8 = fetch[data['8th'] - 1]
    p9 = fetch[data['9th'] - 1]
    p10 = fetch[data['10th'] - 1]
    return templates.TemplateResponse('index.html',
                                      {'request': request, 'p1': p1, 'p2': p2, 'p3': p3, 'p4': p4, 'p5': p5, 'p6': p6,
                                       'p7': p7,
                                       'p8': p8, 'p9': p9, 'p10': p10})


@app.get("/{name}")
async def say_hello(name: str, request: Request):
    try:
        con = sqlite3.connect('Database.db')
        cur = con.cursor()
        cur.execute("SELECT * FROM users WHERE name = ? ", (name,))
        score = cur.fetchone()[2]

        return templates.TemplateResponse('user.html', {'request': request, 'score': score, 'name': name})
    except:
        return {"message": f'User: {name} not found'}
