package frc.team2549.robot.subsystems;

import frc.team2549.robot.Robot;

import edu.wpi.first.wpilibj.networktables.NetworkTable;
//import edu.wpi.first.networktables.NetworkTable;
//import edu.wpi.first.networktables.NetworkTableEntry;
//import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class CameraSubsystem extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

//	private NetworkTable table;
//	private NetworkTableEntry xEntry;
//	private NetworkTableEntry yEntry;
//	private NetworkTableEntry sensingCubeEntry;
	private NetworkTable table;
	private int cube_x;
	private int cube_y;
	private int sensing_cube;
	public int camRes_x = 180;

    public CameraSubsystem() {
        super(CameraSubsystem.class.getSimpleName());
        
        CameraServer.getInstance().startAutomaticCapture(0);
        CameraServer.getInstance().startAutomaticCapture(1);
        
//        table = NetworkTable.getTable("SmartDashboard");
        
//        table = NetworkTableInstance.getDefault().getTable("SmartDashboard");
//        sensingCubeEntry = table.getEntry("sensing_cube");
//    	xEntry = table.getEntry("cube_x");
//    	yEntry = table.getEntry("cube_y");
    }

    public void initDefaultCommand() {
    }

    public int getCubeX() {
//    	xEntry.setNumber(cube_x);
//    	cube_x = (int) table.getNumber("cube_x", 0);
    	cube_x = (int) SmartDashboard.getNumber("cube_pos_x", camRes_x/2);
    	return cube_x;
    }

    public int getCubeY() {
//    	yEntry.setNumber(cube_y);
//    	cube_y = (int) table.getNumber("cube_y", 0);
    	cube_y = (int) SmartDashboard.getNumber("cube_pos_y", camRes_x/2);
    	return cube_y;
    }

    public int sensingCube() {
//    	sensingCubeEntry.setBoolean(sensing_cube);
//    	sensing_cube = (int) table.getNumber("sensing_cube", 0);
    	sensing_cube = (int) SmartDashboard.getNumber("sensing_cube", 0);
    	return sensing_cube;
    }
}
