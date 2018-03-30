package frc.team2549.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.networktables.NetworkTableEntry;
import frc.team2549.robot.Robot;
import frc.team2549.robot.commands.auto.PickUpCube;

/**
 *
 */
public class ManipulatorCommand extends Command {

    public ManipulatorCommand() {
        super(ManipulatorCommand.class.getSimpleName());
        requires(Robot.manipulator);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
//    	if(!Robot.manipulator.getServoReleased())
//    		Robot.manipulator.servoRelease(true);
    	
    }

    protected void execute() {
    	Command c = new PickUpCube();
    	
    	if(Robot.oi.getServoDown())
    		Robot.manipulator.servoRelease(true);
    	else if(Robot.oi.getServoUp())
    		Robot.manipulator.servoRelease(false);

    	if(Robot.oi.getManipulatorOut()) {
    		Robot.manipulator.pushOut();
    		c.cancel();
    	}
    	else if(Robot.oi.getManipulatorIn() && !Robot.manipulator.cubeIn()) {
    		Robot.manipulator.takeIn();
    		c.cancel();
    	}
//    	else Robot.manipulator.stop();
    	else {
    		if(Robot.oi.getPickUpCube()) {
       			c.start();
    		} else if(!c.isRunning()) {
    			Robot.manipulator.stop();
    		}
//    		if(Robot.vision.sensingCube() == 1 &&
//    				!Robot.manipulator.cubeIn() &&
//    				Robot.lift.isAtFloor())
//        		Robot.manipulator.takeIn();
//        	else Robot.manipulator.stop();
    	}
    }

    // https://wpilib.screenstepslive.com/s/currentCS/m/cpp/l/241909-using-limit-switches-to-control-behavior
    //
    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.manipulator.servoRelease(false);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
