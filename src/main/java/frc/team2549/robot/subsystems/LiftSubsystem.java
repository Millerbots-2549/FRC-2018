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
    private boolean moving;

    public LiftSubsystem() {
        super(LiftSubsystem.class.getSimpleName());
        motor = new Talon(RobotMap.liftMotor);
        limitFloor = new DigitalInput(RobotMap.limitFloor);
        limitSwitch = new DigitalInput(RobotMap.limitSwitch);
        limitScale = new DigitalInput(RobotMap.limitScale);

        speed = 1;
        position = 0;
        moving = false;
    }

    public void initDefaultCommand() {
        setDefaultCommand(new LiftCommand());
    }

    public double getMotor() {
    	return motor.get();
    }

    public void raiseLift() {
//    	motor.set(speed);
    	if(isAtScale())
    		stopLift();
    	else motor.set(speed);
    	moving = true;
    }

    public void lowerLift() {
//    	motor.set(-speed);
    	if(isAtFloor())
    		stopLift();
    	else motor.set(-speed*.60);
    	moving = false;
    }

    public void stopLift() {
        motor.set(0.0);
        moving = false;
    }

    public int getPosition() {
    	return position;
    }
    
    public void updatePosition() {
    	if(position == 0)
    		lowerLift();
    	else if(position == 2)
    		raiseLift();
    	else if(position == 1 && isAtFloor())
    		raiseLift();
    	else if(position == 1 && isAtScale())
    		lowerLift();
    		
        if((position == 0 && isAtFloor()) ||
			(position == 1 && isAtSwitch()) ||
			(position == 2 && isAtScale())) stopLift();
    }
    
    public boolean isMoving() {
    	return moving;
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
//    	lowerLift();
    	position = 0;
//    	System.out.println("In move to floor");
//    	if(!isAtFloor())
//    		lowerLift();
//    	else stopLift();
    }

    public void moveToSwitch() {
//    	if(position == 0)
//    		raiseLift();
//    	else if(position == 2)
//    		lowerLift();
    	position = 1;
//    	System.out.println("In move to switch");
//    	if(!isAtSwitch())
//	    	if(!isAtScale())
//	    		lowerLift();
//	    	else if(!isAtFloor())
//	    		raiseLift();
//	    else stopLift();
    }

    public void moveToScale() {
    	//raiseLift();
    	position = 2;
//    	System.out.println("In move to scale");
//    	if(!isAtScale()) {
//        	System.out.println("RAISING In move to scale");
//    		raiseLift();
//    	}
//    	else {
//        	System.out.println("STOPPING In move to scale");
//    		stopLift();
//    	}
    }
}
