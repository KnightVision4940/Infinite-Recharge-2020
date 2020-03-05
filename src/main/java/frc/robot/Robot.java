/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;

import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.OutTakeSubsystem;
import frc.robot.subsystems.climb;
import frc.robot.subsystems.ColourWheel;
import frc.robot.subsystems.Deployer;
import frc.robot.commands.Drive;
import frc.robot.commands.TestControl;
import frc.robot.commands.autonomous.A_FrontOfPowerPorrt;
import frc.robot.commands.autonomous.A_FurthestFromWall;
import frc.robot.commands.autonomous.A_Middle;
import frc.robot.Constants;

/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  private Command m_autonomousCommand;

  // private RobotContainer m_robotContainer;

  public static DriveTrain drive = new DriveTrain(Constants.LeftF_drive, Constants.LeftB_drive, Constants.RightF_drive, Constants.RightB_drive);
  public static final XboxController x = new XboxController(Constants.xbox_drive);
  public static final XboxController x2 = new XboxController(Constants.xbox_shoot);
  public static OutTakeSubsystem sub_outtake = new OutTakeSubsystem();
  public static Deployer IntakeDeployer = new Deployer();
  public static climb Climber = new climb();
  public static Intake in_sub = new Intake();
  public static ColourWheel c_wheel = new ColourWheel();
  SendableChooser<Command> autoChooser;

  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */
  @Override
  public void robotInit() {
    // Instantiate our RobotContainer.  This will perform all our button bindings, and put our
    // autonomous chooser on the dashboard.
    // m_robotContainer = new RobotContainer();
    autoChooser = new SendableChooser<Command>();
    autoChooser.addDefault("Furthest From Wall - Auto 1", new A_FurthestFromWall());
    autoChooser.addObject("In front of Powerport - Auto 2", new A_FrontOfPowerPorrt());
    autoChooser.addObject("Middle - Auto 3", new A_Middle());
    SmartDashboard.putData("Autonomous Chooser:", autoChooser);
  }

  /**
   * This function is called every robot packet, no matter the mode. Use this for items like
   * diagnostics that you want ran during disabled, autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before
   * LiveWindow and SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
    // Runs the Scheduler.  This is responsible for polling buttons, adding newly-scheduled
    // commands, running already-scheduled commands, removing finished or interrupted commands,
    // and running subsystem periodic() methods.  This must be called from the robot's periodic
    // block in order for anything in the Command-based framework to work.
    CommandScheduler.getInstance().run();
  }

  /**
   * This function is called once each time the robot enters Disabled mode.
   */
  @Override
  public void disabledInit() {
  }

  @Override
  public void disabledPeriodic() {
  }

  /**
   * This autonomous runs the autonomous command selected by your {@link RobotContainer} class.
   */
  @Override
  public void autonomousInit() {
    
    // m_autonomousCommand = m_robotContainer.getAutonomousCommand();
  

    // schedule the autonomous command (example)
    if (m_autonomousCommand != null) {
      m_autonomousCommand.schedule();
    }
  }

  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {
  }

  @Override
  public void teleopInit() {
    // This makes sure that the autonomous stops running when
    // teleop starts running. If you want the autonomous to
    // continue until interrupted by another command, remove
    // this line or comment it out.
    if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
    }
  }

  /**
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopPeriodic() {
    CommandScheduler.getInstance().schedule(new Drive());
    CommandScheduler.getInstance().run();
  }

  @Override
  public void testInit() {
    // Cancels all running commands at the start of test mode.
    CommandScheduler.getInstance().cancelAll();
  }

  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
    CommandScheduler.getInstance().schedule(new TestControl());
    CommandScheduler.getInstance().run();


  }

  public static double getTriggers(){
    if(x.getRawAxis(2) > x.getRawAxis(3)){
      return x.getRawAxis(2);
    } else {
      return -x.getRawAxis(3);
    }
  
  }
  public static double getXLeft() {
    double deadzone = 0.05;
    double rawPos = x.getRawAxis(0);
    if(rawPos > -deadzone && rawPos < deadzone) {
      return 0;
    }
    return rawPos;
  }
 
  public static boolean leftBumper(){
    return x.getRawButton(5);
  }
  
  public static boolean rightBumper(){
    return x.getRawButton(6);
  }

  public static double getYRight() {
    double deadzone = 0.05;
    double rawPos = x.getRawAxis(5);
    if(rawPos > -deadzone && rawPos < deadzone) {
      return 0;
    }
    else {
      return rawPos;
    }
  
  }
  public static double getY2Right() {
    double deadzone = 0.05;
    double rawPos = x2.getRawAxis(5);
    if(rawPos > -deadzone && rawPos < deadzone) {
      return 0;
    }
    else {
      return rawPos;
    }
  
  }
  public static double getY2Left() {
    double deadzone = 0.05;
    double rawPos = x2.getRawAxis(1);
    if(rawPos > -deadzone && rawPos < deadzone) {
      return 0;
    }
    return rawPos;
  }
}