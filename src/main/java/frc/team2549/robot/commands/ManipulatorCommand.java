package frc.team2549.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import frc.team2549.robot.Robot;

/**
 *
 */
public class ManipulatorCommand extends MyCommand {

    NetworkTable table;
//    int cube_pos_x, cube_pos_y;
//    boolean cube_sensing;

    public ManipulatorCommand() {
        super(ManipulatorCommand.class.getSimpleName());
        requires(Robot.manipulator);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        Robot.manipulator.servoRelease(false);

        // TODO why are we doing this?
        table = NetworkTable.getTable("SmartDashboard");
    }

    protected void executeJoysticks() {
        if (Robot.oi.joyManipulatorIn.get())
            Robot.manipulator.takeIn();
        else if (Robot.oi.joyManipulatorOut.get())
            Robot.manipulator.pushOut();

        //Robot.oi.ctrlManipulatorIn.whenPressed(new ManipulatorIn());
    }

    protected void executeController() {

        if (Robot.oi.ctrlManipulatorIn.get())
            Robot.manipulator.takeIn();
        else if (Robot.oi.ctrlManipulatorOut.get())
            Robot.manipulator.pushOut();

        // TODO this release
        // Release Servo
//        if (Robot.oi.ctrlServoUp.get())
//            Robot.manipulator.servoRelease(true);
//        else if (Robot.oi.ctrlServoDown.get())
//            Robot.manipulator.servoRelease(false);
    }

    // https://wpilib.screenstepslive.com/s/currentCS/m/cpp/l/241909-using-limit-switches-to-control-behavior
    //
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
