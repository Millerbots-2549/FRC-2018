package frc.team2549.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.networktables.NetworkTableEntry;
import frc.team2549.robot.Robot;

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
    	if(!Robot.manipulator.getServoReleased())
    		Robot.manipulator.servoRelease(true);
    }

    protected void execute() {

//        if (Robot.oi.ctrlManipulatorIn.get())
//            Robot.manipulator.takeIn();
//        else if (Robot.oi.ctrlManipulatorOut.get())
//            Robot.manipulator.pushOut();

        // TODO this release
        // Release Servo
//        if (Robot.oi.ctrlServoUp.get())
//            Robot.manipulator.servoRelease(true);
//        else if (Robot.oi.ctrlServoDown.get())
//            Robot.manipulator.servoRelease(false);
    	if(Robot.oi.getServoDown())
    		Robot.manipulator.servoRelease(true);
    	else if(Robot.oi.getServoUp())
    		Robot.manipulator.servoRelease(false);

    	if(Robot.vision.sensingCube())
    		Robot.manipulator.takeIn();

    	if(Robot.oi.getManipulatorOut())
    		Robot.manipulator.pushOut();
    	else if(Robot.oi.getManipulatorIn())
    		Robot.manipulator.takeIn();
    	else Robot.manipulator.stop();
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
