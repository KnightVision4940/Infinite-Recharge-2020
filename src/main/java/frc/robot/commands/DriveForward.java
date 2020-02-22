/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;

public class DriveForward extends CommandBase {
  double speed = 0;
  double startEncoder;
  double curEncoder;

  int StopPos;
  private double ultrasonic;

  private boolean driveToColour;
  private double[] redTape = {0.47,0.36,0.13};
  private double[] blueTape = {0.15,0.42,0.42};

  private double redRange=0.1;
  private double blueRange=0.1;
  private double greenRange=0.1;
  /**
   * Creates a new DriveForward.
   */
  public DriveForward(double speed, int stop, double ultrasonic, boolean driveToColour) {
    this.speed = speed;
    this.StopPos = stop;
    this.ultrasonic = ultrasonic;
    this.driveToColour = driveToColour;


    addRequirements(Robot.drive);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    Robot.drive.resetGyro();
    startEncoder = Robot.drive.getEncoderLB();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {

    SmartDashboard.putNumber("Ultrasonic", Robot.drive.getUltrasonic());
    if(driveToColour == true){
      toColourTape();
    }else{
      if(ultrasonic == 0){
        ultrasonicDrive();
      }else{
        encoderDrive();
      }
    }
  }

  // Called once the command ends or is interrupted.
  // g@briel Was Here, obrien was here, dm was here, the br@d was here

  public void ultrasonicDrive(){
    if(Robot.drive.getUltrasonic() <= ultrasonic){
      Robot.drive.stop();
    }else{
      Robot.drive.drive(speed, 0);
    }
  }

  public void toColourTape(){
    double[] rgb = Robot.c_wheel.returnColour();
    if(DriverStation.getInstance().getAlliance() == DriverStation.Alliance.Blue){
      Robot.drive.autoDrive(speed, 0);
      if(inRange(rgb[0], blueTape[0] - redRange, blueTape[0] + redRange, rgb[1], blueTape[1] - greenRange, blueTape[1] + greenRange, rgb[2], blueTape[2] - blueRange, blueTape[2] + blueRange)){
        
      }
    }else{

    }
  }

  public void encoderDrive(){
    curEncoder =Robot.drive.getEncoderLB();
    if(curEncoder - startEncoder < StopPos){
      Robot.drive.autoDrive(speed, 0);
    }else{
      Robot.drive.stop();
      end(true);
    }
    Robot.drive.encodersOnDashboard();
  }
  @Override
  public void end(boolean interrupted) {
  }
  
  public boolean inRange(double redV,  double redMin, double redMax,double greenV,  double greenMin, double greenMax,double blueV,  double blueMin, double blueMax) {
    return redV >= redMin && redV <= redMax && greenV >= greenMin && greenV <= greenMax && blueV >= blueMin && blueV <= blueMax;
  }
  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
