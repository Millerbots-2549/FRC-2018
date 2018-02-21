package frc.team2549.robot.commands.auto;

import edu.wpi.first.wpilibj.command.Command;
import frc.team2549.robot.Robot;

/**
 *
 */
public class DrivePrecision extends Command {

	private int distance;
	private double speed;
	public enum side { kLeft, kRight };
	private side _side;
	
    public DrivePrecision(side _side, int distance, double speed) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        requires(Robot.drivetrain);
        this._side = _side;
        this.distance = distance;
        this.speed = -speed;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.drivetrain.resetEncoders();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(_side == side.kLeft)
    		Robot.drivetrain.driveTank(speed, 0);
    	else if(_side == side.kRight)
    		Robot.drivetrain.driveTank(0, speed);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	return Robot.drivetrain.getEncoder(_side == side.kLeft ? 0 : 1) > distance;
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