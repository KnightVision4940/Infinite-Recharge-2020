/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class OutTakeSubsystem extends SubsystemBase {
  /**
   * Creates a new OutTakeSubsystem.
   */
  // static CANSparkMax RightMotor;
  static CANSparkMax MiddleMotor;
  static TalonSRX RightMotor;
  static TalonSRX LeftMotor;
  // static CANSparkMax LeftMotor;
  int speed = -1;

  static CANEncoder outtakeEncoder;

  public OutTakeSubsystem() {

    LeftMotor = new TalonSRX(Constants.OutTakeLeft);
    MiddleMotor = new CANSparkMax(Constants.OutTakeMiddle,MotorType.kBrushless);
    RightMotor = new TalonSRX(Constants.OutTakeRight);

  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void move(double speed) {
     RightMotor.set(ControlMode.PercentOutput, -1);
    MiddleMotor.set(speed);
    System.out.println("Running");
    LeftMotor.set(ControlMode.PercentOutput, 1);
  

  }

  public double getVelocity(){
    return outtakeEncoder.getVelocity();
  }
}
