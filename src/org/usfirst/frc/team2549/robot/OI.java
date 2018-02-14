package org.usfirst.frc.team2549.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	public int portJoyL = 0;
	public int portJoyR = 1;
	public int portCtrl = 2;

	public Joystick joyL = new Joystick(portJoyL);
	public Joystick joyR = new Joystick(portJoyR);
	public Joystick ctrl = new Joystick(portCtrl);

	// Axes
	public int joyDriveAxis = 1;
	
	// Buttons
	public int joyLiftUpN = 3;
	public int joyLiftDownN = 2;
	public int joyManipulatorInN = 1;
	public int joyManipulatorOutN = 1;
	
	public Button joyLiftUp = new JoystickButton(joyR, joyLiftUpN);
	public Button joyLiftDown = new JoystickButton(joyR, joyLiftDownN);
	public Button joyManipulatorIn = new JoystickButton(joyL, joyManipulatorInN);
	public Button joyManipulatorOut = new JoystickButton(joyR, joyManipulatorOutN);

	// Axes
	public int ctrlDriveL = 5;
	public int ctrlDriveR = 1;
	public int ctrlBoxIn = 2;
	public int ctrlBoxOut = 3;
	
	// Buttons
	public int ctrlLiftUp = 0;
	public int ctrlLiftDown = 180;
	
	private int ctrlServoUpN = 4;
	private int ctrlServoDownN = 2;
	private int ctrlManipulatorInN = 5; // FIND OUT
	private int ctrlManipulatorOutN = 6; // FIND OUT
	
	public Button ctrlServoUp = new JoystickButton(ctrl, ctrlServoUpN);
	public Button ctrlServoDown = new JoystickButton(ctrl, ctrlServoDownN);
	public Button ctrlManipulatorIn = new JoystickButton(ctrl, ctrlManipulatorInN);
	public Button ctrlManipulatorOut = new JoystickButton(ctrl, ctrlManipulatorOutN);

	//// CREATING BUTTONS
	// One type of button is a joystick button which is any button on a
	//// joystick.
	// You create one by telling it which joystick it's on and which button
	// number it is.
	// Joystick stick = new Joystick(port);
	// Button button = new JoystickButton(stick, buttonNumber);

	// There are a few additional built in buttons you can use. Additionally,
	// by subclassing Button you can create custom triggers and bind those to
	// commands the same as any other Button.

	//// TRIGGERING COMMANDS WITH BUTTONS
	// Once you have a button, it's trivial to bind it to a button in one of
	// three ways:

	// Start the command when the button is pressed and let it run the command
	// until it is finished as determined by it's isFinished method.
	// button.whenPressed(new ExampleCommand());

	// Run the command while the button is being held down and interrupt it once
	// the button is released.
	// button.whileHeld(new ExampleCommand());

	// Start the command when the button is released and let it run the command
	// until it is finished as determined by it's isFinished method.
	// button.whenReleased(new ExampleCommand());
}
