// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;


import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ShooterConstants;

public class ShooterSubsystem extends SubsystemBase {

  private final WPI_TalonFX shooterMotor = new WPI_TalonFX(ShooterConstants.DEVICE_ID_SHOOTER_MASTER);
  private final WPI_TalonFX shooterMotorSlave = new WPI_TalonFX(ShooterConstants.DEVICE_ID_SHOOTER_SLAVE);

  /** Creates a new ShooterSubsystem. */
  public ShooterSubsystem() {
  shooterMotor.configFactoryDefault();
  shooterMotorSlave.configFactoryDefault();

  shooterMotor.configSelectedFeedbackSensor(FeedbackDevice.IntegratedSensor);
  shooterMotorSlave.configSelectedFeedbackSensor(FeedbackDevice.IntegratedSensor);
  
  shooterMotor.setInverted(true);
  shooterMotorSlave.setInverted(false);
  
  }

  @Override
  public void periodic() {
    //System.out.println("Velocity: " + shooterMotor.getSelectedSensorVelocity());
    //System.out.println(shooterMotor.getSelectedSensorVelocity() > 20000);
    // This method will be called once per scheduler run
  }

  public void Shoot(double speed){
    shooterMotor.set(speed);
    shooterMotorSlave.set(speed);
    if(AtShootVelocity()){
      System.out.println("SHOOT");
    }
    
  }
  public boolean AtShootVelocity(){
    return shooterMotor.getSelectedSensorVelocity() > 18000;
  }
  public void StopShoot(){
    shooterMotor.set(0);
    shooterMotorSlave.set(0);
  }
}

 //one button