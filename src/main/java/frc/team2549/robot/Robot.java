package frc.team2549.robot;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.team2549.robot.commands.auto.AutoLeftRightScale;
import frc.team2549.robot.commands.auto.AutoLeftScale;
import frc.team2549.robot.commands.auto.AutoLeftSwitch;
import frc.team2549.robot.commands.auto.AutoMidLeftSwitch;
import frc.team2549.robot.commands.auto.AutoMidRightSwitch;
import frc.team2549.robot.commands.auto.AutoRightLeftScale;
import frc.team2549.robot.commands.auto.AutoRightScale;
import frc.team2549.robot.commands.auto.AutoRightSwitch;
import frc.team2549.robot.commands.auto.AutoTest;
import frc.team2549.robot.commands.auto.AutoDefault;
import frc.team2549.robot.subsystems.CameraSubsystem;
import frc.team2549.robot.subsystems.DrivetrainSubsystem;
import frc.team2549.robot.subsystems.LiftSubsystem;
import frc.team2549.robot.subsystems.ManipulatorSubsystem;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {

	public static final DrivetrainSubsystem drivetrain = new DrivetrainSubsystem();
	public static final ManipulatorSubsystem manipulator = new ManipulatorSubsystem();
	public static final LiftSubsystem lift = new LiftSubsystem();
	public static final CameraSubsystem vision = new CameraSubsystem();

	private static final SendableChooser<Command> autoChooser = new SendableChooser<>();
	private static final SendableChooser<Objective> objChooser = new SendableChooser<>();
	private static final SendableChooser<Position> pstnChooser = new SendableChooser<>();
	private static final SendableChooser<OI.ControllerType> ctrlChooser = new SendableChooser<>();
	private static final SendableChooser<SpeedType> speedChooser = new SendableChooser<>();

	public static OI oi;
	public static NetworkTableInstance tableInst;
	public static NetworkTable table;
	Command autonomousCommand;
	private double time;

	// private static AutoRecord recorder;
	// private static AutoPlay player;
	// private static boolean isRecording;

	public static OI.ControllerType getControllerType() {
		return ctrlChooser.getSelected();
	}

	public static SpeedType getSpeedType() {
		return speedChooser.getSelected();
	}

	public static Position getStartingPosition() {
		return pstnChooser.getSelected();
	}

	public static Objective getObjective() {
		return objChooser.getSelected();
	}

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		oi = new OI();

		tableInst = NetworkTableInstance.getDefault();
		table = tableInst.getTable("SmartDashboard");

		pstnChooser.addDefault("Left", Position.left);
		pstnChooser.addObject("Middle", Position.middle);
		pstnChooser.addObject("Right", Position.right);

		objChooser.addDefault("Scale", Objective.scale);
		objChooser.addObject("Switch", Objective.kswitch);
		objChooser.addObject("No Auto", Objective.portal);
		objChooser.addObject("Switch Only", Objective.switchOnly);
		objChooser.addObject("Scale Only", Objective.scaleOnly);

		autoChooser.addDefault("Left Scale", new AutoLeftScale());
		autoChooser.addObject("Left Switch", new AutoLeftSwitch());
		autoChooser.addObject("MidLeft Switch", new AutoMidLeftSwitch());
		autoChooser.addObject("MidRight Switch", new AutoMidRightSwitch());
		autoChooser.addObject("Right Scale", new AutoRightScale());
		autoChooser.addObject("Right Switch", new AutoRightSwitch());

		ctrlChooser.addDefault("Joysticks", OI.ControllerType.joystick);
		ctrlChooser.addObject("Controller", OI.ControllerType.controller);

		speedChooser.addDefault("Full Speed", SpeedType.full);
		speedChooser.addObject("Half Speed", SpeedType.half);

		// SmartDashboard.putData("Auto mode", autoChooser);
		// SmartDashboard.putData("Controller type", ctrlChooser);
//		SmartDashboard.putData("Speed", speedChooser);
		SmartDashboard.putData("Position", pstnChooser);
		SmartDashboard.putData("Objective", objChooser);

		// table = NetworkTableInstance.getTable("SmartDashboard");
	}

	/**
	 * This function is called once each time the robot enters Disabled mode.
	 * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
	 */
	@Override
	public void disabledInit() {

	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * getString code to get the auto name from the text box below the Gyro
	 * <p>
	 * You can add additional auto modes by adding additional commands to the
	 * chooser code above (like the commented example) or additional comparisons
	 * to the switch structure below with additional strings & commands.
	 */
	@Override
	public void autonomousInit() {
		drivetrain.resetSensors();

		Command autoCommand = null;
		String gameData = fetchGameData();
		if (gameData != null && gameData.length() > 0) {
			if (pstnChooser.getSelected() == Position.middle) {
				autoCommand = middlePositionAuto(gameData);
			} else if (pstnChooser.getSelected() == Position.right) {
				autoCommand = rightPositionAuto(gameData);
			} else if (pstnChooser.getSelected() == Position.left) {
				autoCommand = leftPositionAuto(gameData);
			} else {
				autoCommand = testPositionAuto(gameData);
			}
		}

		this.autonomousCommand = autoCommand;
		if (this.autonomousCommand == null) {
			this.autonomousCommand = getDefaultAutoCommand();
		}

		manipulator.servoRelease(true);
		drivetrain.resetSensors();

		time = Timer.getFPGATimestamp();
		// schedule the autonomous command (example)
		if (autonomousCommand != null) autonomousCommand.start();
	}

	private Command getDefaultAutoCommand() {
		System.out.println("Auto Left Scale");
		return new AutoDefault();
	}

	private Command testPositionAuto(String gameData) {
		final Command autoCommand;
		if (gameData.charAt(0) == 'T') {
			System.out.println("Auto Test");
			autoCommand = new AutoTest();
		} else if (gameData.charAt(0) == 'N') {
			System.out.println("No Auto");
			autoCommand = null;
		} else {
			autoCommand = null;
		}
		return autoCommand;
	}

	private Command middlePositionAuto(String gameData) {
		final Command autoCommand;

		// MIDDLE POSITION, LEFT SWITCH
		if (gameData.charAt(0) == 'L') {
			System.out.println("Auto Middle Left Switch");
			autoCommand = new AutoMidLeftSwitch();
		}

		// MIDDLE POSITION, RIGHT SWITCH
		else if (gameData.charAt(0) == 'R') {
			System.out.println("Auto Middle Right Switch");
			autoCommand = new AutoMidRightSwitch();
		} else {
			autoCommand = null;
		}

		return autoCommand;
	}

	private Command leftPositionAuto(String gameData) {
		final Command autoCommand;
		// switch
		if ((gameData.charAt(0) == 'L' && objChooser.getSelected() == Objective.kswitch)
				|| (gameData.charAt(1) == 'R' && objChooser.getSelected() == Objective.scale && gameData.charAt(0) == 'L')
				|| (objChooser.getSelected() == Objective.switchOnly && gameData.charAt(0) == 'L')) {
			System.out.println("Auto Left Switch");
			autoCommand = new AutoLeftSwitch();
		}
		// scale
		else if ((gameData.charAt(0) == 'R' && objChooser.getSelected() == Objective.kswitch
				&& gameData.charAt(1) == 'L') || (gameData.charAt(1) == 'L'
				&& objChooser.getSelected() == Objective.scale)
				|| (objChooser.getSelected() == Objective.scaleOnly && gameData.charAt(0) == 'R')) {
			System.out.println("Auto Left Scale");
			autoCommand = new AutoLeftScale();
		}
		// TODO we dont realllly need this last priority
		else if (gameData.charAt(1) == 'R' && objChooser.getSelected() == Objective.scale
				&& gameData.charAt(0) == 'R') {
			System.out.println("Auto Left Right Scale");
			autoCommand = null;
		} else {
			autoCommand = null;
		}

		return autoCommand;
	}

	private Command rightPositionAuto(String gameData) {
//		final Command autoCommand;
//
//		if (gameData.charAt(0) == 'R' && objChooser.getSelected() == Objective.kswitch) {
//			System.out.println("Auto Right Right Switch");
//			autoCommand = new AutoRightSwitch();
//		} else if (gameData.charAt(0) == 'L' && objChooser.getSelected() == Objective.kswitch
//				&& gameData.charAt(1) == 'R') {
//			System.out.println("Auto Right Scale");
//			autoCommand = new AutoRightScale();
//		} else if (gameData.charAt(1) == 'R' && objChooser.getSelected() == Objective.scale) {
//			System.out.println("Auto Right Scale");
//			autoCommand = new AutoRightScale();
//		} else if (gameData.charAt(1) == 'L' && objChooser.getSelected() == Objective.scale
//				&& gameData.charAt(0) == 'R') {
//			System.out.println("Auto Right Right Switch");
//			autoCommand = new AutoRightSwitch();
//		} else if (gameData.charAt(1) == 'L' && objChooser.getSelected() == Objective.scale
//				&& gameData.charAt(0) == 'L') {
//			System.out.println("Auto Right Left Scale");
//			autoCommand = null;
//		} else {
//			autoCommand = null;
//		}
//		return autoCommand;
		final Command autoCommand;
		// switch
		if ((gameData.charAt(0) == 'R' && objChooser.getSelected() == Objective.kswitch)
				|| (gameData.charAt(1) == 'L' && objChooser.getSelected() == Objective.scale && gameData.charAt(0) == 'R')
				|| (objChooser.getSelected() == Objective.switchOnly && gameData.charAt(0) == 'R')) {
			System.out.println("Auto Right Switch");
			autoCommand = new AutoRightSwitch();
		}
		// scale
		else if ((gameData.charAt(0) == 'L' && objChooser.getSelected() == Objective.kswitch
				&& gameData.charAt(1) == 'R') || (gameData.charAt(1) == 'R'
				&& objChooser.getSelected() == Objective.scale)
				|| (objChooser.getSelected() == Objective.scaleOnly && gameData.charAt(0) == 'L')) {
			System.out.println("Auto Right Scale");
			autoCommand = new AutoRightScale();
		}
		// TODO again last priority
		else if (gameData.charAt(1) == 'L' && objChooser.getSelected() == Objective.scale
				&& gameData.charAt(0) == 'L') {
			System.out.println("Auto Right Left Scale");
			autoCommand = null;
		} else {
			autoCommand = null;
		}

		return autoCommand;
	}

	private String fetchGameData() {
		String gameData = null;

		try {
			while (gameData == null || gameData == "" && DriverStation.getInstance().isAutonomous()) {
				gameData = DriverStation.getInstance().getGameSpecificMessage();
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return gameData;
	}

	/**
	 * This function is called periodically during autonomous
	 */
	@Override
	public void autonomousPeriodic() {
		if(Timer.getFPGATimestamp() - time >= 1)
			manipulator.servoRelease(false);
		Scheduler.getInstance().run();
		updateDashboard();
	}

	@Override
	public void teleopInit() {
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to
		// continue until interrupted by another command, remove
		// this line or comment it out.
		if (autonomousCommand != null) autonomousCommand.cancel();

		manipulator.servoRelease(false);
		drivetrain.resetSensors();

		updateDashboard();
	}

	/**
	 * This function is called periodically during operator control
	 */
	@Override
	public void teleopPeriodic() {
		switch (speedChooser.getSelected()) {
		case full:
			drivetrain.setSpeed(1);
			break;
		case half:
			drivetrain.setSpeed(.5);
			break;
		}

		Scheduler.getInstance().run();
		updateDashboard();
	}

	/**
	 * This function is called periodically during test mode
	 */
	@Override
	public void testPeriodic() {
	}

	private void updateDashboard() {
		// Drivetrain
		SmartDashboard.putNumber("Left Motors", drivetrain.getMotor(0));
		SmartDashboard.putNumber("Right Motors", drivetrain.getMotor(1));
		SmartDashboard.putNumber("Left Encoder", drivetrain.getEncoder(0));
		SmartDashboard.putNumber("Right Encoder", drivetrain.getEncoder(1));
		SmartDashboard.putNumber("Encoder Average", drivetrain.getEncoderAvg());
		SmartDashboard.putNumber("Left Sonar", drivetrain.getSonar(0));
		SmartDashboard.putNumber("Right Sonar", drivetrain.getSonar(1));
		SmartDashboard.putNumber("Average Sonar", drivetrain.getSonarAvg());
		SmartDashboard.putNumber("Angle", drivetrain.getAngle());

		// SmartDashboard.putNumber("IMU Temp",
		// drivetrain.getIMU().getTemperature());
		// SmartDashboard.putNumber("IMU Pres",
		// drivetrain.getIMU().getBarometricPressure());
		// SmartDashboard.putNumber("IMU MagX", drivetrain.getIMU().getMagX());
		// SmartDashboard.putNumber("IMU MagY", drivetrain.getIMU().getMagY());
		// SmartDashboard.putNumber("IMU MagZ", drivetrain.getIMU().getMagZ());

		// Manipulator
		SmartDashboard.putNumber("Manip Motors", manipulator.getMotors());
		SmartDashboard.putNumber("Servo Release", manipulator.getServo());
		SmartDashboard.putBoolean("Cube In", manipulator.cubeIn());
		SmartDashboard.putNumber("CubeX", vision.getCubeX());
		SmartDashboard.putNumber("CubeY", vision.getCubeY());

		// Lift
		SmartDashboard.putNumber("Lift Motor", lift.getMotor());
		SmartDashboard.putNumber("Lift Position", lift.getPosition());
		SmartDashboard.putBoolean("Limit Floor", lift.isAtFloor());
		SmartDashboard.putBoolean("Limit Switch", lift.isAtSwitch());
		SmartDashboard.putBoolean("Limit Scale", lift.isAtScale());
	}

	public enum Objective {
		scale, scaleOnly, kswitch, switchOnly, portal
	}

	public enum Position {
		left, middle, right
	}

	public enum SpeedType {
		full, half
	}
}
