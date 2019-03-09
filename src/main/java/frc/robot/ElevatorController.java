/*First way: map 1 button to the elevator and the number of times you press the button controls the elevator
first press: elevator moves up
second press: elevator stops
third press: elevator moves down
fourth press: elevator stops

Second way: map 2 buttons to the elevator
while button 1 is pressed: elevator moves up
while button 2 is pressed: elevator moves down
release both buttons to stop elevator
elevator does nothing if both buttons are pressed at the same time

For both way, keep track of the position of the elevator, so you can tell it to stop moving if it has hit the top or the bottom.
*/
package frc.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.Encoder;

public class ElevatorController implements RobotController {
    public ElevatorController() {

    }

    @Override
    public String getName() {
        return "ElevatorController";
    }

    @Override
    public boolean performAction(RobotProperties properties) {

        boolean isDownElevatorButtonPressed;
        boolean isUpElevatorButtonPressed;
        isDownElevatorButtonPressed = properties.joystick.getButtonFour();
        isUpElevatorButtonPressed = properties.joystick.getButtonSix();
        boolean level1;
        boolean level2;
        boolean level3;
        boolean level4;
        boolean level5;
        boolean level6;
        level1 = properties.joystick.getButtonSeven();
        level2 = properties.joystick.getButtonEight();
        level3 = properties.joystick.getButtonNine();
        level4 = properties.joystick.getButtonTen();
        level5 = properties.joystick.getButtonEleven();
        level6 = properties.joystick.getButtonTwelve();

        WPI_TalonSRX m_elevator1 = properties.getElevator1();
        WPI_TalonSRX m_elevator2 = properties.getElevator2();
        m_elevator1.set(0);
        m_elevator2.set(0);
        if (isUpElevatorButtonPressed && isDownElevatorButtonPressed) {
            m_elevator1.set(0);
            m_elevator2.set(0);
        } else if (isDownElevatorButtonPressed) {
            m_elevator1.set(-10);
            m_elevator2.set(-10);
        } else if (isUpElevatorButtonPressed) {
            m_elevator1.set(10);
            m_elevator2.set(10);
        } else {
            m_elevator1.set(0);
            m_elevator2.set(0);
        }

        int count;
        double distance;
        try (Encoder elevatorEncoder = new Encoder(0, 1, false, Encoder.EncodingType.k4X)) {
            elevatorEncoder.setMaxPeriod(.1);
            elevatorEncoder.setMinRate(10);
            elevatorEncoder.setDistancePerPulse(5);
            elevatorEncoder.setReverseDirection(true);
            elevatorEncoder.setSamplesToAverage(7);

            count = elevatorEncoder.get();
            distance = elevatorEncoder.getDistance();
        }
        // TODO: need to fill out the distance thresholds replacing the 0, 5, 10, and 15
        // values with measured values
        if (level1) {
            while (distance < 5) {
                m_elevator1.set(10);
                m_elevator2.set(10);
            }
        } else if (level2 && distance < 10) {
            while (distance < 10) {
                m_elevator1.set(10);
                m_elevator2.set(10);
            }
        } else if (level3 && distance < 15) {
            while (distance < 15) {
                m_elevator1.set(10);
                m_elevator2.set(10);
            }

        } else if (level4 && distance > 10) {
            while (distance > 10) {
                m_elevator1.set(-10);
                m_elevator2.set(-10);
            }
        } else if (level5 && distance > 5) {
            while (distance > 5) {
                m_elevator1.set(-10);
                m_elevator2.set(-10);
            }
        } else if (level6) {
            while (distance > 0) {
                m_elevator1.set(-10);
                m_elevator2.set(-10);
            }
        } else {
            m_elevator1.set(0);
            m_elevator2.set(0);
        }
        return true;

    }
}