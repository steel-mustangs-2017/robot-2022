// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;


import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ShooterConstants;

public class TurretSubsystem extends SubsystemBase {
  
  private final WPI_TalonSRX turretMotor = new WPI_TalonSRX(ShooterConstants.DEVICE_ID_TURRET);
  private boolean Limit = false;
  private boolean init = false;
  /** Creates a new TurretSubsystem. */
  public TurretSubsystem() {
    turretMotor.configFactoryDefault();
    turretMotor.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder);
    turretMotor.setSensorPhase(true);
    turretMotor.configNominalOutputForward(.1);
    turretMotor.configNominalOutputReverse(-.1);
    reset();
    //turretMotor.configForwardSoftLimitThreshold();
  }

  @Override
  public void periodic() {
    
    //turretMotor.setSelectedSensorPosition(0,0,10);
    //System.out.println("Sensor Position: " + turretMotor.getSelectedSensorPosition() + " " + Limit);
    //This method will be called once per scheduler run
  }
  public void Turn(double speed){
    if(Limit){
      System.out.println("LIMIT " + getPosition());
      if(speed < 0 && getPosition() > 25000){speed = 0;System.out.println("LIMIT RIGHT");}
      else if (speed > 0 && getPosition() < -100000){speed = 0;System.out.println("LIMIT LEFT");}
    }
    turretMotor.set(speed);
  }
  public void StopTurn(){
    turretMotor.set(0);
  }
  public void reset(){
      turretMotor.setSelectedSensorPosition(0);
      Limit = false;
    }
    public double getPosition() {
      return turretMotor.getSelectedSensorPosition();
      
    }
    public void LimitInit(){
      Limit = true;
      /*turretMotor.configForwardSoftLimitThreshold(40000);
      turretMotor.configReverseSoftLimitThreshold(-100000);
      turretMotor.configReverseSoftLimitEnable(true);
      turretMotor.configForwardSoftLimitEnable(true);*/
    }
    public void Init(){
      init = true;
    }
    public boolean getInit(){return init;}
}
