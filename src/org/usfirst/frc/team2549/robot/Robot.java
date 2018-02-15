
package org.usfirst.frc.team2549.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.networktables.NetworkTable;

import edu.wpi.first.wpilibj.DigitalInput;

import org.usfirst.frc.team2549.robot.commands.DriveCommand;
import org.usfirst.frc.team2549.robot.commands.ManipulatorCommand;
import org.usfirst.frc.team2549.robot.commands.LiftCommand;

import org.usfirst.frc.team2549.robot.subsystems.DrivetrainSubsystem;
import org.usfirst.frc.team2549.robot.subsystems.ManipulatorSubsystem;
import org.usfirst.frc.team2549.robot.subsystems.LiftSubsystem;

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
	public static OI oi;
	
	//public DigitalInput limit;

	Command autonomousCommand;
	SendableChooser<Command> autoChooser = new SendableChooser<>();
	SendableChooser<Command> ctrlChooser = new SendableChooser<>();
	SendableChooser<Command> speedChooser = new SendableChooser<>();

	public enum ctrlTypes { kController, kJoysticks };
	public static ctrlTypes ctrlType = ctrlTypes.kController;

	private DriveCommand useJoysticks = new DriveCommand();
	private ManipulatorCommand useController = new ManipulatorCommand();
	
	private DriveCommand fullSpeed = new DriveCommand();
	private ManipulatorCommand halfSpeed = new ManipulatorCommand();
	
	public NetworkTable table;
	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		oi = new OI();
		//autoChooser.addDefault("Default Auto", new ExampleCommand());
		// chooser.addObject("My Auto", new MyAutoCommand());
		//limit = new DigitalInput(1);

		// could be problems with these random commands
		ctrlChooser.addDefault("Joysticks", useJoysticks);
		ctrlChooser.addObject("Controller", useController);
		
		speedChooser.addDefault("Full Speed", fullSpeed);
		speedChooser.addObject("Half Speed", halfSpeed);
		
		SmartDashboard.putData("Auto mode", autoChooser);
		SmartDashboard.putData("Controller type", ctrlChooser);
		SmartDashboard.putData("Speed", speedChooser);
		
		table = NetworkTable.getTable("SmartDashboard");
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
	 *
	 * You can add additional auto modes by adding additional commands to the
	 * chooser code above (like the commented example) or additional comparisons
	 * to the switch structure below with additional strings & commands.
	 */
	@Override
	public void autonomousInit() {
		autonomousCommand = autoChooser.getSelected();

		/*
		 * String autoSelected = SmartDashboard.getString("Auto Selector",
		 * "Default"); switch(autoSelected) { case "My Auto": autonomousCommand
		 * = new MyAutoCommand(); break; case "Default Auto": default:
		 * autonomousCommand = new ExampleCommand(); break; }
		 */

		// schedule the autonomous command (example)
		if (autonomousCommand != null)
			autonomousCommand.start();
	}

	/**
	 * This function is called periodically during autonomous
	 */
	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void teleopInit() {
		if(ctrlChooser.getSelected() == useJoysticks) {
			ctrlType = ctrlTypes.kJoysticks;
			System.out.println("Using joysticks");
		}
		else if(ctrlChooser.getSelected() == useController) {
			ctrlType = ctrlTypes.kController;
			System.out.println("Using controller");
		}
		else System.out.println("Controller type not specified");

		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to
		// continue until interrupted by another command, remove
		// this line or comment it out.
		if (autonomousCommand != null)
			autonomousCommand.cancel();

		updateDashboard();
	}

	/**
	 * This function is called periodically during operator control
	 */
	@Override
	public void teleopPeriodic() {
		if(speedChooser.getSelected() == fullSpeed) {
			drivetrain.setSpeed(1);
		}
		else if(speedChooser.getSelected() == halfSpeed) {
			drivetrain.setSpeed(.5);
		}
		
		Scheduler.getInstance().run();
		updateDashboard();
	}

	/**
	 * This function is called periodically during test mode
	 */
	@Override
	public void testPeriodic() {
		LiveWindow.run();
	}

	private void updateDashboard() {
		// Drivetrain
		SmartDashboard.putNumber("Left Motors", drivetrain.getMotor(0));
    	SmartDashboard.putNumber("Right Motors", drivetrain.getMotor(1));
    	SmartDashboard.putNumber("Left Encoder", drivetrain.getEncoder(0));
    	SmartDashboard.putNumber("Right Encoder", drivetrain.getEncoder(1));
    	
    	SmartDashboard.putNumber("IMU Temp", drivetrain.getIMU().getTemperature());
    	SmartDashboard.putNumber("IMU Pres", drivetrain.getIMU().getBarometricPressure());
    	SmartDashboard.putNumber("IMU MagX", drivetrain.getIMU().getMagX());
    	SmartDashboard.putNumber("IMU MagY", drivetrain.getIMU().getMagY());
    	SmartDashboard.putNumber("IMU MagZ", drivetrain.getIMU().getMagZ());
    	
    	// Manipulator
    	SmartDashboard.putNumber("Manip Motors", manipulator.getMotors());
    	SmartDashboard.putNumber("Servo Release", manipulator.getServo());
    	
    	// Lift
    	SmartDashboard.putNumber("Lift Motor", lift.getMotor());
    	//SmartDashboard.putNumber("Hal says:", lift.hal.getAverageVoltage());
    	//SmartDashboard.putNumber("Hal sayss:", lift.hal.getVoltage());
    	//SmartDashboard.putNumber("Hal saysss:", lift.hal.getValue());
    	//SmartDashboard.putBoolean("This means", lift.getHal());
    	//SmartDashboard.putBoolean("limit", limit.get());
    	SmartDashboard.putBoolean("sensing_cube", table.getBoolean("sensing_cube", false));
    	//SmartDashboard.putNumber("sonaric", drivetrain.getSonar().getRaw());//drivetrain.getSonar().getRaw());
	}
}
