package frc.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class RobotProperties {
    JoystickController joystick;

    public WPI_TalonSRX leftMotor1;
    public WPI_TalonSRX leftMotor2;
    public WPI_TalonSRX rightMotor1;
    public WPI_TalonSRX rightMotor2;

    private SpeedControllerGroup leftDrive;
    private SpeedControllerGroup rightDrive;

    private DifferentialDrive robotDrive;

    private WPI_TalonSRX elevator1;
    private WPI_TalonSRX elevator2;

    private WPI_TalonSRX mechanism;
    private WPI_TalonSRX motor8;

    public ADXRS450_Gyro gyro;

    private PowerDistributionPanel pdp;

    public RobotProperties() {
        joystick = new JoystickController(0);

        leftMotor1 = new WPI_TalonSRX(7);
        leftMotor2 = new WPI_TalonSRX(8);
        rightMotor1 = new WPI_TalonSRX(3);
        rightMotor2 = new WPI_TalonSRX(4);

        leftMotor1.setInverted(false);
        leftMotor2.setInverted(false);
        rightMotor1.setInverted(false);
        rightMotor2.setInverted(false);

        leftDrive = new SpeedControllerGroup(leftMotor1, leftMotor2);
        rightDrive = new SpeedControllerGroup(rightMotor1, rightMotor2);

        robotDrive = new DifferentialDrive(leftDrive, rightDrive);

        elevator1 = new WPI_TalonSRX(1);
        elevator2 = new WPI_TalonSRX(2);

        mechanism = new WPI_TalonSRX(5);
        motor8 = new WPI_TalonSRX(6);

        gyro = new ADXRS450_Gyro();

        pdp = new PowerDistributionPanel();

    }

    public DifferentialDrive getRobotDrive() {
        return robotDrive;
    }

    public WPI_TalonSRX getElevator1() {
        return elevator1;
    }

    public void setElevator1(WPI_TalonSRX elevator1) {
        this.elevator1 = elevator1;
    }

    public WPI_TalonSRX getElevator2() {
        return elevator2;
    }

    public void setElevator2(WPI_TalonSRX elevator2) {
        this.elevator2 = elevator2;
    }

    public WPI_TalonSRX getMechanism() {
        return mechanism;
    }

    public void setMechanism(WPI_TalonSRX mechanism) {
        this.mechanism = mechanism;
    }

    public WPI_TalonSRX getMotor8() {
        return motor8;
    }

    public void setMotor8(WPI_TalonSRX motor8) {
        this.motor8 = motor8;
    }


    public void pushData(RobotProperties properties) {
        SmartDashboard.putNumber("Joystick X", properties.joystick.getJoystickX());
        SmartDashboard.putNumber("Joystick Y", properties.joystick.getJoystickY());
        SmartDashboard.putNumber("Joystick Z", properties.joystick.getJoystickZ());

        SmartDashboard.putBoolean("Trigger", properties.joystick.getButtonOne());
        SmartDashboard.putBoolean("SideButton", properties.joystick.getButtonTwo());
        SmartDashboard.putBoolean("ThumbDownLeft", properties.joystick.getButtonThree());
        SmartDashboard.putBoolean("ThumbDownRight", properties.joystick.getButtonFour());
        SmartDashboard.putBoolean("ThumbUpLeft", properties.joystick.getButtonFive());
        SmartDashboard.putBoolean("ThumbUpRight", properties.joystick.getButtonSix());
        SmartDashboard.putBoolean("BaseBackLeft", properties.joystick.getButtonSeven());
        SmartDashboard.putBoolean("BaseBackRight", properties.joystick.getButtonEight());
        SmartDashboard.putBoolean("BaseMiddleLeft", properties.joystick.getButtonNine());
        SmartDashboard.putBoolean("BaseMiddleRight", properties.joystick.getButtonTen());
        SmartDashboard.putBoolean("BaseFrontLeft", properties.joystick.getButtonEleven());
        SmartDashboard.putBoolean("BaseFrontRight", properties.joystick.getButtonTwelve());

        SmartDashboard.putBoolean("DPadUp", properties.joystick.getDPadUp());
        SmartDashboard.putBoolean("DPadUpRight", properties.joystick.getDPadUpRight());
        SmartDashboard.putBoolean("DPadRight", properties.joystick.getDPadRight());
        SmartDashboard.putBoolean("DPadDownRight", properties.joystick.getDPadDownRight());
        SmartDashboard.putBoolean("DPadDown", properties.joystick.getDPadDown());
        SmartDashboard.putBoolean("DPadDownLeft", properties.joystick.getDPadDownLeft());
        SmartDashboard.putBoolean("DPadLeft", properties.joystick.getDPadLeft());
        SmartDashboard.putBoolean("DPadUpLeft", properties.joystick.getDPadUpLeft());

        SmartDashboard.putNumber("Shutoff", properties.joystick.getSlider());

        SmartDashboard.putData(properties.getRobotDrive());

        SmartDashboard.putData("Gyro", gyro);

        SmartDashboard.putData("talon1", elevator1);
        SmartDashboard.putData("talon2", elevator2);
        SmartDashboard.putData("talon3", rightMotor1);
        SmartDashboard.putData("talon4", rightMotor2);
        SmartDashboard.putData("talon5", mechanism);
        SmartDashboard.putData("talon6", motor8);
        SmartDashboard.putData("talon7", leftMotor1);
        SmartDashboard.putData("talon8", leftMotor2);
        
        SmartDashboard.putData("pdp", pdp);
    }
}