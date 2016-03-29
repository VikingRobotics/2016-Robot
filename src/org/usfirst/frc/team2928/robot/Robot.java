package org.usfirst.frc.team2928.robot;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.SampleRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

import com.ni.vision.NIVision;
import com.ni.vision.NIVision.Image;
import com.ni.vision.VisionException;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.SampleRobot;
import edu.wpi.first.wpilibj.Timer;
import com.ni.vision.NIVision;
import com.ni.vision.NIVision.DrawMode;
import com.ni.vision.NIVision.Image;
import com.ni.vision.NIVision.ShapeMode;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.SampleRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.vision.USBCamera;

/**
 * This is a demo program showing the use of the CameraServer class.
 * With start automatic capture, there is no opportunity to process the image.
 * Look at the IntermediateVision sample for how to process the image before sending it to the FRC PC Dashboard.
 */
public class Robot extends SampleRobot {

    CameraServer server;
    Compressor compress;
    CANTalon frightMotor;
    CANTalon fleftMotor;
    CANTalon bleftMotor;
    CANTalon brightMotor;
    RobotDrive tank;
    Joystick stick;
    Joystick otherStick;
    JoystickButton yudodis;
    JoystickButton suck;
    JoystickButton shoot;
    Talon leftShoot;
    Talon rightShoot;
    Shooter cannon;
    CANTalon lifter;
    Drive drive;
    Solenoid shiftSolen;
    Solenoid hawkSolen;
    Solenoid shootSolen;
    JoystickButton up;
    JoystickButton otherUp;
    JoystickButton down;
    JoystickButton otherDown;
    JoystickButton tom;
    JoystickButton in;
    JoystickButton spinUp;
    JoystickButton shift;
    JoystickButton othertom;
	 Joystick otherstick;
    Tomahawk hawk;
    Talon intake;
    JoystickButton closeShot;
    JoystickButton cornerShot;
    JoystickButton tomUp;
    boolean pulse =false;
    public Robot() {
        
    	compress = new Compressor (0);
        stick = new Joystick(1);
        shiftSolen = new Solenoid(0); //final 0
        otherstick = new Joystick(2);  
        shootSolen = new Solenoid(7); //final 7
        hawkSolen = new Solenoid(1); //final 1
        otherStick = new Joystick(2);
        bleftMotor = new CANTalon(14);//practice14 final 14
        brightMotor = new CANTalon(1);//practice13 final 1
        fleftMotor = new CANTalon(15);//practice12 final 15
        frightMotor = new CANTalon(0);//practice11 final 0
        lifter = new CANTalon(4); //final 4
        suck = new JoystickButton(this.stick, 8);
        yudodis = new JoystickButton(this.otherStick, 9);
        shoot = new JoystickButton(this.otherStick, 5);
        spinUp = new JoystickButton(this.otherStick, 7);
        up = new JoystickButton(this.otherStick, 1);
        down = new JoystickButton(this.otherStick, 4);
        tom = new JoystickButton(this.otherStick, 	19); //11 and 14
        othertom = new JoystickButton(this.otherstick, 8);
        shift = new JoystickButton(this.stick, 9);
         intake = new Talon(2);
        leftShoot = new Talon(0);
        rightShoot = new Talon(1);
        cannon = new Shooter(leftShoot,rightShoot ,lifter ,shoot,intake,shootSolen);
        drive = new Drive(stick, bleftMotor, fleftMotor, brightMotor, frightMotor, shiftSolen);
        tank = new RobotDrive(bleftMotor, fleftMotor, brightMotor, frightMotor);
        hawk = new Tomahawk(hawkSolen);
        in = new JoystickButton(this.otherStick, 6);
        otherUp = new JoystickButton(this.otherStick, 8);
        otherDown = new JoystickButton(this.otherstick,10);
        closeShot = new JoystickButton(this.otherstick, 2);
        cornerShot = new JoystickButton(this.otherStick,3 );
        tomUp = new JoystickButton(this.otherstick, 15);
    	server = CameraServer.getInstance();
        server.setQuality(50 );
        server.startAutomaticCapture("cam0");

}
      /**
     * start up automatic capture you should see the video stream from the
     * webcam in your FRC PC Dashboard.
     */
    public void autonomous(){
    	if(isAutonomous()&&isEnabled()){
    	Timer t = new Timer();
    	t.start();
    	cannon.zero();
		hawk.extend();
		drive.shift();
    	while(t.get() <6.5)
    	{
    		tank.arcadeDrive(0,.75);
    	}
    	tank.arcadeDrive(0, 0);
    	Timer.delay(4);
    	/*
    	 cannon.zero();
		cannon.drivePos();
		Timer.delay(1);
		cannon.stop();
		drive.shift();
    	while(t.get() <6.5)
    	{
    		tank.arcadeDrive(0,.75);
    	}
    	tank.arcadeDrive(0, 0);
    	Timer.delay(4);*/
    	}

	
}
    

    public void operatorControl() {
    //compress.stop(); 

    	compress.start();

    	
        while (isOperatorControl() && isEnabled()) {
        	  drive.moveForwardsAndBackwards();

        	//Shifts gears
        	  if(!shift.get())
        	  {
        		  if(shiftSolen.get() ==false)
        		  {
        			  shiftSolen.set(true);
        		  }
        	  }
        	  if(shift.get())
        	  {
        		  if(shiftSolen.get() == true)
        		  {
        			  shiftSolen.set(false);
        		  }  
        	  }
        	  drive.moveForwardsAndBackwards();

        	/*if(suck.get())
        	{
        		cannon.intake();
        	}
         	else if(shoot.get())
        	{
        		cannon.shoot();
        	}
        	else
        	{
        		cannon.stop();
        	}*/
        	if(spinUp.get())
        	{
        		cannon.spinUp();
        		Timer.delay(1);

              	
        		
        	}
   
        	else if(in.get())
            {
            	cannon.inatke();
            }
        	else if(yudodis.get())
        	{
        		cannon.drivePos();
        
        	}
        	else if(up.get() == true)
        	{
        		cannon .up();
        	}
      	else if(down.get() == true)
        	{
        		
        		cannon.down();
        	}
        	else if(closeShot.get())
        	{
        		cannon.lowGoal();
        	}
        	else if(cornerShot.get())
        	{
        		
        		cannon.closeShot();
        	}
        	else
        	{
        		cannon.stop();
        	}drive.moveForwardsAndBackwards();

        	
        	if(shoot.get())
        	{
        	
        		cannon.shoot();
        	}

        	//Sets lifter speed
        /*	if(down.get()==true)
        	{
        		lifter.set(-0.5);
        	}
        	if(up.get()==true)
			{
				lifter.set(.5);		
			}*/
		if((!tom.get())||(!tomUp.get()))
		{
			pulse = false;
		}
	
        
        	
        	//changes tomahawk settings
        if(hawkSolen.get() == false){
       	if((this.tom.get())&&(pulse == false))
        	{
       			cannon.bottom();
       		    
        		hawk.extend();
        		
        		Timer.delay(.001);
        		drive.moveForwardsAndBackwards();
        		if(tom.get())
        		{
        		pulse = true;
        		}

        	}
       	else if(otherDown.get())
       	{
       	
       		cannon.bottom();
   	
    		hawk.extend();
      		
       		
       	}}
        else{
        	 if(((otherUp.get())||(othertom.get()))&&(pulse == false))
        	{
        		
        	hawk.retract();
        	drive.moveForwardsAndBackwards();
        	if(othertom.get())
        	{
        		pulse = true;
        	}

        	}
        }
        	drive.moveForwardsAndBackwards();
    		


        /*	if(in.get())
        	{
        		cannon.inatke();
        	}
        	else
        	{
        		cannon.stop();
        	}*/
       
        	
        	
        }
      
    }
  
} 