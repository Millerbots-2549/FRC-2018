package frc.team2549.robot.commands.auto;

import frc.team2549.robot.Robot;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutoRightScale extends CommandGroup {

    public AutoRightScale() {
    	//addSequential(new DriveDistance(1150, 0, .3));

    	addParallel(new ManipulatorIn(1));

    	addSequential(new DriveStraight(3500, .8));

    	addParallel(new ManipulatorIn(.2));
    	addSequential(new TurnGyro(-20, .5));

    	addParallel(new DriveStraight(750, .5));
    	addSequential(new LiftToScale());
    	addSequential(new DriveStraight(300,.4));
    	
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
