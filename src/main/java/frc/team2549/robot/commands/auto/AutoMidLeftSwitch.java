package frc.team2549.robot.commands.auto;

import frc.team2549.robot.Robot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutoMidLeftSwitch extends CommandGroup {

    public AutoMidLeftSwitch() {
   
    	Robot.manipulator.servoRelease(true);
    	
    	addParallel(new ManipulatorIn(1));
    	
    	addSequential(new DriveDistance(400, .6));
    	addSequential(new TurnGyro(-50, .5));
    	addSequential(new DriveDistance(1400, .6));
    	addParallel(new ManipulatorIn(1));
    	addSequential(new TurnGyro(45, .5));
    	//addParallel(new DriveDistance(100, .5));
    	addSequential(new LiftToSwitch());
    	addSequential(new ManipulatorOut(.5));

        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.

        // To run multiple commands at the same time,
        // use addParallel()
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
        // Command1 and Command2 will run in parallel.

        // A command group will require all of the subsystems that each member
        // would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.
    }
}
