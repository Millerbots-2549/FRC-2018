package frc.team2549.robot.subsystems;

import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.team2549.robot.RobotMap;
import frc.team2549.robot.commands.ManipulatorCommand;


/**
 *
 */
public class ManipulatorSubsystem extends Subsystem {

    private static final double SPEED = 1.0;
    private Talon motor;

    public ManipulatorSubsystem() {
        super(ManipulatorSubsystem.class.getSimpleName());
        motor = new Talon(RobotMap.manipulatorMotors);
    }

    public void initDefaultCommand() {
        setDefaultCommand(new ManipulatorCommand());
    }

    public void takeIn() {
        motor.set(SPEED);
    }

    public void pushOut() {
        motor.set(-SPEED);
    }
}

