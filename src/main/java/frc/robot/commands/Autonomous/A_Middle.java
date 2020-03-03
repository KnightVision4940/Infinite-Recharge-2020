/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.autonomous;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.autonomous.DriveForward;
// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/latest/docs/software/commandbased/convenience-features.html
public class A_FurthestFromWall extends SequentialCommandGroup {
  /**
   * Creates a new AutoOne.
   */
  //  DriveForward(Turn,Speed,Stop Position)
  public A_FurthestFromWall() {
    //(Speed,Encoder,ultrasonic)  
    super(new DriveForward(0.3,0,40),new DriveTurn(90),new DriveForward(0.3, 50, 0),new Auto_Outake(1000), new DriveForward(-0.3, -20, 0));
    // addCommands(new DriveForward(true,false,0,0.5));
    // Add your commands in the super() call, e.g.
    // super(new FooCommand(), new BarCommand());

  }
}
