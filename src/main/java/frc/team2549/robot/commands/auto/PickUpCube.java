package frc.team2549.robot.commands.auto;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import frc.team2549.robot.Robot;

/**
 *
 */
public class PickUpCube extends Command {
	
	private double timeout, startTime, time;
    
    public PickUpCube() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	System.out.println("Picking Cube Up");
    	startTime = Timer.getFPGATimestamp();
    	timeout = 3;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	time = Timer.getFPGATimestamp();
    	double turn = .6;
    	int resX = Robot.vision.camRes_x;
    	int posX = Robot.vision.getCubeX();
    	System.out.println(posX + " " + Robot.vision.getCubeX());
    	double speed = 0.65;
//    	double left = (posX <= 90) ? posX / (resX/2) * speed : speed;
//    	double right = (posX >= 90) ? speed : speed - left;

    	double left = 0.0;
    	double right = 0.0;

    	if(posX <= resX/2)
    		right = speed;
    	else if(posX >= resX/2)
    		left = speed;

    	Robot.drivetrain.driveTank(left, right);
        Robot.manipulator.takeIn();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return (Robot.vision.getCubeX() == 0 && Robot.vision.getCubeY() == 0)
        		|| Robot.manipulator.cubeIn() || Robot.drivetrain.getSonarAvg() <= 280
        		|| Robot.oi.getManipulatorOut() || Robot.oi.getManipulatorOut()
        		|| (Robot.oi.getDriveL() < -0.2 || Robot.oi.getDriveR() < -0.2)
        		|| time - startTime >= timeout;
    }

    // Called once after isFinished returns true
    protected void end() {
    	System.out.println("Stopped Picking Cube Up");
    	Robot.manipulator.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
