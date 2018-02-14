package org.usfirst.frc.team2549.robot.commands;

import org.usfirst.frc.team2549.robot.Robot;
import org.usfirst.frc.team2549.robot.commands.auto.ManipulatorIn;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.networktables.NetworkTable;

/**
 *
 */
public class ManipulatorCommand extends Command {

	double speed, speedConst;
	NetworkTable table;
	int cube_pos_x, cube_pos_y;
	boolean cube_sensing;

    public ManipulatorCommand() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.manipulator);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	speedConst = 1;
    	speed = speedConst;
    	Robot.manipulator.servoRelease(false);

    	table = NetworkTable.getTable("SmartDashboard");
    }

    protected void executeJoysticks() {
    	if(Robot.oi.joyManipulatorIn.get())
    		speed = speedConst;
    	else if(Robot.oi.joyManipulatorOut.get())
    		speed = -speedConst;
    	
    	//Robot.oi.ctrlManipulatorIn.whenPressed(new ManipulatorIn());
    }

    protected void executeController() {
    	// Motors
    	speed = Robot.oi.ctrl.getRawAxis(Robot.oi.ctrlBoxIn)
    			- Robot.oi.ctrl.getRawAxis(Robot.oi.ctrlBoxOut);

    	if(Robot.oi.ctrlManipulatorIn.get())
    		speed = speedConst;
    	else if(Robot.oi.ctrlManipulatorOut.get())
    		speed = -speedConst;

    	// Release Servo
    	if(Robot.oi.ctrlServoUp.get())
    		Robot.manipulator.servoRelease(true);
    	else if(Robot.oi.ctrlServoDown.get())
    		Robot.manipulator.servoRelease(false);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(Robot.ctrlType == Robot.ctrlTypes.kJoysticks)
    		executeJoysticks();
    	else if(Robot.ctrlType == Robot.ctrlTypes.kController)
    		executeController();
    	
    	cube_sensing = table.getBoolean("sensing_cube", false);
    	cube_pos_x = (int)table.getNumber("cube_pos_x", 0);
    	cube_pos_y = (int)table.getNumber("cube_pos_y", 0);
    	
    	if(!Robot.oi.ctrlManipulatorOut.get() && !Robot.oi.joyManipulatorOut.get())
    		if(cube_sensing && !Robot.manipulator.cubeIn())
    			speed = speedConst;
//    	else if(!cube_sensing)
//    		speed = 0;
    	
    	Robot.manipulator.spinMotors(speed);
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
