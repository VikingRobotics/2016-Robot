package org.usfirst.frc.team2928.robot;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
//up: -130931
//Down: -5399
//Driving: -13299

public class Shooter {
	private Talon left;
	private Talon right;
	private JoystickButton button;
	private CANTalon lifter;
	private int zeroPos;
	private int presetOne;
	private int presetTwo;
	private int presetThree;
	private Talon intake;
	
	private double speed =.8;

	private Solenoid solen;
	public Shooter(Talon left, Talon right, CANTalon lifter, JoystickButton button, Talon intake, Solenoid solen)
	{
		this.left = left;
		this.right = right;
		this.button = button;
		this.lifter =lifter;
		this.intake = intake;
		this.solen = solen;
	}
	public void shoot()
	{
		solen.set(true);
		Timer.delay(1);
		solen.set(false);
	}
	public void spinUp()
	{
		
		if(lifter.getEncPosition() > zeroPos -40000){
		left.set(.65);
		right.set(.65);}
		else
		{
			left.set(.5);
			right.set(.5);
		}
	
		/*intake.set(1);
		Timer.delay(.25);
		solen.set(true);
		Timer.delay(.2);
		solen.set(false);*/
	}
	public int cycleCount =0;
	public void inatke()
	{
		left.set(-.6);
		right.set(-.5);
		if(cycleCount >75)
		{
		intake.set(-.5);
		}
		cycleCount++;
		
	}
	public void stop()
	{
		left.set(0);
		right.set(0);
		intake.set(0);
		lifter.set(0);
		cycleCount =0;
	
	}
	public void up()
	{
		lifter.set(-.55);
		
	}
	public void down()
	{
		
		lifter.set(.55);
	}
	public void zero()
	{
	bottom();
	Timer.delay(2.5);
	stop();
	zeroPos = lifter.getEncPosition();
	}
	public void intakeStop()
	{
		intake.set(0);
		
	}
	public void closeShot()
	{
		int closeShot =119651;
		comparePos(zeroPos-closeShot);
		lifter.set(speed);
		
	}
	public void drivePos()
	{
		int drivePos =34696 ;
		comparePos(zeroPos -drivePos);
		lifter.set(speed);
	}
	public void corner()
	{ 
		int cornerPos = 110000-(10128);
		comparePos(zeroPos- cornerPos);
		lifter.set(speed);
	}
	public void lowGoal()
	{
		int lowPos = 17483;
		comparePos(zeroPos-lowPos);
		lifter.set(speed);
	}
	// corner shot -10128
	//batter shot -20062
	public void comparePos(int goalPos)
	{
		if(lifter.getEncPosition() > goalPos +2000)
		{
	
			if (lifter.getEncPosition()> goalPos + 10000)
			{
				speed = .3;
			}
			else if(lifter.getEncPosition() > 30000)
			{
				speed = .5;
			}
			else 
			{
				speed = 1;
			}
		}
		else if(lifter.getEncPosition() < goalPos -2000)
		{
			if (lifter.getEncPosition()< goalPos + 10000)
			{
				speed = .3;
			}
			else if(lifter.getEncPosition() < 30000)
			{
				speed = .5;
			}
			else 
			{
				speed = 1;
			}
		}
		else
		{
			speed =0;
		}
	}
	public void bottom()
	{
		lifter.set(1);
	

	}
	
}