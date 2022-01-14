// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.math.MathUtil;
import frc.robot.Constants.ControllerConstants;
import frc.robot.subsystems.IndexerSubsystem;
import frc.robot.subsystems.LimelightSubsystem;
import frc.robot.subsystems.ShooterSubsystem;
import frc.robot.subsystems.TurretSubsystem;

public class FullAutoCommand extends CommandBase {
  private final Joystick operatorController;

  private final ShooterSubsystem shooterSubsystem;
  private final IndexerSubsystem indexerSubsystem;
  private final TurretSubsystem turretSubsystem;
  
  private final LimelightSubsystem limelightSubsystem;
   Timer Timer = new Timer();

  private double time = 0;

  private double speed = 0;

  private final PIDController pid = new PIDController(1, .1, 0);
  /** Creates a new FullAutoCommand. */
  public FullAutoCommand(Joystick operatorController, ShooterSubsystem shooterSubsystem, IndexerSubsystem indexerSubsystem,  LimelightSubsystem limelightSubsystem, TurretSubsystem turretSubsystem, double time) {
    this.operatorController = operatorController;
    this.shooterSubsystem = shooterSubsystem;
    this.indexerSubsystem = indexerSubsystem;
    this.turretSubsystem = turretSubsystem;
    this.limelightSubsystem = limelightSubsystem;

    this.time = time;

    addRequirements(shooterSubsystem, indexerSubsystem, limelightSubsystem);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    super.initialize();
    limelightSubsystem.setLedOn();
    limelightSubsystem.setVision();

    indexerSubsystem.StopIndex();
    shooterSubsystem.StopShoot();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    super.execute();
    
    
    //aimShooter();
  
    if(shooterSubsystem.AtShootVelocity() ){
      shooterSubsystem.Shoot(1);
      indexerSubsystem.RunIndex();
    }
    else{
      shooterSubsystem.Shoot(1);
      indexerSubsystem.StopIndex();
      
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    super.end(interrupted);
    limelightSubsystem.setLedOff();
    limelightSubsystem.setCamera();
    shooterSubsystem.StopShoot();
    indexerSubsystem.StopIndex();
  }

  public void aimShooter(){
    if(limelightSubsystem.validTarget() == 1 ){
      
    double angle = limelightSubsystem.getAngle();
    double turn = pid.calculate(angle/10);
    System.out.println("BEFORE: " + turn);
    turn = MathUtil.clamp(turn, -.5, .5);
    System.out.println(turn);
    
    turretSubsystem.Turn(-turn);
  }
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return Timer.hasElapsed(time);
  }
}
