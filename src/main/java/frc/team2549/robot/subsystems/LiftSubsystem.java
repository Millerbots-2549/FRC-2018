package frc.team2549.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.team2549.robot.RobotMap;
import frc.team2549.robot.commands.LiftCommand;

/**
 *
 */
public class LiftSubsystem extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    private static final double SPEED = 1.0;

    public DigitalInput upperLimit;
    public DigitalInput lowerLimit;
    private Talon motor;
    //public AnalogInput hal;

    public LiftSubsystem() {
        super(LiftSubsystem.class.getSimpleName());
        motor = new Talon(RobotMap.liftMotor);
        lowerLimit = new DigitalInput(4);
        upperLimit = new DigitalInput(5);
        //hal = new AnalogInput(0);
    }

    public void initDefaultCommand() {
        setDefaultCommand(new LiftCommand());
    }

    public void raiseLift() {
        motor.set(SPEED);
    }

    public void lowerLift() {
        motor.set(-0.5);
    }

    public void stopLift() {
        motor.set(0.0);
    }

}

