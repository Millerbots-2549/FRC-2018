package frc.team2549.robot.subsystems;

import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.team2549.robot.RobotMap;
import frc.team2549.robot.commands.ManipulatorCommand;


/**
 *
 */
public class ManipulatorSubsystem extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    private Talon motors;

    public ManipulatorSubsystem() {
        motors = new Talon(RobotMap.manipulatorMotors);
    }

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
        setDefaultCommand(new ManipulatorCommand());
    }

    public void spinMotors(double speed) {
        motors.set(speed);
    }
}

