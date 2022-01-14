// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ChassisSubsystem;


public class DriveTimeCommand extends CommandBase {

  ChassisSubsystem chassisSubsystem;
  double Time;
  double speed;
  double turn;

  Timer time = new Timer();
  /** Creates a new DriveTimeCommand. */
  public DriveTimeCommand(ChassisSubsystem chassisSubsystem, double Time, double speed, double turn) {
    this.chassisSubsystem = chassisSubsystem;
    this.Time = Time;
    this.speed = speed;
    this.turn = turn;

    addRequirements(chassisSubsystem);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    time.start();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    chassisSubsystem.Drive(speed, turn);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    chassisSubsystem.StopDrive();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return time.hasElapsed(Time);
  }
}
