// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.IntakeConstants;

public class IntakeSubsystem extends SubsystemBase {
  private final WPI_TalonSRX intakeMain = new WPI_TalonSRX(IntakeConstants.DEVICE_ID_INTAKE);
  private final WPI_TalonSRX intakeDeploy = new WPI_TalonSRX(IntakeConstants.DEVICE_ID_INTAKE_DEPLOY);
  private final WPI_TalonSRX intakeDeploySlave = new WPI_TalonSRX(IntakeConstants.DEVICE_ID_INTAKE_DEPLOY_SLAVE);

  /** Creates a new IntakeSubsystem. */
  public IntakeSubsystem() {
    intakeMain.configFactoryDefault();
    intakeDeploy.configFactoryDefault();
    intakeDeploySlave.configFactoryDefault();

    intakeMain.setInverted(true);
    intakeDeploySlave.setInverted(true);

    
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void Deploy() {
    intakeDeploy.set(.33);
    intakeDeploySlave.set(.33);
  }
  public void Retract() {
    intakeDeploy.set(-.75);
    intakeDeploySlave.set(-.75);

  }
  public void DeployStop() {
    intakeDeploy.set(0);
    intakeDeploySlave.set(0);

  }

  public void Intake() {
    intakeMain.set(.88);
  }
  public void Reverse() {
    intakeMain.set(-1);
  }
  public void IntakeStop() {
    intakeMain.set(0);
  }

  public void IntakeFullStop() {
    intakeDeploy.set(0);
    intakeMain.set(0);
  }


}
