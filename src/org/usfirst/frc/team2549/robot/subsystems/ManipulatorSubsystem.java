package org.usfirst.frc.team2549.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.DigitalInput;

import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Servo;

import org.usfirst.frc.team2549.robot.RobotMap;
import org.usfirst.frc.team2549.robot.commands.ManipulatorCommand;

/**
 *
 */
public class ManipulatorSubsystem extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

	private Talon motors;
	private DigitalInput boxIn;
	private Servo servo;
	private double servoDownPos, servoUpPos;

	public ManipulatorSubsystem() {
		motors = new Talon(RobotMap.manipulatorMotors);
		servo = new Servo(RobotMap.releaseServo);
		servoDownPos = 1;
		servoUpPos = 0; // change these
	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new ManipulatorCommand());
    }

    public void spinMotors(double speed) {
    	motors.set(speed);
    }
    
    public void servoRelease(boolean set) {
    	if(set)
    		servo.set(servoDownPos);
    	else if(!set)
    		servo.set(servoUpPos);
    }
    
    public double getMotors() {
    	return motors.getSpeed();
    }
    
    public double getServo() {
    	return servo.getPosition();
    }
    
    public boolean cubeIn() {
    	if(boxIn.get())
    		return true;
    	else return false;
    }
}

