// Arduino headers
#include <SoftwareSerial.h>

// Other headers
#include "Adafruit_APDS9960.h"


// Global objects

// Create second serial port using pins 3 and 2 on the UNO board.
// Note: pins 0 and 1 are default serial port and we use them for debug messages
SoftwareSerial bluetooth(3,2);      // rx-3, tx-2

// Initialize the gesture sensor
Adafruit_APDS9960 apds = Adafruit_APDS9960();



// One shot function for board initialization.
void setup() {
    // setup my default serial port. This is the port where i send debug messages
    Serial.begin(9600);
 

    // setup the BT serial port
    bluetooth.begin(9600);

    if (apds.begin()) {
        Serial.println("Sensor initialized.");
    } else {
        Serial.println("Failed to initialize the sensor.");
    }

    // gesture mode will be entered once proximity mode senses something close
    apds.enableProximity(true);
    apds.enableGesture(true);
}



// forever
// Read gesture byte. Print human readable debug for what i got. Then send the
// gesture on BT air via the second serial port.
//
void loop() 
{
    // read one byte from gesture device
 int8_t gesture = apds.readGesture();
  int incomingByte ;
  
  while (bluetooth.available())
  {
    Serial.println(gesture);
    
   // for(int a = 0; a<3; a++)
  //  {
   //
    
   
   
  //  }


    int8_t writed_bytes = bluetooth.write(gesture);
    incomingByte = bluetooth.read();
    
    switch(incomingByte)
    {
      case 49:
      incomingByte = 1;
      break;
      case 50:
      incomingByte = 2;
      break;
      case 51:
      incomingByte = 3;
      break;
      case 52:
      incomingByte = 4;
      break;
    }
    
    Serial.println(incomingByte, DEC);
    
    if (writed_bytes != 1) 
    Serial.println("cannot write on the BT");
     
    //if(apds.gestureValid())
      switch (gesture) 
      {
        case APDS9960_DOWN : Serial.println("got down");
        if(gesture == incomingByte){
                  Serial.println("Good");
        }
        break;
        case APDS9960_UP : Serial.println("got up");
        if(gesture == incomingByte){
                  Serial.println("Good");
        }
        break;
        case APDS9960_LEFT : Serial.println("got left");
        if(gesture == incomingByte){
                  Serial.println("Good");
        }
        break;
        case APDS9960_RIGHT : Serial.println("got right");
        if(gesture == incomingByte){
                  Serial.println("Good");
        }
        break;
     }
    


   //if(gesture == 1 || gesture ==2 || gesture ==3|| gesture ==4) //if connections are available for BT
     // write one byte in serial device (the BT module)
    
  }
}
