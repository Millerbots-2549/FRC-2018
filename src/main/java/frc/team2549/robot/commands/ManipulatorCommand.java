package frc.team2549.robot.commands;


import edu.wpi.first.wpilibj.command.Command;
import frc.team2549.robot.Robot;

/**
 *
 */
public class ManipulatorCommand extends Command {

    double speed, speedConst;

    public ManipulatorCommand() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        requires(Robot.manipulator);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        speedConst = 1;
    }

    protected void executeJoysticks() {
        if (Robot.oi.joyManipulatorIn.get())
            Robot.manipulator.spinMotors(speed);
        else if (Robot.oi.joyManipulatorOut.get())
            Robot.manipulator.spinMotors(-speed);
    }

    protected void executeController() {
        speed = Robot.oi.ctrl.getRawAxis(Robot.oi.ctrlBoxIn)
                - Robot.oi.ctrl.getRawAxis(Robot.oi.ctrlBoxOut);

        if (Robot.oi.ctrlManipulatorIn.get())
            speed = speedConst;
        else if (Robot.oi.ctrlManipulatorOut.get())
            speed = -speedConst;

        Robot.manipulator.spinMotors(speed);
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
