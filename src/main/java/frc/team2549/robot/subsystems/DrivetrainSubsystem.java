package frc.team2549.robot.subsystems;

import com.analog.adis16448.frc.ADIS16448_IMU;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SafePWM;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.AnalogInput;

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
    private Encoder leftEnc;
    private Encoder rightEnc;
    private AnalogInput leftSonar;
    private AnalogInput rightSonar;
    private ADXRS450_Gyro gyro;
    //private ADIS16448_IMU imu;

    private double speed;

    public DrivetrainSubsystem() {
        super(DrivetrainSubsystem.class.getSimpleName());
        leftMotor = new Talon(RobotMap.leftDriveMotor);
        rightMotor = new Talon(RobotMap.rightDriveMotor);
        leftMotor.setInverted(true);
        rightMotor.setInverted(true);
        drive = new DifferentialDrive(leftMotor, rightMotor);

        leftEnc = new Encoder(RobotMap.leftDriveEnc[0], RobotMap.leftDriveEnc[1]);
        rightEnc = new Encoder(RobotMap.rightDriveEnc[0], RobotMap.rightDriveEnc[1]);
        
        leftSonar = new AnalogInput(RobotMap.leftSonar);
        rightSonar = new AnalogInput(RobotMap.rightSonar);
        gyro = new ADXRS450_Gyro();
//        try { imu = new ADIS16448_IMU(); }
//        finally { System.out.println("Error: rawr xD"); };
        speed = 1;
    }

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
        setDefaultCommand(new DriveCommand());
    }

    public void driveTank(double left, double right) {
        drive.tankDrive(left * speed, right * speed);
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public double getMotor(int side) {
        switch (side) {
            case 0:
                return leftMotor.get();
            case 1:
                return rightMotor.get();
            default:
                return .0;
        }
    }

    public void disableMotors() {
    	leftMotor.setDisabled();
    	rightMotor.setDisabled();
    }

    public double getEncoder(int side) {
        switch (side) {
            case 0:
                return leftEnc.get();
            case 1:
                return -rightEnc.get();
            default:
                return .0;
        }
    }
    
    public double getEncoderAvg() {
    	return getEncoder(1);
    	//return (getEncoder(0) + getEncoder(1)) / 2;
    }
    
    public void resetEncoders() {
    	leftEnc.reset();
    	rightEnc.reset();
    }

    public double getAngle() {
    	return gyro.getAngle();
    }

//    public ADIS16448_IMU getIMU() {
//        return imu;
//    }

    public int getSonar(int side) {
    	switch (side) {
        case 0:
            return leftSonar.getValue();
        case 1:
            return rightSonar.getValue();
        default:
            return 0;
    	}
    }
    
    public void resetSensors() {
    	gyro.reset();
    	resetEncoders();
    }
}

