import serial
import RPi.GPIO as GPIO
import time
 
#return False
ser=serial.Serial("/dev/ttyACM0",9600)
ser.baudrate = 9600
c = input("bleh")
ser.write(c)
while True:
    read_ser = ser.readline()
    if(read_ser.isnumeric()):
        ##User ID
    else:
        ##Send string back to GUI  
                

