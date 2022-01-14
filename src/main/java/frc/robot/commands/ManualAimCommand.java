// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.math.MathUtil;
import frc.robot.Constants.ControllerConstants;
import frc.robot.subsystems.TurretSubsystem;

public class ManualAimCommand extends CommandBase {
  private final TurretSubsystem turretSubsystem;
  private final Joystick operatorController;
  private double turn = 1;

  /** Creates a new ManualAimCommand. */
  public ManualAimCommand(Joystick operatorController, TurretSubsystem turretSubsystem) {
    this.turretSubsystem = turretSubsystem;
    this.operatorController = operatorController;

    addRequirements(turretSubsystem);
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
    turn = operatorController.getRawAxis(ControllerConstants.AIM_AXIS_ID)/4;
    //System.out.println(turn);
    if(Math.abs(turn) < .05){turn = 0;}
    if(turn > 0){turn = MathUtil.clamp(turn, .1, .3);}
    else if (turn < 0){turn = MathUtil.clamp(turn,-1,-.3);}
    //System.out.println(turn);
    turretSubsystem.Turn(turn);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    super.end(interrupted);
    turretSubsystem.StopTurn();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
