package frc.team2549.robot.commands.auto;

import frc.team2549.robot.Robot;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutoMidRightSwitch extends CommandGroup {

    public AutoMidRightSwitch() {
    	//addSequential(new DriveDistance(1150, 0, .3));
    	
    	addSequential(new DriveStraight(1250, .75));

    	addSequential(new LiftToSwitch());
    	addSequential(new DriveStraight(500, .5));
    	addSequential(new ManipulatorOut(.3));

    	// 2 cube
    	
//    	addParallel(new LiftToFloor());
//    	addSequential(new DriveDistance(1000, -.75, -.75));
//    	
//    	addSequential(new TurnGyro(-45, .5));
//    	addParallel(new ManipulatorIn(3));
//    	addSequential(new DriveStraight(750, .75));
//    	
//    	addSequential(new DriveDistance(750, -.75, -.75));
//    	addSequential(new TurnGyro(45, .5));
//    	
//    	addSequential(new DriveStraight(1000, .75));
//    	addSequential(new LiftToSwitch());
//    	addSequential(new ManipulatorOut(.3));
//    	addParallel(new ManipulatorIn(1));
//    	
//    	addSequential(new DriveStraight(400, .6));
//    	
//    	addSequential(new TurnGyro(20, .5));
//    	
//    	addSequential(new DriveStraight(1000, .6));
//    	
//    	addParallel(new ManipulatorIn(1));
//    	addSequential(new TurnGyro(-20, .5));
//    	
//    	addParallel(new DriveStraight(100, .5));
//    	addSequential(new LiftToSwitch());
//    	
//    	addSequential(new ManipulatorOut(.5));

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
