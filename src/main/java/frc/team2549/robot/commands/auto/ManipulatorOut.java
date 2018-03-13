package frc.team2549.robot.commands.auto;

import edu.wpi.first.wpilibj.Timer;

import edu.wpi.first.wpilibj.command.Command;
import frc.team2549.robot.Robot;

/**
 *
 */
public class ManipulatorOut extends Command {

	private double time;
	private Timer timer;
	
    public ManipulatorOut(double time) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.manipulator);
    	this.time = time;
    	timer = new Timer();
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	System.out.println("ManipulatorOut");
    	timer.start();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.manipulator.pushOut();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return timer.get() >= time;
    }

    // Called once after isFinished returns true
    protected void end() {
    	timer.stop();
    	Robot.manipulator.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
