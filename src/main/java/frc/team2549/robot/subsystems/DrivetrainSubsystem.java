package frc.team2549.robot.subsystems;

import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.team2549.robot.RobotMap;
import frc.team2549.robot.commands.DriveCommand;

/**
 *
 */
public class DrivetrainSubsystem extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    private DifferentialDrive drive;
    private Talon leftMotor;
    private Talon rightMotor;

    public DrivetrainSubsystem() {
        leftMotor = new Talon(RobotMap.leftDriveMotor);
        rightMotor = new Talon(RobotMap.rightDriveMotor);
        drive = new DifferentialDrive(leftMotor, rightMotor);
    }

    ;

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
        setDefaultCommand(new DriveCommand());
    }

    public void driveTank(double left, double right) {
        drive.tankDrive(left, right);
    }

    public double getMotor(motorSide side) {
        switch (side) {
            case kLeft:
                return leftMotor.get();
            case kRight:
                return rightMotor.get();
            default:
                return .0123456789;
        }
    }

    enum motorSide {kLeft, kRight}
}

