package frc.team2549.robot.subsystems;

import frc.team2549.robot.Robot;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class CameraSubsystem extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

	private NetworkTable table;
	private NetworkTableEntry xEntry;
	private NetworkTableEntry yEntry;
	private NetworkTableEntry sensingCubeEntry;
	private int cube_x;
	private int cube_y;
	private boolean sensing_cube;

    public CameraSubsystem() {
        super(CameraSubsystem.class.getSimpleName());
        
        CameraServer.getInstance().startAutomaticCapture();
        
        table = NetworkTableInstance.getDefault().getTable("SmartDashboard");
        sensingCubeEntry = table.getEntry("sensing_cube");
    	xEntry = table.getEntry("cube_x");
    	yEntry = table.getEntry("cube_y");
    }

    public void initDefaultCommand() {
    }

    public int getCubeX() {
    	xEntry.setNumber(cube_x);
    	return cube_x;
    }

    public int getCubeY() {
    	yEntry.setNumber(cube_y);
    	return cube_y;
    }

    public boolean sensingCube() {
    	sensingCubeEntry.setBoolean(sensing_cube);
    	SmartDashboard.putBoolean("cube_sensedd", sensing_cube);
    	return sensing_cube;
    }
}
