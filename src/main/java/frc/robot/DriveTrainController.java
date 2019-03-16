 package frc.robot;

import java.util.Properties;

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

    // absolute drive
    boolean absoluteDrive = false;

    // self align
    boolean selfAlign = false;

    private static final double kAngleSetpoint = 0.0;
    private double kP = -0.1;

    public DriveTrainController() {
        // send values to dashboard
        SmartDashboard.putNumber("insanityFactor", insanityFactor);
        SmartDashboard.putBoolean("reverseDrive", reverseDrive);
        SmartDashboard.putBoolean("joyDrive", joyDrive);
        SmartDashboard.putBoolean("brakeMode", brakeMode);
        SmartDashboard.putBoolean("absoluteDrive", absoluteDrive);
        SmartDashboard.putBoolean("selfAlign", selfAlign);
    }

    // name function for initial testing
    @Override
    public String getName() {
        return "DriveTrainController";
    }

    @Override
    public boolean performAction(RobotProperties properties) {
        DifferentialDrive robotDrive = properties.getRobotDrive();

        selfAlign = SmartDashboard.getBoolean("selfAlign", selfAlign);

        insanityFactor = SmartDashboard.getNumber("insanityFactor", insanityFactor);
        
        reverseDrive = SmartDashboard.getBoolean("reverseDrive", reverseDrive);

        joyDrive = SmartDashboard.getBoolean("joyDrive", joyDrive);

        brakeMode = SmartDashboard.getBoolean("brakeMode", brakeMode);

        absoluteDrive = SmartDashboard.getBoolean("absoluteDrive", absoluteDrive);

        double turningValue = (kAngleSetpoint - properties.gyro.getAngle()) * kP;

        if (turningValue > 0.5) {
            turningValue = 0.5;
        } else if (turningValue < -0.5) {
            turningValue = -0.5;
        }

        if (selfAlign) {
            
        } else if (joyDrive) {
            if (reverseDrive) {
                // reverseDrive switch
                robotDrive.arcadeDrive(insanityFactor * properties.joystick.getJoystickY(), insanityFactor * properties.joystick.getJoystickZ(), false);
            } else if (absoluteDrive) {
                // absoluteDrive switch
                robotDrive.arcadeDrive(-insanityFactor * properties.joystick.getJoystickY(), turningValue, false);
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

        return true;
    }

}