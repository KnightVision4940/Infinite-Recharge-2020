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

  int encoderStop;
  private double ultrasonic;

  //red - 227 27 35 (0.89, 0.11, 0.14)
  //Electric Blue - 15 78 171 (0.06, 0.31, 0.67)
  private boolean driveToColour;
  private double[] redTape = {0.89, 0.11, 0.14};
  private double[] blueTape = {0.06, 0.31, 0.67};

  private int stages = 0;

  private double redRange=0.1;
  private double blueRange=0.1;
  private double greenRange=0.1;
  /**
   * Creates a new DriveForward.
   */
  public DriveForward(double speed, int encoderStop, double ultrasonic, boolean driveToColour) {
    this.speed = speed;
    this.encoderStop = encoderStop;
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
      if(inRange(rgb[0], blueTape[0] - redRange, blueTape[0] + redRange, rgb[1], blueTape[1] - greenRange, blueTape[1] + greenRange, rgb[2], blueTape[2] - blueRange, blueTape[2] + blueRange) && stages == 0 || stages == 2){
        stages += 1;
      }else if(inRange(rgb[0], blueTape[0] - redRange, blueTape[0] + redRange, rgb[1], blueTape[1] - greenRange, blueTape[1] + greenRange, rgb[2], blueTape[2] - blueRange, blueTape[2] + blueRange) == false && stages == 1){
        stages += 2;
      }else if(stages == 3){
        speed = 0;
        turnThing();
      }
    }else if(DriverStation.getInstance().getAlliance() == DriverStation.Alliance.Red){
      Robot.drive.autoDrive(speed, 0);
      if(inRange(rgb[0], redTape[0] - redRange, redTape[0] + redRange, rgb[1], redTape[1] - greenRange, redTape[1] + greenRange, rgb[2], redTape[2] - blueRange, redTape[2] + blueRange) && stages == 0 || stages == 2){
        stages += 1;
      }else if(inRange(rgb[0], redTape[0] - redRange, redTape[0] + redRange, rgb[1], redTape[1] - greenRange, redTape[1] + greenRange, rgb[2], redTape[2] - blueRange, redTape[2] + blueRange) == false && stages == 1){
        stages += 2;
      }else if(stages == 3){
        speed = 0;
        turnThing();
      }
    }else{
      System.out.println("Error");
    }
  }

  public void encoderDrive(){
    curEncoder =Robot.drive.getEncoderLB();
    if(curEncoder - startEncoder < encoderStop){
      Robot.drive.autoDrive(speed, 0);
    }else{
      Robot.drive.stop();
      end(true);
    }
    Robot.drive.encodersOnDashboard();
  }

  //Figure out some drive thing.
  public void turnThing(){
    Robot.drive.autoDrive(0, 0);
    System.out.println("It works!!");
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

//g@briel wuz here
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////Wouldnt it be great to just go insane.
