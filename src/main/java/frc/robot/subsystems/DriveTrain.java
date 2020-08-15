/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/
// RIP FRC 2020 

package frc.robot.subsystems;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class DriveTrain extends SubsystemBase {

  private CANSparkMax leftFront;
  private CANSparkMax leftBack;
  private CANSparkMax rightFront;
  private CANSparkMax rightBack;

  private CANEncoder encoderLB;
  private CANEncoder encoderLF;
  private CANEncoder encoderRB;
  private CANEncoder encoderRF;

  private ADXRS450_Gyro gyro;

  private static final int kUltrasonicPort = 0;
  private static final double kValueToInches = 0.125;

  private final AnalogInput m_ultrasonic = new AnalogInput(kUltrasonicPort);

  static DifferentialDrive drive;

  private static Timer t = new Timer();
  private double waitTime = 3;
  private int motor=0;
  // drivetrain main function --- defines vars
  public DriveTrain(int leftF, int leftB, int rightF, int rightB) {
    leftFront = new CANSparkMax(leftF, MotorType.kBrushless);
    leftBack = new CANSparkMax(leftB, MotorType.kBrushless);
    rightFront = new CANSparkMax(rightF, MotorType.kBrushless);
    rightBack = new CANSparkMax(rightB, MotorType.kBrushless);

    encoderLB = new CANEncoder(leftBack);
    encoderLF = new CANEncoder(leftFront);
    encoderRB = new CANEncoder(rightBack);
    encoderRF = new CANEncoder(rightFront);

    SPI.Port kGyroPort = SPI.Port.kOnboardCS0;
    gyro = new ADXRS450_Gyro(kGyroPort);

    SpeedControllerGroup Left = new SpeedControllerGroup(leftFront, leftBack);
    SpeedControllerGroup Right = new SpeedControllerGroup(rightFront, rightBack);

    drive = new DifferentialDrive(Left, Right);
  }

  //basic drive
  public void drive(double speed, double turn) {
    drive.arcadeDrive(speed, turn, true);
  }
  public void stop() {
    drive.arcadeDrive(0, 0, true);
  }

  //gyro code
  public void resetGyro() {
    gyro.reset();
  }

  public void calibrateGyro() {
    gyro.calibrate();
  }

  //auto drive && teletop drive
  public void autoDrive(double speed, double turnSpeed) {
    if (turnSpeed != 0.0) {
      drive(speed, turnSpeed);
    } else if (turnSpeed == 0.0) {
      driveStraightGyro(speed);
      System.out.println("Running Gyro");
    }
  }

  //has deband
  public void telopDrive(double speed, double turnSpeed) {
    double deadband = 0.1;
    if (turnSpeed >= deadband || turnSpeed <= deadband) {
      drive(speed, turnSpeed);
    } else if (turnSpeed == 0.0) {
      driveStraightGyro(speed);
      System.out.println("Running Gyro");
    }
  }
  // public void teleop(double speed, double turnSpeed) {
  // double bumpThreshold = 1.9; // degrees turned in one 80hz interval?
  // if(turnSpeed != 0.0 && gyro.getRate() > bumpThreshold){
  // drive(speed, turnSpeed);
  // }else if(turnSpeed == 0.0){
  // driveStraightGyro(speed);
  // System.out.println("Running Gyro");
  // }
  // }

  //auto turning
  //In need of testing
  public boolean turnToAngle(int angle, double speed) {
    if (angle > gyro.getAngle()) {
      drive(0, speed);
      return false;
    } else {
      stop();
      return true;
    }
  }

  //dirve straight
  public void driveStraightGyro(double speed) {
    double p = 0.05;
    double error = -gyro.getAngle();
    double turn = p * error;
    drive.arcadeDrive(speed, turn);
  }

  public void encodersOnDashboard() {
    //puts encoder values on dashboard
    SmartDashboard.putNumber("Encoder Left Back", getEncoderLB());
    SmartDashboard.putNumber("Encoder Right Back", getEncoderRB());
    SmartDashboard.putNumber("Encoder Left Front", getEncoderLF());
    SmartDashboard.putNumber("Encoder Right Front", getEncoderRF());
  }

  //return values of each encoder
  public double getEncoderLB() {
    return encoderLB.getPosition();
  }

  public double getEncoderLF() {
    return encoderLF.getPosition();
  }

  public double getEncoderRB() {
    return encoderRB.getPosition();
  }

  public double getEncoderRF() {
    return encoderRF.getPosition();
  }

  //gets ultrasonic distance
  public double getUltrasonic() {
    return m_ultrasonic.getValue() * kValueToInches;
  }

  //test code
  public void test(double speed) {
    startTimer();
    for(motor=0; motor<4 ;motor++){
      if (motor == 0) {
        leftFront.set(speed);
      }else if(motor == 1){
        leftFront.set(0);
        leftBack.set(speed);
      }else if(motor == 2){
        leftBack.set(0);
        rightFront.set(speed);
      }else if(motor  == 3){
        rightFront.set(0);
        rightBack.set(speed);
      }else if(motor  == 4){
        rightBack.set(speed);
      }
      wait(waitTime);
    }
  }
  private void wait(double seconds){
    while(t.get() < seconds){
       
    }
  }
  private void startTimer(){
    t.start();
  }
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}