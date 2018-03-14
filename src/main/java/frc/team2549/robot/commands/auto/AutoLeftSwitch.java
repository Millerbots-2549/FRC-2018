package frc.team2549.robot.commands.auto;

import frc.team2549.robot.Robot;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutoLeftSwitch extends CommandGroup {

    public AutoLeftSwitch() {
    	//addSequential(new DriveDistance(1150, 0, .3));
    	
    	addParallel(new ManipulatorIn(1));

    	addSequential(new DriveStraight(2000, .8));

    	addParallel(new ManipulatorIn(.2));
    	addSequential(new TurnGyro(90, .4));

    	addParallel(new DriveStraight(400, .5));
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
