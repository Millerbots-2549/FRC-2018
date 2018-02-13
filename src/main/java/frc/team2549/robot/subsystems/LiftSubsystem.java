package frc.team2549.robot.subsystems;

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
    private Talon motor;

    public LiftSubsystem() {
        super(LiftSubsystem.class.getSimpleName());
        motor = new Talon(RobotMap.liftMotor);
    }

    public void initDefaultCommand() {
        setDefaultCommand(new LiftCommand());
    }

    public void raiseLift() {
        motor.set(SPEED);
    }

    public void lowerLift() {
        motor.set(-SPEED);
    }
}

