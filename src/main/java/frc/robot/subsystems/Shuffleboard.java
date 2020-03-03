/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;


  public class Shuffleboard extends SubsystemBase {
    static final NetworkTable table = NetworkTableInstance.getDefault().getTable("Drive");
    static final NetworkTable table = NetworkTableInstance.getDefault().getTable("Vision");
      Shuffleboard.getTab("Drive")
        .addPersistent("Max Speed", 1.0);
 
  public class DriveBase extends subsystems {
    Shuffleboard tab = Shuffleboard.getTab("Drive");
    NetworkEntry maxSpeed = ((Object) tab).add("Max Speed", 1);
      .getEntry();
}

  class VisionCalculator {
    Shuffleboard tab = Shuffleboard.getTab('Vision');
    NetworkTableEntry velocityEntry = tab.add('Velocity to target', 0);
        .getEntry();

  public void calculator() {
    double velocity = get.velocityEntry
      velocityEntry.setdouble(double);
}

  public void excute(){ 
    SmartDashboard.putBoolean("Bridge Limit", bridgeTipper.atBridge());
    SmartDashboard.putNumber("Bridge Angle", bridgeTipper.getPosition());
    SmartDashboard.putNumber("Swerve Angle", DriveTrain.getSwerveAngle());
    SmartDashboard.putNumber("Left Drive Encoder", DriveTrain.getLeftEncoder());
    SmartDashboard.putNumber("Right Drive Encoder", DriveTrain.getRightEncoder());
    SmartDashboard.putNumber("Turret Pot", turret.getCurrentAngle());
    SmartDashboard.putNumber("Turret Pot Voltage", turret.getAverageVoltage());
      edu.wpi.first.wpilibj.smartdashboard.SmartDashboar
      SmartDashboard.putNumber("Joystick X value", Joystick.getX());
  }
}
  @Override
  public void periodic() {
  }
}
