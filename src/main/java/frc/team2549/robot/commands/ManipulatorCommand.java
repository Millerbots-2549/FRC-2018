package frc.team2549.robot.commands;


import edu.wpi.first.wpilibj.command.Command;
import frc.team2549.robot.Robot;

/**
 *
 */
public class ManipulatorCommand extends MyCommand {

    public ManipulatorCommand() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        super(ManipulatorCommand.class.getSimpleName());
        requires(Robot.manipulator);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    protected void executeJoysticks() {
        if (Robot.oi.joyManipulatorIn.get())
            Robot.manipulator.takeIn();
        else if (Robot.oi.joyManipulatorOut.get())
            Robot.manipulator.pushOut();
    }

    protected void executeController() {
        if (Robot.oi.ctrlManipulatorIn.get())
            Robot.manipulator.takeIn();
        else if (Robot.oi.ctrlManipulatorOut.get())
            Robot.manipulator.pushOut();
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
