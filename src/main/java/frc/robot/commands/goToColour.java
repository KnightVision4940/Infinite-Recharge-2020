// /*----------------------------------------------------------------------------*/
// /* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
// /* Open Source Software - may be modified and shared by FRC teams. The code   */
// /* must be accompanied by the FIRST BSD license file in the root directory of */
// /* the project.                                                               */
// /*----------------------------------------------------------------------------*/

// package frc.robot.commands;

// import edu.wpi.first.wpilibj.DriverStation;
// import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
// import edu.wpi.first.wpilibj2.command.CommandBase;
// import frc.robot.subsystems.colourWheel;
// import edu.wpi.first.wpilibj.DriverStation;

// public class goToColour extends CommandBase {
//   /**
//    * Creates a new goToColour.
//    */
//   private int colourToGoTo;
//   colourWheel m_colourWheel = new colourWheel();
//   public goToColour() {
//     // Use addRequirements() here to declare subsystem dependencies.
//   }

//   // Called when the command is initially scheduled.
//   @Override
//   public void initialize() {
//     String gameData;
//     gameData = DriverStation.getInstance().getGameSpecificMessage();
//     SmartDashboard.putString("Color", "None");
//     String[] colorNames = {"Blue", "Yellow", "Red", "Green"};
//     if(gameData.length() > 0)
//     { 
//       switch (gameData.charAt(0))
//       {
//         case 'B':
//           colourToGoTo = 0;
//           SmartDashboard.putString("Color", "Blue");
//           break;
//         case 'G':
//           colourToGoTo = 3;
//           SmartDashboard.putString("Color", "Green");
//           break;
//         case 'R':
//           colourToGoTo = 2;
//           SmartDashboard.putString("Color", "Red");
//           break;
//         case 'Y':
//           colourToGoTo = 1;
//           SmartDashboard.putString("Color", "Yellow");
//           break;
//         default:
//           //This is corrupt data
//           break;
//       }
      
//     } else {
//       //Code for no data received yet
//     }
//     SmartDashboard.putString("Color", colorNames[colourToGoTo]);
//   }

//   // Called every time the scheduler runs while the command is scheduled.
//   @Override
//   public void execute() {
//     m_colourWheel.findColour(colourToGoTo);
//   }

//   // Called once the command ends or is interrupted.
//   @Override
//   public void end(boolean interrupted) {
//   }

//   // Returns true when the command should end.
//   @Override
//   public boolean isFinished() {
//     return false;
//   }
// }
