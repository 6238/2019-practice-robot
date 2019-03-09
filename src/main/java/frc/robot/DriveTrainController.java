package frc.robot;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Created by imadan on 3/9/19
 */
public class DriveTrainController implements RobotController {

    // speed multiplier/reducer
    double insanityFactor = 0.5;

    // inverted drive for hatch panel
    boolean reverseDrive = false;

    public DriveTrainController() {
        // send values to dashboard
        SmartDashboard.putNumber("insanityFactor", insanityFactor);
        SmartDashboard.putBoolean("reverseDrive", reverseDrive);
    }

    // name function for initial testing
    @Override
    public String getName() {
        return "DriveTrainController";
    }

    @Override
    public boolean performAction(RobotProperties properties) {

        DifferentialDrive robotDrive = properties.getRobotDrive();

        insanityFactor = SmartDashboard.getNumber("insanityFactor", insanityFactor);

        reverseDrive = SmartDashboard.getBoolean("reverseDrive", reverseDrive);

        if (SmartDashboard.getBoolean("Joystick Control", true)) {
            if (reverseDrive) {
                // reverseDrive switch
                robotDrive.arcadeDrive(-insanityFactor * properties.joystick.getJoystickY(),
                        insanityFactor * properties.joystick.getJoystickZ());
            } else {
                // normal driving
                robotDrive.arcadeDrive(insanityFactor * properties.joystick.getJoystickY(),
                        insanityFactor * properties.joystick.getJoystickZ());
            }
        }

        return true;
    }

}