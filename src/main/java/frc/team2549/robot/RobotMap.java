package frc.team2549.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
    // For example to map the left and right motors, you could define the
    // following variables to use with your drivetrain subsystem.
    // public static int leftMotor = 1;
    // public static int rightMotor = 2;

    // If you are using multiple modules, make sure to define both the port
    // number and the module. For example you with a rangefinder:
    // public static int rangefinderPort = 1;
    // public static int rangefinderModule = 1;

	// PWM
    public static int leftDriveMotor = 0;
    public static int rightDriveMotor = 1;
    public static int manipulatorMotors = 2;
    public static int liftMotor = 3;
    public static int releaseServo = 4;
    // DIO
    public static int[] leftDriveEnc = {0, 1};
    public static int[] rightDriveEnc = {2, 3};
    public static int limitFloor = 4;
    public static int limitSwitch = 5;
    public static int limitScale = 6;
    public static int boxIn = 7;
    // Analog
    public static int leftSonar = 0;
    public static int rightSonar = 1;
}
