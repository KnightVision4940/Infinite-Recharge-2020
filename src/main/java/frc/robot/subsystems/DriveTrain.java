/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.revrobotics.CANEncoder;
// import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import com.revrobotics.CANSparkMax;
// import com.revrobotics.SparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.Timer;
// import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import edu.wpi.first.wpilibj.Encoder;

public class DriveTrain extends SubsystemBase {
  /**
   * Creates a new DriveTrain.
   */
  
  // static WPI_VictorSPX leftFront;
  // static WPI_VictorSPX leftBack;
  // static WPI_VictorSPX rightFront;
  // static WPI_VictorSPX rightBack;
  static CANSparkMax leftFront;
  static CANSparkMax leftBack;
  static CANSparkMax rightFront;
  static CANSparkMax rightBack;

  static CANEncoder encoderLB;
  static CANEncoder encoderLF;
  static CANEncoder encoderRB;
  static CANEncoder encoderRF;

  static ADXRS450_Gyro gyro;

  static DifferentialDrive drive;
    
  public DriveTrain(int leftF,int leftB, int rightF, int rightB) {
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
    // leftFront = new WPI_VictorSPX(leftF);
    // leftBack = new WPI_VictorSPX(leftB);
    // rightFront = new WPI_VictorSPX(rightF);
    // rightBack = new WPI_VictorSPX(rightB);

    SpeedControllerGroup Left = new SpeedControllerGroup(leftFront,leftBack);
    SpeedControllerGroup Right = new SpeedControllerGroup(rightFront, rightBack);
    
    drive = new DifferentialDrive(Left, Right);
  }
  //Without Gyro Functionality
  public void drive(double speed, double turn){
    // Encoder motorEncoder = new Encoder(0, 1);
    // motorEncoder.setDistancePerPulse(1./256.);
    // if(motorEncoder.get() < 1000) {  } // < --- help
    drive.arcadeDrive(speed, turn,true);
    // leftBack.set(1);
  }
  public void resetGyro(){
    gyro.reset();
  }
  public void calibrateGyro(){
    gyro.calibrate();
  }
  public void stop(){
    drive.arcadeDrive(0, 0,true);
  }
  public void autoDrive(double speed, double turnSpeed){
    if(turnSpeed != 0.0){
      drive(speed, turnSpeed);
    }else if(turnSpeed == 0.0){
      driveStraightGyro(speed);
      System.out.println("Running Gyro");
    }
  }

  public boolean turnToAngle(int angle, double speed){
    if(angle > gyro.getAngle()){
      drive(0,1);
    }else{

    }
  }

  public void driveStraightGyro(double speed){
    double p = 0.05;
    double error = -gyro.getAngle(); 
    double turn = p * error;
    drive.arcadeDrive(speed, turn);   
  }

  public void encodersOnDashboard(){
    SmartDashboard.putNumber("Encoder Left Back", encoderLB.getPosition());
    SmartDashboard.putNumber("Encoder Right Back", encoderLF.getPosition());
    SmartDashboard.putNumber("Encoder Left Front", encoderRB.getPosition());
    SmartDashboard.putNumber("Encoder Right Front", encoderRF.getPosition());
  }

  public double getEncoderLB(){
    return encoderLB.getPosition();
  }

  public double getEncoderLF(){
    return encoderLF.getPosition();
  }

  public double getEncoderRB(){
    return encoderRB.getPosition();
  }  

  public double getEncoderRF(){
    return encoderRF.getPosition();
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
