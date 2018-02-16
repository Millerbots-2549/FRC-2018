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
        limitFloor = new DigitalInput(7);
        limitSwitch = new DigitalInput(8);
        limitScale = new DigitalInput(9);
                
        position = 0;
    }

    public void initDefaultCommand() {
        setDefaultCommand(new LiftCommand());
    }

    public void raiseLift() {
        motor.set(speed);
    }

    public void lowerLift() {
        motor.set(-speed/2);
    }

    public void stopLift() {
        motor.set(0.0);
    }
    
    public int getPosition() {
    	return position;
    }
    
    public boolean isAtFloor() {
    	return limitFloor.get();
    }
    
    public boolean isAtSwitch() {
    	return limitSwitch.get();
    }
    
    public boolean isAtScale() {
    	return limitScale.get();
    }
}

