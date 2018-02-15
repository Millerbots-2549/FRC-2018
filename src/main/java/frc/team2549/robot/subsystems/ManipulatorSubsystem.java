package frc.team2549.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.team2549.robot.RobotMap;
import frc.team2549.robot.commands.ManipulatorCommand;

/**
 *
 */
public class ManipulatorSubsystem extends Subsystem {

    private static final double SPEED = 1.0;

    private Talon motor;
    private DigitalInput boxIn;

    //TODO this servo should be a separate subsystem with its own commands for controlling it
    private Servo servo;
    private double servoDownPos, servoUpPos;

    public ManipulatorSubsystem() {
        super(ManipulatorSubsystem.class.getSimpleName());
        motor = new Talon(RobotMap.manipulatorMotors);
        servo = new Servo(RobotMap.releaseServo);
        servoDownPos = 1;
        servoUpPos = 0; // change these
    }

    public void initDefaultCommand() {
        setDefaultCommand(new ManipulatorCommand());
    }

    public void servoRelease(boolean set) {
        if (set)
            servo.set(servoDownPos);
        else if (!set)
            servo.set(servoUpPos);
    }

    public void takeIn() {
        motor.set(SPEED);
    }

    public void pushOut() {
        motor.set(-SPEED);
    }

    public double getServo() {
        return servo.getPosition();
    }

    public boolean cubeIn() {
        if (boxIn.get())
            return true;
        else return false;
    }
}

