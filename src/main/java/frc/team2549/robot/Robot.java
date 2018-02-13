package frc.team2549.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
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

    private static final SendableChooser<Command> autoChooser = new SendableChooser<>();
    private static final SendableChooser<ControllerType> ctrlChooser = new SendableChooser<>();

    public static OI oi;

    private Command autonomousCommand;

    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    @Override
    public void robotInit() {
        oi = new OI();

        ctrlChooser.addDefault("Joysticks", ControllerType.joystick);
        ctrlChooser.addObject("Controller", ControllerType.controller);

        SmartDashboard.putData("Auto mode", autoChooser);
        SmartDashboard.putData("Controller type", ctrlChooser);
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
        if (ctrlChooser.getSelected() == ControllerType.joystick) {
            System.out.println("Using joysticks");
        } else if (ctrlChooser.getSelected() == ControllerType.controller) {
            System.out.println("Using controller");
        } else System.out.println("Controller type not specified");

        // This makes sure that the autonomous stops running when
        // teleop starts running. If you want the autonomous to
        // continue until interrupted by another command, remove
        // this line or comment it out.
        if (autonomousCommand != null)
            autonomousCommand.cancel();
    }

    /**
     * This function is called periodically during operator control
     */
    @Override
    public void teleopPeriodic() {
        Scheduler.getInstance().run();
    }

    /**
     * This function is called periodically during test mode
     */
    @Override
    public void testPeriodic() {
    }

    public static ControllerType getControllerType() {
        return ctrlChooser.getSelected();
    }

    public enum ControllerType {controller, joystick}
}
