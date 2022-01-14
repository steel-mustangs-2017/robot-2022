// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.CommandBase;

import frc.robot.subsystems.ClimberSubsystem;

public class ClimbCommand extends CommandBase {

  private final ClimberSubsystem climberSubsystem;
  private final Joystick operatorController;
  /** Creates a new ClimbCommand. */
  public ClimbCommand(ClimberSubsystem climberSubsystem, Joystick operatorController) {

    this.climberSubsystem = climberSubsystem;
    this.operatorController = operatorController;

    addRequirements(climberSubsystem);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    climberSubsystem.StopClimb();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
     double speed = -operatorController.getRawAxis(5);
      climberSubsystem.ClimbUp(speed);

  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    climberSubsystem.StopClimb();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
