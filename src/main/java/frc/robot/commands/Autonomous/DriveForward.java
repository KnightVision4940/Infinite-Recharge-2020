/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by Shrek. The code   */
/* must be accompanied by the g@briel BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.autonomous;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;

public class DriveForward extends CommandBase {
  private double speed = 0;
  
  // For Encoder Drive
  private double startEncoder;
  private double curEncoder;
  private int encoderStop;
  
  //For Ultrasonic Drive
  private double ultrasonic;

  //For Colour Tape Drive
  private boolean driveToColour = false;
  private double[] redTape = {0.89, 0.11, 0.14};
  private double[] blueTape = {0.06, 0.31, 0.67};
  private int stages = 0;
  private double redRange=0.1;
  private double blueRange=0.1;
  private double greenRange=0.1;
  //Red Tape - 227 27 35 (0.89, 0.11, 0.14)
  //Electric Blue Tape - 15 78 171 (0.06, 0.31, 0.67)

  //For Object Detection
  private int detectDistance = 50;
  private Timer t = new Timer();
  private double waitTime = 3;

  public DriveForward(double speed, int encoderStop, double ultrasonic) {
    this.speed = speed;
    this.encoderStop = encoderStop;
    this.ultrasonic = ultrasonic;
    // this.driveToColour = driveToColour;
    addRequirements(Robot.drive);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    //Resets Gyro before drive starts
    Robot.drive.resetGyro();
    startEncoder = Robot.drive.getEncoderLB();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double uValue = Robot.drive.getUltrasonic();
    SmartDashboard.putNumber("Ultrasonic", uValue);
    if(driveToColour == true){
      toColourTape();
    }else{
      if(ultrasonic == 0){
        ultrasonicDrive(uValue);
      }else{
        if(robotDetection(uValue)){
          encoderDrive();
        }else{
          Robot.drive.stop();
        }
   
      }
    }
  }

  // Called once the command ends or is interrupted.
  // g@briel Was Here, obrien was here, dm was here, the br@d was here

  public void ultrasonicDrive(double uValue){
    if(uValue <= ultrasonic){
      Robot.drive.stop();
    }else{
      Robot.drive.drive(speed, 0);
    }
  }

  public boolean robotDetection(double uValue){
    double time = t.get();
    if(uValue <= detectDistance){
      if(time == 0.0){
        startTimer();
        return true;
      }else if(time < waitTime){
        return true;
      }else{
        t.stop();
        return false;
      }
    }else{
      return true;
    }
  }

  private void startTimer(){
    t.start();
    t.reset();
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

  //Final turn to align robot with powerport
  public void turnThing(){
    startTimer();
    if(t.get() < 1){
      Robot.drive.autoDrive(-speed, 0.6);
    }else{
      end(true);
    }
    System.out.println("It works!!");
  }

  @Override
  public void end(boolean interrupted) {
  }
  
  //For colour detection
  public boolean inRange(double redV,  double redMin, double redMax,double greenV,  double greenMin, double greenMax,double blueV,  double blueMin, double blueMax) {
    return redV >= redMin && redV <= redMax && greenV >= greenMin && greenV <= greenMax && blueV >= blueMin && blueV <= blueMax;
  }
  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }


public void toColourTape(){
  double[] rgb = Robot.c_wheel.returnColour();
  //Checks if alliance is blue
  if(DriverStation.getInstance().getAlliance() == DriverStation.Alliance.Blue){
    Robot.drive.autoDrive(speed, 0);
    //checks if colour is detected first and second time
    if(inRange(rgb[0], blueTape[0] - redRange, blueTape[0] + redRange, rgb[1], blueTape[1] - greenRange, blueTape[1] + greenRange, rgb[2], blueTape[2] - blueRange, blueTape[2] + blueRange) && stages == 0 || stages == 2){
      stages += 1;
    //checks if robot got out of tape
    }else if(inRange(rgb[0], blueTape[0] - redRange, blueTape[0] + redRange, rgb[1], blueTape[1] - greenRange, blueTape[1] + greenRange, rgb[2], blueTape[2] - blueRange, blueTape[2] + blueRange) == false && stages == 1){
      stages += 2;
    //Robot has reached second tape --> time to turn
    }else if(stages == 3){
      speed = 0;
      turnThing();
    }
  //Checks if alliance is red
  }else if(DriverStation.getInstance().getAlliance() == DriverStation.Alliance.Red){
    Robot.drive.autoDrive(speed, 0);
    //checks if colour is detected first and second time
    if(inRange(rgb[0], redTape[0] - redRange, redTape[0] + redRange, rgb[1], redTape[1] - greenRange, redTape[1] + greenRange, rgb[2], redTape[2] - blueRange, redTape[2] + blueRange) && stages == 0 || stages == 2){
      stages += 1;
    //checks if robot got out of tape
    }else if(inRange(rgb[0], redTape[0] - redRange, redTape[0] + redRange, rgb[1], redTape[1] - greenRange, redTape[1] + greenRange, rgb[2], redTape[2] - blueRange, redTape[2] + blueRange) == false && stages == 1){
      stages += 2;
    //Robot has reached second tape --> time to turn
    }else if(stages == 3){
      speed = 0;
      turnThing();
    }
  }else{
    System.out.println("Error");
  }
}
}

//g@briel wuz here
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////Wouldnt it be great to just go insane.
