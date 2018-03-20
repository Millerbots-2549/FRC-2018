package frc.team2549.robot.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import frc.team2549.robot.Robot;

public class AutoPlay {

	Scanner scanner;
	long startTime;

	boolean onTime = true;
	double nextDouble;

	public AutoPlay() throws FileNotFoundException {
		scanner = new Scanner(new File("/home/lvuser/auto/test.csv"));
		scanner.useDelimiter(",|\\n");
		startTime = System.currentTimeMillis();
	}

	public void play() {
		if((scanner != null) && (scanner.hasNextDouble())) {
			double t_delta;
			if(onTime) {
				nextDouble = scanner.nextDouble();
			}
			t_delta = nextDouble - (System.currentTimeMillis()-startTime);
			if(t_delta <= 0) {
				Robot.drivetrain.driveTank(scanner.nextDouble(),
						scanner.nextDouble());
				onTime = true;
			}
		} else {
			onTime = false;
		}
	}
}
