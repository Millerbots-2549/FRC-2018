package frc.team2549.robot.commands;

import frc.team2549.robot.Robot;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class CameraCommand extends Command {
	
//	NetworkTableEntry xEntry;
//	NetworkTableEntry yEntry;
//	private int cube_x;
//	private int cube_y;
//	private boolean sensing_cube;

    public CameraCommand() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        super(CameraCommand.class.getSimpleName());
        //requires(Robot.drivetrain);
        //requires(Robot.manipulator);
        //requires(Robot.vision);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
//    	xEntry = Robot.table.getEntry("cube_x");
//      yEntry = Robot.table.getEntry("cube_y");
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        //CameraServer.getInstance().startAutomaticCapture();
//        xEntry.setNumber(cube_x);
//        yEntry.setNumber(cube_y);
//        if(Robot.vision.sensingCube()) {
//        	Robot.manipulator.takeIn();
//        }
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
