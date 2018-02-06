package org.usfirst.frc.team2549.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.AnalogInput;

import org.usfirst.frc.team2549.robot.Robot;
import org.usfirst.frc.team2549.robot.RobotMap;
import org.usfirst.frc.team2549.robot.commands.LiftCommand;

/**
 *
 */
public class LiftSubsystem extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	private Talon motor;
	//public AnalogInput hal;

	public LiftSubsystem() {
		motor = new Talon(RobotMap.liftMotor);
		//hal = new AnalogInput(0);
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new LiftCommand());
    }
    
    public void driveLift(double speed) {
    	motor.set(speed);
    }
    
    /*public boolean getHal() {
    	if(hal.getVoltage() > 3)
    		return true;
    	else if(hal.getVoltage() < 3)
    		return false;
    	else return false;
    }//*/
}

