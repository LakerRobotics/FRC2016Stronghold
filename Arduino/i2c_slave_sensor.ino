
#include <Wire.h>
#include "Arduino.h"

class Ultrasonic
{
   public:
      Ultrasonic(int pin);
        void DistanceMeasure(void);
      long microsecondsToCentimeters(void);
      
   private:
      int _pin;//pin number of Arduino that is connected with SIG pin of Ultrasonic Ranger.
        long duration;// the Pulse time received;
};
Ultrasonic::Ultrasonic(int pin)
{
   _pin = pin;
}
/*Begin the detection and get the pulse back signal*/
void Ultrasonic::DistanceMeasure(void)
{
    pinMode(_pin, OUTPUT);
   digitalWrite(_pin, LOW);
   delayMicroseconds(2);
   digitalWrite(_pin, HIGH);
   delayMicroseconds(5);
   digitalWrite(_pin,LOW);
   pinMode(_pin,INPUT);
   duration = pulseIn(_pin,HIGH);
}
/*The measured distance from the range 0 to 400 Centimeters*/
long Ultrasonic::microsecondsToCentimeters(void)
{
   return duration/29/2;   
}

Ultrasonic ultrasonic(7);

void setup() {
   Wire.begin(8);                // join i2c bus with address #8
   Wire.onRequest(requestEvent); // register event
}

void loop() {
   delay(10);
}

// function that executes whenever data is requested by master
// this function is registered as an event, see setup()
void requestEvent() {
   long RangeInCentimeters;
   ultrasonic.DistanceMeasure();// get the current signal time;
   RangeInCentimeters = ultrasonic.microsecondsToCentimeters();//convert the time to centimeters
   Wire.write(RangeInCentimeters); // respond with message of 6 bytes
   // as expected by master
}
