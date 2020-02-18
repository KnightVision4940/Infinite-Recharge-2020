/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.revrobotics.CANEncoder;
import com.revrobotics.CANPIDController;
import com.revrobotics.CANSparkMax;
import com.revrobotics.ControlType;
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
  static CANPIDController pid;
  // static CANSparkMax LeftMotor;
  static int speed = -1;

  static CANEncoder outtakeEncoder;

  public OutTakeSubsystem() {

    LeftMotor = new TalonSRX(Constants.OutTakeLeft);
    MiddleMotor = new CANSparkMax(Constants.OutTakeMiddle,MotorType.kBrushless);
    RightMotor = new TalonSRX(Constants.OutTakeRight);
    pid = new CANPIDController(MiddleMotor);

  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void move(double speed) {
     RightMotor.set(ControlMode.PercentOutput, -1);
    // MiddleMotor.set(speed);
    LeftMotor.set(ControlMode.PercentOutput, 1);
  }

  public void movePID(double maxRPM, double p, double i, double d, double ff){
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
}
