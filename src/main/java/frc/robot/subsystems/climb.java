/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/
// RIP FRC 2020 

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;


public class climb extends SubsystemBase {
  private TalonFX leftMotor;
  private TalonFX rightMotor;

  private double P, I, D;
  private double integral, previous_error, setpoint = 0;
  private double rcwLeft;
  private double rcwRight;

  private double topLimit;
  private double bottomLimit;

  public climb() {
    leftMotor = new TalonFX(Constants.Motor1Climb);
    rightMotor = new TalonFX(Constants.Motor2Climb);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void move(double speed) {
    // P = 1;
    // I = 1;
    // D = 1;
    int l_position = leftPos();
    SmartDashboard.putNumber("Left Climb Motor", leftVel());
    SmartDashboard.putNumber("Right Climb Motor", rightVel());
    // PIDLeft();
    // PIDRight();
    //Encoder Logic (Old)
    // if(speed > 0 && topLimit < l_position){
    //   leftMotor.set(ControlMode.PercentOutput, speed);
    //   // rightMotor.set(ControlMode.PercentOutput, speed);
    // }else if(speed < 0 && bottomLimit > l_position){
    //   leftMotor.set(ControlMode.PercentOutput, speed);
    //   // 

    //Encoder Logic (New)
    // if(speed > 0 && topLimit <= l_position){
    //   stop();
    // }else if(speed < 0 && bottomLimit >= l_position){
    //   stop();
    // }else{
    //   leftMotor.set(ControlMode.PercentOutput, speed);
    //   rightMotor.set(ControlMode.PercentOutput, speed);
    // }
      
    leftMotor.set(ControlMode.PercentOutput, Math.abs(speed));
     rightMotor.set(ControlMode.PercentOutput, Math.abs(speed));
     
    // }  
  }

  public int leftPos(){
    return leftMotor.getSelectedSensorPosition();
  } 

  public int rightPos(){
    return rightMotor.getSelectedSensorPosition();
  }
  public double leftVel(){
    return leftMotor.getSelectedSensorVelocity()*(1/2048)*(100/0.00166667);
  } 

  // 2048 units --> 1 rate 
  //100ms --> 0.00166667
  //55(1/2048)(100/0.00166667)

  public double rightVel(){
    return rightMotor.getSelectedSensorVelocity()*(1/2048)*(100/0.00166667);
  }

  public void setSetpoint(int setpoint){
    this.setpoint = setpoint;
  }
  public void setLimits(){
    bottomLimit = leftPos();
    topLimit = bottomLimit + 2000;
  }
  
  public void PIDLeft(){
    double error = setpoint -   leftMotor.getSelectedSensorVelocity();
    // Error = Target - Actual
    this.integral += (error*.02); // Integral is increased by the error*time (which is .02 seconds using normal IterativeRobot)
    double derivative = (error - this.previous_error) / .02;
    rcwLeft = P*error + I*this.integral + D*derivative;
  }
  public void PIDRight(){
    double errorRight = setpoint -   rightMotor.getSelectedSensorVelocity(); // Error = Target - Actual
    this.integral += (errorRight*.02); // Integral is increased by the error*time (which is .02 seconds using normal IterativeRobot)
    double derivative = (errorRight - this.previous_error) / .02;
    rcwRight = P*errorRight + I*this.integral + D*derivative;
}
public void stop(){
  leftMotor.set(ControlMode.PercentOutput, 0);
  rightMotor.set(ControlMode.PercentOutput, 0);
}

}