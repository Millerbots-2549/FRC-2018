package frc.team2549.robot.commands;

import frc.team2549.robot.Robot;

/**
 *
 */
public class LiftCommand extends MyCommand {

    public LiftCommand() {
        super(LiftCommand.class.getSimpleName());
        requires(Robot.lift);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    protected void executeJoysticks() {

        if (Robot.oi.joyLiftUp.get() && Robot.lift.upperLimit.get())
            Robot.lift.raiseLift();
        else if (Robot.oi.joyLiftDown.get() && Robot.lift.lowerLimit.get())
            Robot.lift.lowerLift();
        else
            Robot.lift.stopLift();
    }

    protected void executeController() {
//    	if(Robot.oi.ctrl.getPOV() == Robot.oi.ctrlLiftUp)
//    		Robot.lift.raiseLift();
//    	else if(Robot.oi.ctrl.getPOV() == Robot.oi.ctrlLiftDown)
//    		Robot.lift.lowerLift();
//    	else Robot.lift.stopLift();
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
