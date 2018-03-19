package frc.team2549.robot.commands.auto;

import edu.wpi.first.wpilibj.command.Command;
import frc.team2549.robot.Robot;

/**
 *
 */
public class TurnGyro extends Command {

	private double angle, startingAngle, speed;
	
    public TurnGyro(double angle, double speed) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        requires(Robot.drivetrain);
        this.angle = angle;
        this.speed = speed;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	//Robot.drivetrain.resetSensors();
    	System.out.println("TurnGyro");
    	startingAngle = Robot.drivetrain.getAngle();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.drivetrain.driveTank(angle > 0 ?  speed : -speed,
    							   angle > 0 ? -speed :  speed);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	return Math.abs(Robot.drivetrain.getAngle()) - Math.abs(startingAngle) >= Math.abs(angle);
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