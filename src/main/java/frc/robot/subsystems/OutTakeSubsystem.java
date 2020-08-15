/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/
// RIP FRC 2020 

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.revrobotics.CANEncoder;
import com.revrobotics.CANPIDController;
import com.revrobotics.CANSparkMax;
import com.revrobotics.ControlType;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class OutTakeSubsystem extends SubsystemBase {

  //main vars
  private CANSparkMax MiddleMotor;
  private TalonSRX RightMotor;
  private TalonSRX LeftMotor;
  private CANPIDController pid;
  static int speed = -1;
  static CANEncoder outtakeEncoder;

  // testing vars
  private int motor=0;
  private Timer delay = new Timer();
  private double waitTime;

  //running delay
  static double startTime;
  static boolean runningFull = false;


  public OutTakeSubsystem() {

    LeftMotor = new TalonSRX(Constants.OutTakeLeft);
    MiddleMotor = new CANSparkMax(Constants.OutTakeMiddle,MotorType.kBrushless);
    RightMotor = new TalonSRX(Constants.OutTakeRight);
    pid = new CANPIDController(MiddleMotor);
    startTime = Timer.getFPGATimestamp();
    outtakeEncoder = new CANEncoder(MiddleMotor);

  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void move(double speed, double speed2) {
    //get current velocity of outtake
    if(getVelocity() < -4030 && runningFull == true){
      RightMotor.set(ControlMode.PercentOutput, speed2);
      LeftMotor.set(ControlMode.PercentOutput, speed2);
    }else if(getVelocity() < -4030 && runningFull == false){
      runningFull = true;
    }
     MiddleMotor.set(speed);
  }

  public void stop(){
    RightMotor.set(ControlMode.PercentOutput, 0);
    LeftMotor.set(ControlMode.PercentOutput, 0);
    MiddleMotor.set(0);
  }

  public void changeRunningFullToFalse(){
    runningFull = false;
  }

  public void movePID(double maxRPM, double p, double i, double d, double ff){
    //pid
    pid.setP(p);
    pid.setI(i);
    pid.setD(d);
    pid.setIZone(0);
    pid.setFF(ff);
    pid.setOutputRange(0, 1);
    pid.setReference(maxRPM, ControlType.kVelocity);
  }

  public double getVelocity(){
    return outtakeEncoder.getVelocity();
  }
  public double getPosition(){
    return outtakeEncoder.getPosition();
  }

  public void testShot(double testSpeed){
    if (motor == 0) {
      MiddleMotor.set(testSpeed);
      startTimer();
      motor++;
    }else if(motor == 1 && delay.get() == waitTime*1){
      MiddleMotor.set(0);
      RightMotor.set(ControlMode.PercentOutput, testSpeed);
      motor++;
    }else if(motor == 2 && delay.get() == waitTime*2){
      RightMotor.set(ControlMode.PercentOutput, 0);
      LeftMotor.set(ControlMode.PercentOutput, testSpeed);
      motor++;
    }

  }

  private void startTimer(){
    delay.start();
  }
}
