package frc.team2549.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.team2549.robot.Robot;

public abstract class MyCommand extends Command {

    public MyCommand(String name) {
        super(name);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        switch (Robot.getControllerType()) {
            case joystick:
                executeJoysticks();
                break;
            case controller:
                executeController();
                break;
        }
    }

    protected abstract void executeJoysticks();

    protected abstract void executeController();
}
