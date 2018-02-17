package frc.team2549.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
    ///////////////////////////////////////////////////////////////////////////
    // Joy Sticks
    ///////////////////////////////////////////////////////////////////////////
    private int portJoyL = 0;
    private int portJoyR = 1;

    private Joystick joyL = new Joystick(portJoyL);
    private Joystick joyR = new Joystick(portJoyR);

    // Axes
    private int joyDriveAxis = 1;

    // Buttons
    private int joyLiftFloorN = 2;
    private int joyLiftSwitchN = 5;
    private int joyLiftScaleN = 3;
    private int joyLiftUpN = 3;
    private int joyLiftDownN = 2;
    private int joyManipulatorInN = 1;
    private int joyManipulatorOutN = 1;
    private int joyServoDownN = 4;
    private int joyServoUpN = 5;

    private Button joyLiftFloor = new JoystickButton(joyL, joyLiftFloorN);
    private Button joyLiftSwitch = new JoystickButton(joyL, joyLiftSwitchN);
    private Button joyLiftScale = new JoystickButton(joyL, joyLiftScaleN);
    private Button joyLiftUp = new JoystickButton(joyR, joyLiftUpN);
    private Button joyLiftDown = new JoystickButton(joyR, joyLiftDownN);
    private Button joyManipulatorIn = new JoystickButton(joyL, joyManipulatorInN);
    private Button joyManipulatorOut = new JoystickButton(joyR, joyManipulatorOutN);
    private Button joyServoDown = new JoystickButton(joyR, joyServoDownN);
    private Button joyServoUp = new JoystickButton(joyR, joyServoUpN);

    ///////////////////////////////////////////////////////////////////////////
    // CONTROLLER
    ///////////////////////////////////////////////////////////////////////////
    private int portCtrl = 2;
    private Joystick ctrl = new Joystick(portCtrl);

    // Axes
    private int ctrlDriveL = 5;
    private int ctrlDriveR = 1;
    private int ctrlBoxIn = 2;
    private int ctrlBoxOut = 3;

    // Buttons
    private int ctrlLiftFloor = 180;
    private int ctrlLiftSwitch = 90;
    private int ctrlLiftScale = 0;
    private int ctrlLiftUp = 0;
    private int ctrlLiftDown = 180;

    private int ctrlServoUpN = 4;
    private Button ctrlServoUp = new JoystickButton(ctrl, ctrlServoUpN);
    private int ctrlServoDownN = 2;
    private Button ctrlServoDown = new JoystickButton(ctrl, ctrlServoDownN);
    private int ctrlManipulatorInN = 5; // FIND OUT
    private Button ctrlManipulatorIn = new JoystickButton(ctrl, ctrlManipulatorInN);
    private int ctrlManipulatorOutN = 6; // FIND OUT
    private Button ctrlManipulatorOut = new JoystickButton(ctrl, ctrlManipulatorOutN);
    
    ///////////////////////////////////////////////////////////////////////////
    // GETTERS
    ///////////////////////////////////////////////////////////////////////////
    
    public enum ControllerType {controller, joystick}
    private ControllerType ctrlType = ControllerType.joystick;
    
    public void setCtrlType(ControllerType ctrlType) {
    	this.ctrlType = ctrlType;
    }
    
    public double getDriveL() {
    	if(ctrlType == ControllerType.joystick) return joyL.getRawAxis(joyDriveAxis);
    	else if(ctrlType == ControllerType.controller) return ctrl.getRawAxis(ctrlDriveL);
    	return 0;
    }
    
    public double getDriveR() {
    	if(ctrlType == ControllerType.joystick) return joyR.getRawAxis(joyDriveAxis);
    	else if(ctrlType == ControllerType.controller) return ctrl.getRawAxis(ctrlDriveR);
    	return 0;
    }
    
    public boolean getManipulatorIn() {
    	if(ctrlType == ControllerType.joystick) return joyManipulatorIn.get();
    	else if(ctrlType == ControllerType.controller) return ctrlManipulatorIn.get();
    	return false;
    }
    
    public boolean getManipulatorOut() {
    	if(ctrlType == ControllerType.joystick) return joyManipulatorOut.get();
    	else if(ctrlType == ControllerType.controller) return ctrlManipulatorOut.get();
    	return false;
    }
    
    public boolean getLiftDown() {
    	if(ctrlType == ControllerType.joystick) return joyLiftDown.get();
    	return false;
    }
    
    public boolean getLiftUp() {
    	if(ctrlType == ControllerType.joystick) return joyLiftUp.get();
    	return false;
    }
    
    public boolean getLiftFloor() {
    	if(ctrlType == ControllerType.joystick) return joyLiftFloor.get();
    	else if(ctrlType == ControllerType.controller) return ctrl.getPOV() == ctrlLiftFloor;
    	return false;
    }
    
    public boolean getLiftSwitch() {
    	if(ctrlType == ControllerType.joystick) return joyLiftSwitch.get();
    	else if(ctrlType == ControllerType.controller) return ctrl.getPOV() == ctrlLiftSwitch;
    	return false;
    }
    
    public boolean getLiftScale() {
    	if(ctrlType == ControllerType.joystick) return joyLiftScale.get();
    	else if(ctrlType == ControllerType.controller) return ctrl.getPOV() == ctrlLiftScale;
    	return false;
    }
    
    public boolean getServoDown() {
    	if(ctrlType == ControllerType.joystick) return joyServoDown.get();
    	else if(ctrlType == ControllerType.controller) return ctrlServoDown.get();
    	return false;
    }
    
    public boolean getServoUp() {
    	if(ctrlType == ControllerType.joystick) return joyServoUp.get();
    	else if(ctrlType == ControllerType.controller) return ctrlServoUp.get();
    	return false;
    }

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
