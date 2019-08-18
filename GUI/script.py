import serial
import RPi.GPIO as GPIO
import time
import sys

def is_number(s):
    try:
        float(s)
        return True
    except ValueError:
        pass
 
    try:
        import unicodedata
        unicodedata.numeric(s)
        return True
    except (TypeError, ValueError):
        pass

ser=serial.Serial("/dev/ttyACM1",9600)
ser.baudrate = 9600

c = sys.argv[1].encode('utf-8')
print(c)
ser.write(c)
if(c == "B"):
    exit()
while True:
    read_ser = ser.readline()
    if is_number(read_ser):
        print("Yay")
        break
    else:
        print(read_ser)
                

