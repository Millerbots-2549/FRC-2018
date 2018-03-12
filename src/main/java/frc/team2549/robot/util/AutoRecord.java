package frc.team2549.robot.util;

import java.io.FileWriter;
import java.io.IOException;

import frc.team2549.robot.Robot;

public class AutoRecord {
	
	FileWriter writer;
	long startTime;
	
	public AutoRecord() throws IOException {
		startTime = System.currentTimeMillis();
		writer = new FileWriter("/home/lvuser/auto/test.csv");
	}
	
	public void record() throws IOException {
		if(writer != null) {
			// Time
			writer.append("" + (System.currentTimeMillis()-startTime));
			
			writer.append("," + Robot.drivetrain.getMotor(0));
			writer.append("," + Robot.drivetrain.getMotor(1));
			
			// Delimiter / end of this line
			writer.append("\n");
		}
	}
	
	public void end() throws IOException {
		if(writer != null) {
			writer.flush();
			writer.close();
		}
	}
}
