package frc.team2549.robot.commands.auto;

import frc.team2549.robot.Robot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutoMidLeftSwitch extends CommandGroup {

    public AutoMidLeftSwitch() {
    	double speed = .75;
    	double turnSpeed = .6;
    	
    	addParallel(new ManipulatorIn(1));
    	
    	addSequential(new DriveStraight(400, speed));
    	addSequential(new TurnGyro(-90, turnSpeed));

    	addSequential(new DriveStraight(1800, speed));
    	addParallel(new ManipulatorIn(.3));

    	addSequential(new TurnGyro(90, turnSpeed));
    	addSequential(new ManipulatorIn(.1));

    	addSequential(new DriveStraight(1000, .5));

    	addSequential(new LiftToSwitch());
    	// TODO change to prevent tech foul
    	addParallel(new DriveStraight(200, .5));
    	addSequential(new ManipulatorOut(.5));

    	// 2 cube
    	addParallel(new DriveDistance(1500, -speed, -speed));
    	addSequential(new LiftToFloor());

    	addSequential(new TurnGyro(55, turnSpeed));
    	addParallel(new ManipulatorIn(3));
    	addSequential(new DriveStraight(1000, speed));
//    	addSequential(new PickUpCube());

    	addSequential(new DriveDistance(1000, -speed, -speed));

    	addSequential(new TurnGyro(-55, turnSpeed));
    	
    	addParallel(new DriveStraight(100, .5));
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
