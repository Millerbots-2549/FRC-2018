package org.usfirst.frc.team2549.robot.subsystems;

import org.usfirst.frc.team2549.robot.RobotMap;
import org.usfirst.frc.team2549.robot.commands.DriveCommand;

import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class DrivetrainSubsystem extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

	private RobotDrive drive;
	private Talon leftMotor;
	private Talon rightMotor;
	
	enum motorSide { kLeft, kRight };
	
	public DrivetrainSubsystem() {
		leftMotor = new Talon(RobotMap.leftDriveMotor);
		rightMotor = new Talon(RobotMap.rightDriveMotor);
		drive = new RobotDrive(leftMotor, rightMotor);
	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new DriveCommand());
    }
    
    public void driveTank(double left, double right) {
    	drive.tankDrive(left, right);
    }
    
    public double getMotor(motorSide side) {
    	switch(side)
    	{
    	case kLeft:
    		return leftMotor.get();
		case kRight:
    		return rightMotor.get();
		default:
			return .0123456789;
    	}
    }
}

