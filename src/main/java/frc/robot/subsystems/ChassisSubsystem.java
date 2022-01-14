// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ChassisConstants;

public class ChassisSubsystem extends SubsystemBase {
    private final WPI_TalonFX driveFrontRight = new WPI_TalonFX(ChassisConstants.DEVICE_ID_DRIVE_FRONT_RIGHT);
    private final WPI_TalonFX driveBackRight = new WPI_TalonFX(ChassisConstants.DEVICE_ID_DRIVE_BACK_RIGHT);
    private final SpeedControllerGroup driveRight = new SpeedControllerGroup(driveFrontRight, driveBackRight);

    private final WPI_TalonFX driveFrontLeft = new WPI_TalonFX(ChassisConstants.DEVICE_ID_DRIVE_FRONT_LEFT);
    private final WPI_TalonFX driveBackLeft = new WPI_TalonFX(ChassisConstants.DEVICE_ID_DRIVE_BACK_LEFT);
    private final SpeedControllerGroup driveLeft = new SpeedControllerGroup(driveFrontLeft, driveBackLeft);
  
    private final DifferentialDrive _Drive = new DifferentialDrive(driveLeft, driveRight);
    //DifferentialDrive _drive; //= new DifferentialDrive(driveFrontLeft, driveFrontRight);
    
  /** Creates a new ChassisSubsystem. */
  public ChassisSubsystem() {
      driveFrontRight.configFactoryDefault();
      driveFrontLeft.configFactoryDefault();
      driveBackRight.configFactoryDefault();
      driveBackLeft.configFactoryDefault();

      driveFrontRight.setNeutralMode(NeutralMode.Brake);
      driveFrontLeft.setNeutralMode(NeutralMode.Brake);
      driveBackRight.setNeutralMode(NeutralMode.Brake);
      driveBackLeft.setNeutralMode(NeutralMode.Brake);

      driveRight.setInverted(false);
      driveLeft.setInverted(false);


  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void Drive(double forward, double turn){
    _Drive.arcadeDrive(forward, turn);
  }

  public void StopDrive() {
    _Drive.arcadeDrive(0, 0);
  }
}
