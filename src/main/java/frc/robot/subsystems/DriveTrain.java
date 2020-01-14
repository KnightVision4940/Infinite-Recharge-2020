/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class DriveTrain extends SubsystemBase {
  /**
   * Creates a new DriveTrain.
   */
  
  // static WPI_VictorSPX leftFront;
  // static WPI_VictorSPX leftBack;
  // static WPI_VictorSPX rightFront;
  // static WPI_VictorSPX rightBack;
  static Victor leftFront;
  static Victor leftBack;
  static Victor rightFront;
  static Victor rightBack;

  static DifferentialDrive drive;
    
  public DriveTrain(int leftF,int leftB, int rightF, int rightB) {
    leftFront = new Victor(leftF);
    leftBack = new Victor(leftB);
    rightFront = new Victor(rightF);
    rightBack = new Victor(rightB);
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
    drive.arcadeDrive(speed, turn,true);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
