package frc.team2549.robot.commands.auto;

import frc.team2549.robot.Robot;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutoRightSwitch extends CommandGroup {

    public AutoRightSwitch() {
    	
    	addParallel(new ManipulatorIn(1));

    	addSequential(new DriveStraight(2150, .8));
    	
    	addParallel(new ManipulatorIn(.3));
    	addSequential(new TurnGyro(-90, .5));
    	addParallel(new LiftToSwitch());
    	addSequential(new DriveStraight(500, .5));
    	
    	addSequential(new ManipulatorOut(.5));
    	
    	//addSequential(new DriveDistance(1150, 0, .3));

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
