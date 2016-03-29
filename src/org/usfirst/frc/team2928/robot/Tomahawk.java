package org.usfirst.frc.team2928.robot;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Timer;

public class Tomahawk {
	
	private Solenoid solen;
	public Tomahawk(Solenoid solen) {
		this.solen = solen;
	}
	
	public void extend(){
		solen.set(true);
		Timer.delay(0.5);
	}
	
	public void retract(){
		solen.set(false);
	}

}
