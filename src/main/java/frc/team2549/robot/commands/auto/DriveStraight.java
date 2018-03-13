package frc.team2549.robot.commands.auto;

import edu.wpi.first.wpilibj.command.Command;
import frc.team2549.robot.Robot;
import edu.wpi.first.wpilibj.Timer;

/**
 *
 */
public class DriveStraight extends Command {

	private double distance, startingDistance;
	private double speed, amount, angle, startingAngle, targetAngle;
	
    public DriveStraight(double distance, double speed) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        requires(Robot.drivetrain);
        this.distance = distance;
        this.speed = -speed;
    }
    
    public DriveStraight(double distance, double speed, double targetAngle) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        requires(Robot.drivetrain);
        this.distance = distance;
        this.speed = -speed;
        this.targetAngle = targetAngle;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	//Robot.drivetrain.resetSensors();
    	System.out.println("DriveStraight");
    	startingAngle = Robot.drivetrain.getAngle();
    	startingDistance = Robot.drivetrain.getEncoderAvg();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	angle = Robot.drivetrain.getAngle();
    	amount = .01*(startingAngle - angle);
    	
    	Robot.drivetrain.driveTank(speed - amount, speed + amount);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	return Robot.drivetrain.getEncoderAvg() - startingDistance >= distance;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.drivetrain.driveTank(0, 0);
    	Robot.drivetrain.disableMotors();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
//written entirely by Victor LaBrie