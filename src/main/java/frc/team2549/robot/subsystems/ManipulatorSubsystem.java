package frc.team2549.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.team2549.robot.RobotMap;
import frc.team2549.robot.commands.ManipulatorCommand;

/**
 *
 */
public class ManipulatorSubsystem extends Subsystem {

    private Talon motor;
    private DigitalInput boxIn;
    private double speed;

    //TODO this servo should be a separate subsystem with its own commands for controlling it
    private Servo servo;
    private double servoDownPos, servoUpPos;

    public ManipulatorSubsystem() {
        super(ManipulatorSubsystem.class.getSimpleName());
        motor = new Talon(RobotMap.manipulatorMotors);
        servo = new Servo(RobotMap.releaseServo);
        boxIn = new DigitalInput(RobotMap.boxIn);
        servoDownPos = .4;
        servoUpPos = 1; // change these
        speed = 1;
    }

    public void initDefaultCommand() {
        setDefaultCommand(new ManipulatorCommand());
    }

    public void setSpeed(double speed) {
    	this.speed = speed;
    }

    public void servoRelease(boolean set) {
        if (set)
            servo.set(servoDownPos);
        else if (!set)
            servo.set(servoUpPos);
    }

    public void takeIn() {
    	if(!cubeIn())
    		motor.set(-speed);
    }

    public void pushOut() {
        motor.set(speed);
    }
    
    public void stop() {
    	motor.set(0);
    }
    
    public double getMotors() {
    	return motor.get();
    }

    public double getServo() {
        return servo.getPosition();
    }

    public boolean getServoReleased() {
    	if(servo.getPosition() == servoDownPos)
    		return true;
    	else return false;
    }

    public boolean cubeIn() {
        return !boxIn.get();
    }
}

