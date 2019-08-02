

#include "FPS_GT511C3.h"
#include "SoftwareSerial.h"

#define arduino_rx_pin 4
#define arduino_tx_pin 5
// FPS (TX) is connected to pin 4 (Arduino's Software RX)
// FPS (RX) is connected through a converter to pin 5 (Arduino's Software TX)
FPS_GT511C3 fps(arduino_rx_pin, arduino_tx_pin); // (Arduino SS_RX = pin 4, Arduino SS_TX = pin 5)

void blinkLED()
{
  // FPS Blink LED Test
  fps.SetLED(true); // turn on the LED inside the fps
  delay(1000);
  fps.SetLED(false);// turn off the LED inside the fps
  delay(1000);  
}
void Enroll()
{
  // Enroll test

  // find open enroll id
  int enrollid = 0;
  bool usedid = true;
  while (usedid == true)
  {
    usedid = fps.CheckEnrolled(enrollid);
    if (usedid==true) enrollid++;
  }
  fps.EnrollStart(enrollid);

  // enroll
  Serial.print("Press finger to Enroll #");
  Serial.println(enrollid);
  while(fps.IsPressFinger() == false) delay(100);
  bool bret = fps.CaptureFinger(true);
  int iret = 0;
  if (bret != false)
  {
    Serial.println("Remove finger");
    fps.Enroll1(); 
    while(fps.IsPressFinger() == true) delay(100);
    Serial.println("Press same finger again");
    while(fps.IsPressFinger() == false) delay(100);
    bret = fps.CaptureFinger(true);
    if (bret != false)
    {
      Serial.println("Remove finger");
      fps.Enroll2();
      while(fps.IsPressFinger() == true) delay(100);
      Serial.println("Press same finger yet again");
      while(fps.IsPressFinger() == false) delay(100);
      bret = fps.CaptureFinger(true);
      if (bret != false)
      {
        Serial.println("Remove finger");
        iret = fps.Enroll3();
        if (iret == 0)
        {
          Serial.println("Enrolling Successful");
        }
        else
        {
          Serial.print("Enrolling Failed with error code:");
          Serial.println(iret);
        }
      }
      else Serial.println("Failed to capture third finger");
    }
    else Serial.println("Failed to capture second finger");
  }
  else Serial.println("Failed to capture first finger");
}

int Validate()
{
  int ID;
  // Identify fingerprint test
  if (fps.IsPressFinger())
  {
    fps.CaptureFinger(false);
    int id = fps.Identify1_N();
    ID=id;
       /*Note:  GT-521F52 can hold 3000 fingerprint templates
                GT-521F32 can hold 200 fingerprint templates
                 GT-511C3 can hold 200 fingerprint templates. 
                GT-511C1R can hold 20 fingerprint templates.
       Make sure to change the id depending on what
       model you are using */
    if (id <200) //<- change id value depending model you are using
    {//if the fingerprint matches, provide the matching template ID
      Serial.print("Verified ID:");
      Serial.println(id);
    }
    else
    {//if unable to recognize
      Serial.println("Finger not found");
    }
  }
  else
  {
    Serial.println("Please press finger");
  }
  delay(100);
  return ID;
}

void setup()
{
  Serial.begin(9600); //set up Arduino's hardware serial UART
  fps.UseSerialDebug = true; // so you can see the messages in the serial debug screen
  fps.Open(); //send serial command to initialize fps
}

void loop()
{
  while(Serial.available())
  {
    char c= Serial.read();
    // Blink scanner to test
    if(c=='B')
    {
      blinkLED();
    }
    //Enrol fingerprint
    else if(c== 'E')
    {
      Enroll();
    }
    //Detect fingerprint validity
    else if(c=='V')
    {
      Validate();
    }
  }
}
