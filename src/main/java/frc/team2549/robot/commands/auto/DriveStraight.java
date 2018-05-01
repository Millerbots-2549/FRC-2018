package frc.team2549.robot.commands.auto;

import edu.wpi.first.wpilibj.command.Command;
import frc.team2549.robot.Robot;
import edu.wpi.first.wpilibj.Timer;

/**
 *
 */
public class DriveStraight extends Command {

	private double maxSpeed, speed, amount, angle,
		startingAngle, distance, startingDistance;
	double timeout, startTime;
	
    public DriveStraight(double distance, double speed) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        requires(Robot.drivetrain);
        this.distance = distance;
        this.maxSpeed = speed;
        timeout = 5;
    	startTime = Timer.getFPGATimestamp();
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.drivetrain.resetSensors();
    	System.out.println("DriveStraight");
    	startingAngle = Robot.drivetrain.getAngle();
    	startingDistance = Robot.drivetrain.getEncoderAvg();
    	speed = 0;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(speed < maxSpeed)
    		speed += .03;
    	
    	angle = Robot.drivetrain.getAngle();
    	amount = .02*(startingAngle - angle);
    	
    	Robot.drivetrain.driveTank(speed + amount, speed - amount);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	return Math.abs(Robot.drivetrain.getEncoderAvg() - startingDistance) >= distance
    			|| Timer.getFPGATimestamp() - startTime >= timeout;
    }

    // Called once after isFinished returns true
    protected void end() {
    	System.out.println("DriveStraight finished with " + Robot.drivetrain.getEncoderAvg());
    	Robot.drivetrain.driveTank(0, 0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
//written entirely by Victor LaBrie