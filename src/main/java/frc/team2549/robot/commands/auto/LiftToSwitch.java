package frc.team2549.robot.commands.auto;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import frc.team2549.robot.Robot;

/**
 *
 */
public class LiftToSwitch extends Command {

	double timeout, startTime;
    public LiftToSwitch() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        requires(Robot.lift);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
//    	Robot.lift.raiseLift();
    	timeout = 2;
    	startTime = Timer.getFPGATimestamp();
    	System.out.println("LiftToSwitch");
    	if(Robot.lift.isAtFloor())
    		Robot.lift.raiseLift();
    	else if(Robot.lift.isAtScale())
    		Robot.lift.lowerLift();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
//        return Timer.getFPGATimestamp() - startingTime >= time;
    	return Robot.lift.isAtSwitch()
    			|| Timer.getFPGATimestamp() - startTime >= timeout;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.lift.stopLift();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
//written entirely by Victor LaBrie