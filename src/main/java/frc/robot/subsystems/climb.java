/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.Robot;
import com.ctre.phoenix.sensors.CANCoder;

public class climb extends SubsystemBase {
  // static TalonSRX mainMotor;
  static TalonFX leftMotor;
  static TalonFX rightMotor;
  static CANCoder encoderLeft;
  static CANCoder encoderRight;

  static double P, I, D;
  static double integral, previous_error, setpoint = 0;
  private double rcwLeft;
  private double rcwRight;
  /**
   * Creates a new Climb.
   */
  public climb() {
    leftMotor = new TalonFX(Constants.Motor1Climb);
    rightMotor = new TalonFX(Constants.Motor2Climb);
    
  encoderLeft = new CANCoder(Constants.Motor1Climb);
  encoderRight = new CANCoder(Constants.Motor2Climb);
    
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void move(double spe) {
    P,I,D,= spe;
    PIDLeft();
    PIDRight();
    leftMotor.set(ControlMode.PercentOutput, rcwLeft);
    rightMotor.set(ControlMode.PercentOutput, rcwRight);
  }

  public void Climbing(){
   
  }

  public void setSetpoint(int setpoint)
  {
      this.setpoint = setpoint;
  }

  public void PIDLeft(){
    double error = setpoint - encoderLeft.getVelocity(); // Error = Target - Actual
    this.integral += (error*.02); // Integral is increased by the error*time (which is .02 seconds using normal IterativeRobot)
    double derivative = (error - this.previous_error) / .02;
    rcwLeft = P*error + I*this.integral + D*derivative;
  }
  public void PIDRight(){
    double errorRight = setpoint - encoderRight.getVelocity(); // Error = Target - Actual
    this.integral += (errorRight*.02); // Integral is increased by the error*time (which is .02 seconds using normal IterativeRobot)
    double derivative = (errorRight - this.previous_error) / .02;
    rcwRight = P*errorRight + I*this.integral + D*derivative;
}
}
