// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants.ControllerConstants;
import frc.robot.subsystems.ChassisSubsystem;

public class DriveCommand extends CommandBase {
  private final ChassisSubsystem chassisSubsystem;
  private final Joystick driverController;

  private double forward;
  private double turn;

  private double forwardMult;
  private double turnMult;

  /** Creates a new DriveCommand. */
  public DriveCommand(Joystick driverController, ChassisSubsystem chassisSubsystem) {
    this.chassisSubsystem = chassisSubsystem;
    this.driverController = driverController;

    addRequirements(chassisSubsystem);
  // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    forward = driverController.getRawAxis(ControllerConstants.FORWARD_AXIS_ID);
    turn = driverController.getRawAxis(ControllerConstants.TURN_AXIS_ID);
    if(Math.abs(forward) < 0.1){forward = 0;}

    forwardMult = 1-(driverController.getRawAxis(ControllerConstants.FORWARD_MULT_AXIS_ID));
    turnMult = (1+driverController.getRawAxis(ControllerConstants.TURN_MULT_AXIS_ID))*.5;

    forward = forward * forwardMult;
    turn = turn * turnMult;

    chassisSubsystem.Drive(forward, turn);

  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    chassisSubsystem.StopDrive();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
