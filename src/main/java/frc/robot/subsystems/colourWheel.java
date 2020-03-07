/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

<<<<<<< Updated upstream
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.revrobotics.ColorSensorV3;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
=======
import com.ctre.phoenix.motorcontrol.ControlMode;//imports stuff
import com.ctre.phoenix.motorcontrol.can.TalonSRX;//imports stuff
import com.revrobotics.ColorSensorV3;//imports stuff

import edu.wpi.first.wpilibj.I2C;//imports stuff
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;//imports stuff
import edu.wpi.first.wpilibj.util.Color;//imports stuff
import edu.wpi.first.wpilibj2.command.SubsystemBase;//imports stuff
import frc.robot.Constants;//imports stuff
>>>>>>> Stashed changes

public class ColourWheel extends SubsystemBase {
  /**
   * Creates a new findColour.
   */
  private final I2C.Port i2cPort = I2C.Port.kOnboard;
  private final ColorSensorV3 m_colorSensor = new ColorSensorV3(i2cPort);
  public static TalonSRX motor = new TalonSRX(Constants.colourWheel);
  private double speed = 0.4;
  private double redV = 0;
  private double greenV = 0;
  private double blueV = 0;
  private double redRange=0.1;
  private double blueRange=0.1;
  private double greenRange=0.1;
  private double red[] = {0.47,0.36,0.13};
  private double blue[] = {0.15,0.42,0.42};
  private double green[] = {0.16,0.56,0.25};
  private double yellow[] = {0.32,0.53,0.13};
  private String colours[] = {"Red", "Yellow", "Blue", "Green","Other"};
  private int referenceColour = 0;
  private int counter = 0;

  public ColourWheel() {
   
    // double IR = m_colorSensor.getIR();
    // // SmartDashboard.putNumber("Red", detectedColor.red);
    // // SmartDashboard.putNumber("Green", detectedColor.green);
    // // SmartDashboard.putNumber("Blue", detectedColor.blue);
    // SmartDashboard.putNumber("IR", IR);
    // int proximity = m_colorSensor.getProximity();
    //dont h8 be gr8 brad waz here

    // SmartDashboard.putNumber("Proximity", proximity);

  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  //0 --> Blue --> 0 255 255
  //1 --> Yellow --> 255 255 0
  //2 --> Red --> 255 0 0
  //3 --> Green --> 0 255 0
  public void putColour(){
    Color detectedColour = m_colorSensor.getColor();
    SmartDashboard.putNumber("Red", detectedColour.red);
    SmartDashboard.putNumber("Green", detectedColour.green);
    SmartDashboard.putNumber("Blue", detectedColour.blue);
  }

  public double[] returnColour(){
    Color detectedColour = m_colorSensor.getColor();
    return new double[] {detectedColour.red, detectedColour.green, detectedColour.blue};
  }

  public void findColour(int colour){
    Color detectedColour = m_colorSensor.getColor();
    int currentColour = 0;
    redV = detectedColour.red;
    blueV = detectedColour.blue;
    greenV = detectedColour.green;
    currentColour = colourDetected();

    SmartDashboard.putString("Colour Detected:", colours[currentColour]);
    if(currentColour == colour){
      motor.set(ControlMode.PercentOutput,0);
    }else{
      motor.set(ControlMode.PercentOutput, speed);
    }
  }

  public void countRotations(int numOfRotations){
    int currentColour = colourDetected();
    
    if(referenceColour  == 5){
      referenceColour = currentColour;
    }
    if(currentColour == referenceColour && counter % 2 == 0){
      if(currentColour != referenceColour && counter % 2 == 0){
        counter++;
      }
    }else if(currentColour == referenceColour && counter % 2 != 0){
      counter++;
    }
    if((counter/2) == numOfRotations){
      motor.set(ControlMode.PercentOutput, 0);
    }else{
      motor.set(ControlMode.PercentOutput,speed);
    }
  } 

  public int colourDetected(){
    Color detectedColour = m_colorSensor.getColor();
    redV = detectedColour.red;
    blueV = detectedColour.blue;
    greenV = detectedColour.green;
    if(inRange(redV, red[0] - redRange, red[0] + redRange, greenV, red[1] - greenRange, red[1] + greenRange, blueV, red[2] - blueRange, red[2] + blueRange)){
      //red
      return 0;
    }else if(inRange(redV, yellow[0] - redRange, yellow[0] + redRange, greenV, yellow[1] - greenRange, yellow[1] + greenRange, blueV, yellow[2] - blueRange, yellow[2] + blueRange)){
      //yellow
      return 1;
    }
    else if(inRange(redV, blue[0] - redRange, blue[0] + redRange, greenV, blue[1] - greenRange, blue[1] + greenRange, blueV, blue[2] - blueRange, blue[2] + blueRange)){
      //blue
      return 2;
    }
    else if(inRange(redV, green[0] - redRange, green[0] + redRange, greenV, green[1] - greenRange, green[1] + greenRange, blueV, green[2] - blueRange, green[2] + blueRange)){
      //green
      return 3;
    }else{
      return 4;
    }
  }

  public void stop(){
    motor.set(ControlMode.PercentOutput, 0);
  }
  public boolean inRange(double redV,  double redMin, double redMax,double greenV,  double greenMin, double greenMax,double blueV,  double blueMin, double blueMax) {
    return redV >= redMin && redV <= redMax && greenV >= greenMin && greenV <= greenMax && blueV >= blueMin && blueV <= blueMax;
  }
}