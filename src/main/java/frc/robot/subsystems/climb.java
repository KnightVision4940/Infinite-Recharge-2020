/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Climb extends SubsystemBase {
  // static TalonSRX mainMotor;
  static TalonSRX Motor1;
  static TalonSRX Motor2;
  /**
   * Creates a new Climb.
   */
  public Climb() {
    Motor1 = new TalonSRX(Constants.Motor1Climb);
    Motor2 = new TalonSRX(Constants.Motor2Climb);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void move(double speed1, double speed2) {
    Motor1.set(ControlMode.PercentOutput, speed1);
    Motor2.set(ControlMode.PercentOutput, speed2);
  }
}
