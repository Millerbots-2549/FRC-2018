package frc.team2549.robot;

import java.io.IOException;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import frc.team2549.robot.util.AutoRecord;
import frc.team2549.robot.util.AutoPlay;

import frc.team2549.robot.subsystems.DrivetrainSubsystem;
import frc.team2549.robot.subsystems.LiftSubsystem;
import frc.team2549.robot.subsystems.ManipulatorSubsystem;
import frc.team2549.robot.subsystems.CameraSubsystem;

import frc.team2549.robot.commands.auto.AutoRightScale;
import frc.team2549.robot.commands.auto.AutoRightLeftScale;
import frc.team2549.robot.commands.auto.AutoRightSwitch;

import frc.team2549.robot.commands.auto.AutoMidLeftSwitch;
import frc.team2549.robot.commands.auto.AutoMidRightSwitch;

import frc.team2549.robot.commands.auto.AutoLeftScale;
import frc.team2549.robot.commands.auto.AutoLeftRightScale;
import frc.team2549.robot.commands.auto.AutoLeftSwitch;

import frc.team2549.robot.commands.auto.AutoTest;

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
    
    private static AutoRecord recorder;
    private static AutoPlay player;
    private static boolean isRecording;

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
        objChooser.addObject("Portal", Objective.portal);
        
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

        //SmartDashboard.putData("Auto mode", autoChooser);
        //SmartDashboard.putData("Controller type", ctrlChooser);
        SmartDashboard.putData("Speed", speedChooser);
        SmartDashboard.putData("Position", pstnChooser);
        SmartDashboard.putData("Objective", objChooser);

        //table = NetworkTableInstance.getTable("SmartDashboard");
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
        autonomousCommand = autoChooser.getSelected();

        /*
         * String autoSelected = SmartDashboard.getString("Auto Selector",
         * "Default"); switch(autoSelected) { case "My Auto": autonomousCommand
         * = new MyAutoCommand(); break; case "Default Auto": default:
         * autonomousCommand = new ExampleCommand(); break; }
         */
        
        drivetrain.resetSensors();
        
        String gameData = DriverStation.getInstance().getGameSpecificMessage();
        if(gameData.length() > 0) {
        	final Command autonomousCommand;

        	// MIDDLE POSITION, LEFT SWITCH
        	if(gameData.charAt(0) == 'L'&&
        			pstnChooser.getSelected() == Position.middle) {
        		System.out.println("Auto Middle Left Switch");
        		autonomousCommand = new AutoMidLeftSwitch();
        		}
       
        	// MIDDLE POSITION, RIGHT SWITCH
        	else if(gameData.charAt(0) == 'R' &&
        			pstnChooser.getSelected() == Position.middle) {
        		System.out.println("Auto Middle Right Switch");
        		autonomousCommand = new AutoMidRightSwitch();
        	}
        	// #####################################################
        	// RIGHT POSITION, RIGHT SWITCH
        	else if(gameData.charAt(0) == 'R' &&
        			pstnChooser.getSelected() == Position.right &&
        			objChooser.getSelected() == Objective.kswitch) {
        		System.out.println("Auto Right Right Switch");
        		autonomousCommand = new AutoRightSwitch();
        	}
        	// RIGHT POSITION, RIGHT SCALE
        	else if(gameData.charAt(1) == 'R' &&
        			pstnChooser.getSelected() == Position.right &&
        			objChooser.getSelected() == Objective.scale) {
        		System.out.println("Auto Right Scale");
        		autonomousCommand = new AutoRightScale();
        		
        	}
        	else if(gameData.charAt(1) == 'L' &&
        			pstnChooser.getSelected() == Position.right &&
        			objChooser.getSelected() == Objective.scale &&
        			gameData.charAt(0) == 'R') {
        		System.out.println("Auto Right Right Switch");
        		autonomousCommand = new AutoRightSwitch();
        	}
        	else if(gameData.charAt(1) == 'L' &&
        			pstnChooser.getSelected() == Position.right &&
        			objChooser.getSelected() == Objective.scale &&
        			gameData.charAt(0) == 'L') {
        		System.out.println("Auto Right Left Scale");
        		autonomousCommand = new AutoRightLeftScale();
        	}
        	// #####################################################
        	// LEFT POSITION, LEFT SWITCH
        	else if(gameData.charAt(0) == 'L' &&
        			pstnChooser.getSelected() == Position.left &&
        			objChooser.getSelected() == Objective.kswitch) {
        		System.out.println("Auto Left Switch");
        		autonomousCommand = new AutoLeftSwitch();
        	}
        	// LEFT POSITION, LEFT SCALE
        	else if(gameData.charAt(1) == 'L' &&
        			pstnChooser.getSelected() == Position.left &&
        			objChooser.getSelected() == Objective.scale) {
        		System.out.println("Auto Left Scale");
        		autonomousCommand = new AutoLeftScale();
        	}
        	else if(gameData.charAt(1) == 'R' &&
        			pstnChooser.getSelected() == Position.left &&
        			objChooser.getSelected() == Objective.scale && gameData.charAt(0) == 'L') {
        		System.out.println("Auto Left Switch");
        		autonomousCommand = new AutoLeftSwitch();
        	}
        	else if(gameData.charAt(1) == 'R' &&
        			pstnChooser.getSelected() == Position.left &&
        			objChooser.getSelected() == Objective.scale && gameData.charAt(0) == 'R') {
        		System.out.println("Auto Left Right Scale");
        		autonomousCommand = new AutoLeftRightScale();
        	}
        	// #####################################################
        	// TEST
        	else if(gameData.charAt(0) == 'T') {
        		System.out.println("Auto Test");
        		autonomousCommand = new AutoTest();
        	} else if(gameData.charAt(0) == 'N'){
        		System.out.println("No Auto");
        		autonomousCommand = null;
        	} else {
        		System.out.println("Null Auto");
        		autonomousCommand = null;
        	}
        	this.autonomousCommand = autonomousCommand;
        }

        manipulator.servoRelease(true);
    	drivetrain.resetSensors();

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
        updateDashboard();
    }

    @Override
    public void teleopInit() {
        // This makes sure that the autonomous stops running when
        // teleop starts running. If you want the autonomous to
        // continue until interrupted by another command, remove
        // this line or comment it out.
        if (autonomousCommand != null)
            autonomousCommand.cancel();

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
                drivetrain.setSpeed(.6);
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

//        SmartDashboard.putNumber("IMU Temp", drivetrain.getIMU().getTemperature());
//        SmartDashboard.putNumber("IMU Pres", drivetrain.getIMU().getBarometricPressure());
//        SmartDashboard.putNumber("IMU MagX", drivetrain.getIMU().getMagX());
//        SmartDashboard.putNumber("IMU MagY", drivetrain.getIMU().getMagY());
//        SmartDashboard.putNumber("IMU MagZ", drivetrain.getIMU().getMagZ());

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

    public enum Objective { scale, kswitch, portal }
    public enum Position { left, middle, right }
    public enum SpeedType { full, half }
}
