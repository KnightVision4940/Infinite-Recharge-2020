/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class DriveTrain extends SubsystemBase {
  /**
   * Creates a new DriveTrain.
   */
  
  static Victor leftFront;
  static Victor leftBack;

  static DifferentialDrive drive;
    

  public DriveTrain(int frontL,int backl ) {
    leftFront = new Victor(frontL);
    leftBack = new Victor(backl);

    

    drive = new DifferentialDrive(leftFront, leftBack);


  }
  public void drive(double Speed, double turn){

    drive.arcadeDrive(Speed, turn,true);

  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
