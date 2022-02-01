// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants.ControllerConstants;
import frc.robot.subsystems.ShooterSubsystem;

public class ShootCommand extends CommandBase {

  private final ShooterSubsystem shooterSubsystem;
  private final Joystick operatorController;
  private double speed;
  /** Creates a new ShootCommand. */
  public ShootCommand(Joystick operatorController, ShooterSubsystem shooterSubsystem) {
      this.shooterSubsystem = shooterSubsystem;
      this.operatorController = operatorController;
  
    addRequirements(shooterSubsystem);
    // Use addRequirements() here to declare subsystem dependencies.
  }

// Called when the command is initially scheduled.
  @Override
  public void initialize() {
    super.initialize();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    super.execute();
    speed = operatorController.getRawAxis(ControllerConstants.SHOOT_AXIS_2_ID);
    if(speed>.1){
    speed = 0.3;
    }
    shooterSubsystem.ShootHigh(speed);
  }


  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    super.end(interrupted);
    shooterSubsystem.StopShoot();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
