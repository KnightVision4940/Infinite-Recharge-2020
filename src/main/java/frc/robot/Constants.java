/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants.  This class should not be used for any other purpose.  All constants should be
 * declared globally (i.e. public static).  Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
    // Xbox Controller Ports
    public static int xbox_drive = 0;
    public static int xbox_shoot = 1;

    //Motor Ports (PDP)
    public static int LeftF_drive = 13;
    public static int LeftB_drive = 14;
    public static int RightF_drive = 2;
    public static int RightB_drive = 16;
    public static int colourWheel = 0;
    public static int OutTakeLeft = 1;
    public static int OutTakeRight = 4;
    public static int OutTakeMiddle = 3;
    public static int Intake = 5;
    public static int Motor1Climb = 11;
    public static int Motor2Climb = 6;
    public static int Solenoid = 102;
    
    // ports
	public static final int DRIVER_PORT = 0;
    public static final int OPERATOR_PORT = 1;
    
	// analog inputs
	public static final int LEFT_STICK_X = 0;
	public static final int LEFT_STICK_Y = 1;
	public static final int LEFT_TRIGGER = 2;
	public static final int RIGHT_TRIGGER = 3;
	public static final int RIGHT_STICK_X = 4;
	public static final int RIGHT_STICK_Y = 5;
	
	// digital inputs
	public static final int A = 1;
	public static final int B = 2;
	public static final int X = 3;
	public static final int Y = 4;
	public static final int LB = 5;
	public static final int RB = 6;
	public static final int LOGO_LEFT = 7;
	public static final int LOGO_RIGHT = 8;
	public static final int LEFT_STICK_BUTTON = 9;
	public static final int RIGHT_STICK_BUTTON = 10;
}