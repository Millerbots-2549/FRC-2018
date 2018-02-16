package frc.team2549.robot.commands.auto;

import edu.wpi.first.wpilibj.command.Command;
import frc.team2549.robot.Robot;

/**
 *
 */
public class ManipulatorIn extends Command {

    double speed;

    public ManipulatorIn() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        requires(Robot.manipulator);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        speed = 1;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        //Robot.manipulator.spinMotors(speed);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
