package frc.team2549.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.team2549.robot.Robot;

/**
 *
 */
public class DriveCommand extends Command {

    public DriveCommand() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        requires(Robot.drivetrain);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    protected void executeJoysticks() {
        Robot.drivetrain.driveTank(Robot.oi.joyL.getRawAxis(Robot.oi.joyDriveAxis),
                Robot.oi.joyR.getRawAxis(Robot.oi.joyDriveAxis));
    }

    protected void executeController() {
        Robot.drivetrain.driveTank(Robot.oi.ctrl.getRawAxis(Robot.oi.ctrlDriveL),
                Robot.oi.ctrl.getRawAxis(Robot.oi.ctrlDriveR));
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
