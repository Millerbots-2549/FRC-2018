package org.usfirst.frc.team2549.robot.commands;

import org.usfirst.frc.team2549.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

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
    	
    	if(Robot.oi.joyLiftUp.get() && Robot.lift.upperLimit.get())
    		speed = 1;
    	else if(Robot.oi.joyLiftDown.get() && Robot.lift.lowerLimit.get())
    		speed = -.5;
    	else speed = 0;
    	
//    	if(!Robot.lift.lowerLimit.get() && Robot.oi) {
//    		speed = 0;
//    		SmartDashboard.putBoolean("limit switched", true);
//    	} else SmartDashboard.putBoolean("limit switched", false);
    	
    	Robot.lift.driveLift(speed);
    }
    
    protected void executeController() {
//    	if(Robot.oi.ctrl.getPOV() == Robot.oi.ctrlLiftUp)
//    		Robot.lift.driveLift(speed);
//    	else if(Robot.oi.ctrl.getPOV() == Robot.oi.ctrlLiftDown)
//    		Robot.lift.driveLift(-0.5);
//    	else Robot.lift.driveLift(0);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(Robot.ctrlType == Robot.ctrlTypes.kJoysticks)
    		executeJoysticks();
    	else if(Robot.ctrlType == Robot.ctrlTypes.kController)
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
