// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ClimberConstants;

public class ClimberSubsystem extends SubsystemBase {

  WPI_TalonFX climbRight = new WPI_TalonFX(ClimberConstants.DEVICE_ID_CLIMB_RIGHT);
  WPI_TalonFX climbLeft = new WPI_TalonFX(ClimberConstants.DEVICE_ID_CLIMB_LEFT);

  
  /** Creates a new ClimberSubsystem. */
  public ClimberSubsystem() {
    climbRight.setInverted(false);
    climbLeft.setInverted(false);

    climbRight.setNeutralMode(NeutralMode.Brake);
    climbLeft.setNeutralMode(NeutralMode.Brake);

  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
  public void StopClimb() {
    climbLeft.set(0);
    climbRight.set(0);
  }
  public void ClimbUp(double speed) {
    climbLeft.set(speed);
    climbRight.set(speed);
  }
  public void ClimbDown(double speed) {
    climbLeft.set(-speed);
    climbRight.set(-speed);
  }
}
