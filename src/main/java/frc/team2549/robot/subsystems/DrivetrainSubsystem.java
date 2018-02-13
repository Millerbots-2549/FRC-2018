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

    private DifferentialDrive drive;
    private Talon leftMotor;
    private Talon rightMotor;

    public DrivetrainSubsystem() {
        super(DrivetrainSubsystem.class.getSimpleName());
        leftMotor = new Talon(RobotMap.leftDriveMotor);
        rightMotor = new Talon(RobotMap.rightDriveMotor);
        drive = new DifferentialDrive(leftMotor, rightMotor);
    }

    public void initDefaultCommand() {
        setDefaultCommand(new DriveCommand());
    }

    public void driveTank(double left, double right) {
        drive.tankDrive(left, right);
    }

}

