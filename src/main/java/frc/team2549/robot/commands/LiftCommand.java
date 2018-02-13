package frc.team2549.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.team2549.robot.Robot;

/**
 *
 */
public class LiftCommand extends Command {

    double speed, constSpeed;

    public LiftCommand() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        requires(Robot.lift);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        constSpeed = 1;
        speed = constSpeed;
    }

    protected void executeJoysticks() {
    }

    protected void executeController() {
        if (Robot.oi.ctrl.getPOV() == 0)
            Robot.lift.driveLift(speed);
        else if (Robot.oi.ctrl.getPOV() == 4)
            Robot.lift.driveLift(-speed);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        if (Robot.ctrlType == Robot.ctrlTypes.kJoysticks)
            executeJoysticks();
        else if (Robot.ctrlType == Robot.ctrlTypes.kController)
            executeController();
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
