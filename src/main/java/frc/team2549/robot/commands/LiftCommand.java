package frc.team2549.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

import frc.team2549.robot.Robot;
import frc.team2549.robot.commands.auto.LiftToFloor;
import frc.team2549.robot.commands.auto.LiftToSwitch;
import frc.team2549.robot.commands.auto.LiftToScale;

/**
 *
 */
public class LiftCommand extends Command {

    public LiftCommand() {
        super(LiftCommand.class.getSimpleName());
        requires(Robot.lift);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    protected void execute() {
//    	if(Robot.oi.ctrl.getPOV() == Robot.oi.ctrlLiftUp)
//    		Robot.lift.raiseLift();
//    	else if(Robot.oi.ctrl.getPOV() == Robot.oi.ctrlLiftDown)
//    		Robot.lift.lowerLift();
//    	else Robot.lift.stopLift();
//    	if(Robot.oi.getLiftFloor()) {
//    		System.out.println("floor");
//    		Robot.lift.moveToFloor();
//    	}
//    	else if(Robot.oi.getLiftSwitch()) {
//    		System.out.println("switch");
//    		Robot.lift.moveToSwitch();
//    	}
//    	else if(Robot.oi.getLiftScale()) {
//    		System.out.println("scale");
//    		Command c = new LiftToScale();
//    		c.start();
//    		//Robot.lift.moveToScale();
//    	}
    	
    	if(Robot.oi.getLiftDown())
    		Robot.lift.lowerLift();
    	else if(Robot.oi.getLiftUp())
    		Robot.lift.raiseLift();
    	else Robot.lift.stopLift();
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
