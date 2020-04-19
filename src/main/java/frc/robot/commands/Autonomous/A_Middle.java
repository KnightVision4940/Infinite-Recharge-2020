/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/
// RIP FRC 2020 

package frc.robot.commands.autonomous;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.ControlClimb;
import frc.robot.commands.autonomous.DriveForward;

public class A_Middle extends SequentialCommandGroup {

  public A_Middle() {
    
    //ControlClimb command is for setting limits
    //Parameter for DriveForward: (Speed,Encoder,ultrasonic)
    //Parameter for DriveTurn: (Degrees)
    
    super(new ControlClimb(1, true), new DriveForward(0.3,0,40),new DriveTurn(90),new DriveForward(0.3, 50, 0),new Auto_Outake(1000), new DriveForward(-0.3, -20, 0));
    // super(new FooCommand(), new BarCommand());

  }
}
