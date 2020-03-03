package frc.robot;

import edu.wpi.first.cameraserver.*;
import edu.wpi.cscore.UsbCamera;

 public class CamServer{

 public static UsbCamera m_Cam;
	
 public static void camInit(){
		m_Cam = CameraServer.getInstance().startAutomaticCapture("vision", 0);
		m_Cam.setFPS(30);
		m_Cam.setResolution(192, 144);
		m_Cam.setExposureManual(5);
 }

 public static void camStop(){
		CameraServer.getInstance().removeCamera("vision");
  }
 }
