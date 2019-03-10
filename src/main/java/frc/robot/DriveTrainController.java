package frc.robot;

import com.ctre.phoenix.motorcontrol.NeutralMode;

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

    // joystick drive or no?
    boolean joyDrive = true;

    // brake mode
    boolean brakeMode = true;

    public DriveTrainController() {
        // send values to dashboard
        SmartDashboard.putNumber("insanityFactor", insanityFactor);
        SmartDashboard.putBoolean("reverseDrive", reverseDrive);
        SmartDashboard.putBoolean("joystickDrive", joyDrive);
        SmartDashboard.putBoolean("brakeMode", brakeMode);
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

        joyDrive = SmartDashboard.getBoolean("joyDrive", joyDrive);

        brakeMode = SmartDashboard.getBoolean("brakeMode", brakeMode);

        if (SmartDashboard.getBoolean("Joystick Control", true)) {
            if (joyDrive) {
                if (reverseDrive) {
                    // reverseDrive switch
                    robotDrive.arcadeDrive(insanityFactor * properties.joystick.getJoystickY(), insanityFactor * properties.joystick.getJoystickZ(), false);
                } else {
                    // normal driving
                    robotDrive.arcadeDrive(-insanityFactor * properties.joystick.getJoystickY(), insanityFactor * properties.joystick.getJoystickZ(), false);
                }
            }

            if (brakeMode) {
                properties.leftMotor1.setNeutralMode(NeutralMode.Brake);
                properties.leftMotor2.setNeutralMode(NeutralMode.Brake);
                properties.rightMotor1.setNeutralMode(NeutralMode.Brake);
                properties.rightMotor2.setNeutralMode(NeutralMode.Brake);
            } else {
                properties.leftMotor1.setNeutralMode(NeutralMode.Coast);
                properties.leftMotor2.setNeutralMode(NeutralMode.Coast);
                properties.rightMotor1.setNeutralMode(NeutralMode.Coast);
                properties.rightMotor2.setNeutralMode(NeutralMode.Coast);
            }
        }

        return true;
    }

}