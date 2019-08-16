import serial
import RPi.GPIO as GPIO
import time
import sys
 

ser=serial.Serial("/dev/ttyACM0",9600)
ser.baudrate = 9600
arg = sys.argv[1]

ser.write(arg)
while True:
    read_ser = ser.readline()
    if(arg == "V" and read_ser.isnumeric()):
        print(read_ser)
        break
    else:
        print(read_ser)