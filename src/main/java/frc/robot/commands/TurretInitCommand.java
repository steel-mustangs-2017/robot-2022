// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.math.MathUtil;
import frc.robot.Constants.ShooterConstants;
import frc.robot.subsystems.TurretSubsystem;



public class TurretInitCommand extends CommandBase {
  private final TurretSubsystem turretSubsystem;

  private final double tolerance = 1000;
  private boolean atPoint = false;

  private final double target = 112000;

  /** Creates a new TurretInitCommand. */
  public TurretInitCommand(TurretSubsystem turretSubsystem) {
    this.turretSubsystem = turretSubsystem;

    addRequirements(turretSubsystem);

  
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    super.initialize();
    turretSubsystem.reset();

  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    super.execute();
    PIDController pid = new PIDController(ShooterConstants.TURRET_KP, 0.00000005, ShooterConstants.TURRET_KD);
    pid.setTolerance(tolerance);
    
    System.out.println("hi: " +MathUtil.clamp(pid.calculate(target, turretSubsystem.getPosition()), -.75, .75));
    turretSubsystem.Turn(MathUtil.clamp(pid.calculate(target, turretSubsystem.getPosition()), -.75, .75)); 
 
    
    atPoint = pid.atSetpoint();
    pid.close();
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    super.end(interrupted);
    turretSubsystem.StopTurn();
    turretSubsystem.reset();
    turretSubsystem.LimitInit();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return atPoint;
  }
}