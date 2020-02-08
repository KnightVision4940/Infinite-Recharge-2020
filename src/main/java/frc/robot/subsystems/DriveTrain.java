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
  static ADXRS450_Gyro gyro;

  static DifferentialDrive drive;
    
  public DriveTrain(int leftF,int leftB, int rightF, int rightB) {
    leftFront = new CANSparkMax(leftF, MotorType.kBrushless);
    leftBack = new CANSparkMax(leftB, MotorType.kBrushless);
    rightFront = new CANSparkMax(rightF, MotorType.kBrushless);
    rightBack = new CANSparkMax(rightB, MotorType.kBrushless);
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

  public void driveStraightGyro(double speed){
    double p = 1.0;
    double error = -gyro.getAngle(); 
    double turn = p * error;
    drive.arcadeDrive(speed, turn);
  }
  public void getEncoder(){
    CANEncoder encoderA = new CANEncoder(leftBack);
    SmartDashboard.putNumber("Encoder Left Back", encoderA.getPosition());
    CANEncoder encoderB = new CANEncoder(leftBack);
    SmartDashboard.putNumber("Encoder Right Back", encoderB.getPosition());
    CANEncoder encoderC = new CANEncoder(leftBack);
    SmartDashboard.putNumber("Encoder Left Front", encoderC.getPosition());
    CANEncoder encoderD = new CANEncoder(leftBack);
    SmartDashboard.putNumber("Encoder Right Front", encoderD.getPosition());
  }


  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
