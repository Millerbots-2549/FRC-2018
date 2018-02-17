package frc.team2549.robot.subsystems;

import frc.team2549.robot.RobotMap;
import frc.team2549.robot.commands.LiftCommand;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.Counter;

/**
 *
 */
public class LiftSubsystem extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    private DigitalInput limitFloor;
    private DigitalInput limitSwitch;
    private DigitalInput limitScale;
    private Talon motor;
    private double speed;
    private int position;

    public LiftSubsystem() {
        super(LiftSubsystem.class.getSimpleName());
        motor = new Talon(RobotMap.liftMotor);
        limitFloor = new DigitalInput(RobotMap.limitFloor);
        limitSwitch = new DigitalInput(RobotMap.limitSwitch);
        limitScale = new DigitalInput(RobotMap.limitScale);

        position = 0;
        speed = 1;
    }

    public void initDefaultCommand() {
        setDefaultCommand(new LiftCommand());
    }
    
    public double getMotor() {
    	return motor.get();
    }

    public void raiseLift() {
    	if(isAtScale())
    		stopLift();
    	else motor.set(speed);
    }

    public void lowerLift() {
    	if(isAtFloor())
    		stopLift();
    	else motor.set(-speed/2);
    }

    public void stopLift() {
        motor.set(0.0);
    }

    public int getPosition() {
    	return position;
    }

    public boolean isAtFloor() {
    	return !limitFloor.get();
    }

    public boolean isAtSwitch() {
    	return !limitSwitch.get();
    }

    public boolean isAtScale() {
    	return !limitScale.get();
    }

    public void moveToFloor() {
    	System.out.println("In move to floor");
    	if(!isAtFloor())
    		lowerLift();
    	else stopLift();
    }

    public void moveToSwitch() {
    	System.out.println("In move to switch");
    	if(!isAtSwitch())
	    	if(!isAtScale())
	    		lowerLift();
	    	else if(!isAtFloor())
	    		raiseLift();
	    else stopLift();
    }

    public void moveToScale() {
    	System.out.println("In move to scale");
    	if(!isAtScale()) {
        	System.out.println("RAISING In move to scale");
    		raiseLift();
    	}
    	else {
        	System.out.println("STOPPING In move to scale");
    		stopLift();
    	}
    }
}
