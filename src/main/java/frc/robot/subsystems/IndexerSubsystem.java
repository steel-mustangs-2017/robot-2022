// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.IndexerConstants;

public class IndexerSubsystem extends SubsystemBase {

  private final WPI_TalonSRX indexerLeft = new WPI_TalonSRX(IndexerConstants.DEVICE_ID_INDEXER_LEFT);
  private final WPI_TalonSRX indexerRight = new WPI_TalonSRX(IndexerConstants.DEVICE_ID_INDEXER_RIGHT);
  private final WPI_TalonSRX indexerTop = new WPI_TalonSRX(IndexerConstants.DEVICE_ID_INDEXER_TOP);
  private final double speed = 1;
  /** Creates a new IndexerSubsystem. */
  public IndexerSubsystem() {
    indexerLeft.configFactoryDefault();
    indexerRight.configFactoryDefault();
    indexerTop.configFactoryDefault();

    indexerRight.setInverted(true);

    indexerLeft.setNeutralMode(NeutralMode.Brake);
    indexerRight.setNeutralMode(NeutralMode.Brake);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
  public void RunIndex() {
    indexerLeft.set(speed);
    indexerRight.set(speed);
    indexerTop.set(1);
  }

  public void RumbleIndex() {
    indexerLeft.set(speed);
    indexerRight.set(-speed);
    indexerTop.set(-speed);
  }

  public void StopIndex() {
    indexerLeft.set(0);
    indexerRight.set(0);
    indexerTop.set(0);

  }
}
