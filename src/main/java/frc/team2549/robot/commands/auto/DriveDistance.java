package frc.team2549.robot.commands.auto;

import edu.wpi.first.wpilibj.command.Command;
import frc.team2549.robot.Robot;

/**
 *
 */
public class DriveDistance extends Command {

	private double distance, startingDistance;
	private double leftSpeed, rightSpeed;
	
    public DriveDistance(double distance, double leftSpeed, double rightSpeed) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        requires(Robot.drivetrain);
        this.distance = distance;
        this.leftSpeed = -leftSpeed;
        this.rightSpeed = -rightSpeed;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	System.out.println("DriveDistance");
//    	Robot.drivetrain.resetSensors();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.drivetrain.driveTank(leftSpeed, rightSpeed);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	return Robot.drivetrain.getEncoderAvg() >= distance;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.drivetrain.driveTank(0, 0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
//written entirely by Victor LaBrie