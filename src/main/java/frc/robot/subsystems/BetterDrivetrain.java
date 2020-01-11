/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class BetterDrivetrain extends SubsystemBase {
  /**
   * Creates a new BetterDrivetrain.
   */
  WPI_VictorSPX leftFront = new WPI_VictorSPX(Constants.leftFront);
  WPI_VictorSPX leftBack = new WPI_VictorSPX(Constants.leftBack);
  WPI_VictorSPX rightFront = new WPI_VictorSPX(Constants.rightFront);
  WPI_VictorSPX rightBack = new WPI_VictorSPX(Constants.rightBack);

  // SpeedControllerGroup left = new SpeedControllerGroup( leftFront,  leftBack);
  
  DifferentialDrive Drive = new DifferentialDrive(leftFront, rightFront);

  public BetterDrivetrain() {

  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
  public void drive(double speed, double turn){
      Drive.arcadeDrive(speed *0.8, turn*0.5);
  }
  public void gyrodrive(){

  }
}
