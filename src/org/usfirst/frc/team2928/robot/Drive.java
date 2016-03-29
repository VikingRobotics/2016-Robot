package org.usfirst.frc.team2928.robot;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Solenoid;

public class Drive {

	private Joystick stick;
	private CANTalon talon1;
	private CANTalon talon2;
	private CANTalon talon3;
	private CANTalon talon4;
	private RobotDrive drive;
	private Solenoid s;
	
	public Drive (Joystick stick, CANTalon talon1, CANTalon talon2, CANTalon talon3, CANTalon talon4, Solenoid s)
	{
		this.stick = stick;
		this.talon1 = talon1;
		this.talon2 = talon2;
		this.talon3 = talon3;
		this.talon4 = talon4;
		this.s =s;
		/*talon1.changeControlMode(CANTalon.TalonControlMode.Follower);
		talon1.set(talon2.getDeviceID());
		talon3.changeControlMode(CANTalon.TalonControlMode.Follower);
		talon3.set(talon4.getDeviceID());*/
		drive = new RobotDrive(talon1, talon2, talon3, talon4);
		
	}
	
	//TODO: IMPLEMENT TALONS
	public void moveForwardsAndBackwards()
	{

		if((stick.getX() > .2 || stick.getX()< -.2) || (stick.getY()>.2 || stick.getY() <-.2) || (stick.getZ()>.35 || stick.getZ() <-.35))
		{
		double pt1 = Math.pow(stick.getZ() ,3);
		double pt1x = Math.pow(stick.getX(), 3);
		double pt1y = Math.pow(stick.getY(), 3);
		double pt2 = (3.25/5)*(pt1);
		double pt2y =(4.0/5)*(pt1y);
		double pt2x = (4.0/5)*(pt1x);
		double fixedX =0;
		double fixedY =0;
		double fixedZ = 0;
		
		
		if(stick.getX()>.3)
		{
			fixedX = pt2x+.2;
		}
		
		if(stick.getX()<-.2)
		{
			fixedX = pt2x-.2;
		}
		
		if(stick.getY()>.3)
		{
			fixedY = pt2y+.2;
		}
		
		if(stick.getY()<-.2)
		{
			fixedY= pt2y-.2;
		}

		drive.arcadeDrive(-fixedX-.3,-fixedY-.3);}
	}
	public void shift()
	{
		s.set(!s.get());
	}
	public void otherShift()
	{
		
		s.set(!s.get());
		}
}