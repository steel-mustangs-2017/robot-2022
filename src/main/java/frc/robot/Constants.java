// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.



package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
public final static class ShooterConstants {
    public static final int DEVICE_ID_SHOOTER_MASTER = 4;
    public static final int DEVICE_ID_SHOOTER_SLAVE = 5;
    public static final int DEVICE_ID_TURRET = 9;

    public static final double TURRET_KP = .00001;
    public static final double TURRET_KI = 0;
    public static final double TURRET_KD = 0.0000001;
}
public final static class ControllerConstants {
    public static final int PORT_ID_OPERATOR_CONTROLLER = 1;
    public static final int PORT_ID_DRIVER_CONTROLLER = 0;

    public static final int SHOOT_AXIS_1_ID = 2;
    public static final int SHOOT_AXIS_2_ID = 3;
    public static final int AIM_AXIS_ID = 0;

    public static final int FORWARD_AXIS_ID = 1;
    public static final int TURN_AXIS_ID = 5;
    public static final int TURN_MULT_AXIS_ID = 6;
    public static final int FORWARD_MULT_AXIS_ID = 2;


    public static final int INDEX_BUTTON_ID = 6;
    public static final int RUMBLE_BUTTON_ID = 2;
    
    public static final int INTAKE_BUTTON_ID = 5;

    public static final int INTAKE_DEPLOY_BUTTON_ID = 1;
    public static final int INTAKE_RETRACT_BUTTON_ID = 3;


}
public final static class IndexerConstants {
    public static final int DEVICE_ID_INDEXER_LEFT = 6;
    public static final int DEVICE_ID_INDEXER_RIGHT = 7;
    public static final int DEVICE_ID_INDEXER_TOP = 8;

}
public final static class IntakeConstants {
    public static final int DEVICE_ID_INTAKE = 10;
    public static final int DEVICE_ID_INTAKE_DEPLOY = 11;
    public static final int DEVICE_ID_INTAKE_DEPLOY_SLAVE = 12;
}
public final static class ChassisConstants {
    public static final int DEVICE_ID_DRIVE_FRONT_RIGHT = 2;
    public static final int DEVICE_ID_DRIVE_BACK_RIGHT = 3;
    public static final int DEVICE_ID_DRIVE_FRONT_LEFT = 1;
    public static final int DEVICE_ID_DRIVE_BACK_LEFT = 0;
}
public final static class LimelightConstants {
    public static final double LIMELIGHT_KP = .01;
    public static final double LIMELIGHT_KI = 0;
    public static final double LIMELIGHT_KD = 0;
}
public final static class ClimberConstants {
    public static final int DEVICE_ID_CLIMB_RIGHT = 13;
    public static final int DEVICE_ID_CLIMB_LEFT = 14;
    
}

}
