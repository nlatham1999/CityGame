import subprocess

cmd = 'javac CityGame.java Players.java Business.java GoldMine.java'
proc = subprocess.Popen(cmd, shell=True)
cmd = 'java CityGame'
proc = subprocess.Popen(cmd, shell=True)


