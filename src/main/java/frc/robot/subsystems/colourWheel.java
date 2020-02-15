/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.revrobotics.ColorSensorV3;

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class colourWheel extends SubsystemBase {
  /**
   * Creates a new findColour.
   */
  private final I2C.Port i2cPort = I2C.Port.kOnboard;
  private final ColorSensorV3 m_colorSensor = new ColorSensorV3(i2cPort);
  private TalonSRX motor = new TalonSRX(Constants.colourWheel);
  private int speed = 1;
  private double redValue = 0;
  private double greenValue = 0;
  private double blueValue = 0;
  private double redRange=0.02;
  private double blueRange=0.02;
  private double greenRange=0.02;
  private double red[] = {0.47,0.36,0.13};
  private double blue[] = {0.15,0.42,0.42};
  private double green[] = {0.2,0.5,0.2};
  private double yellow[] = {0.32,0.53,0.24};

  public colourWheel() {
   
    // double IR = m_colorSensor.getIR();
    // // SmartDashboard.putNumber("Red", detectedColor.red);
    // // SmartDashboard.putNumber("Green", detectedColor.green);
    // // SmartDashboard.putNumber("Blue", detectedColor.blue);
    // SmartDashboard.putNumber("IR", IR);
    // int proximity = m_colorSensor.getProximity();

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
  public void findColour(int colour){
    Color detectedColour = m_colorSensor.getColor();
    int currentColour = 0;
    redValue = detectedColour.red;
    blueValue = detectedColour.blue;
    greenValue = detectedColour.green;
    SmartDashboard.putNumber("Red", detectedColour.red);
    SmartDashboard.putNumber("Green", detectedColour.green);
    SmartDashboard.putNumber("Blue", detectedColour.blue);
    //Red
    if(redValue >= red[0] + redRange && redValue <= red[0] - redRange && blueValue >= red[1] + blueRange && blueValue <= red[1] - blueRange && greenValue >= red[2]+greenRange && greenValue <= red[2]-greenRange){
      currentColour = 0;
    }
    //Yellow
    if(redValue >=  yellow[0] + redRange && redValue <= yellow[0] - redRange && blueValue >= yellow[1] + blueRange && blueValue <= yellow[1] - blueRange && greenValue >= yellow[2] + greenRange && greenValue <= yellow[2] - greenRange){
      currentColour = 1;
    }
    //Blue
    if(redValue >=  blue[0] + redRange && redValue <= blue[0] - redRange && blueValue >= blue[1] + blueRange && blueValue <= blue[1] - blueRange && greenValue >= blue[2] + greenRange && greenValue <= blue[2] - greenRange){
      currentColour = 2;
    }
    //Green
    if(redValue >=  green[0] + redRange && redValue <= green[0] - redRange && blueValue >= green[1] + blueRange && blueValue <= green[1] - blueRange && greenValue >= green[2] + greenRange && greenValue <= green[2] - greenRange){
      currentColour = 3;
    }
    if(currentColour == colour){
      motor.set(ControlMode.PercentOutput, 0);
    }else{
      motor.set(ControlMode.PercentOutput,speed);
    }
  }
}

