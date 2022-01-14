// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;


import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import frc.robot.Constants.ControllerConstants;
import frc.robot.Constants.ShooterConstants;
import frc.robot.commands.ClimbCommand;
import frc.robot.commands.DeployTimeCommand;
import frc.robot.commands.DriveCommand;
import frc.robot.commands.DriveTimeCommand;
import frc.robot.commands.FullAutoCommand;
import frc.robot.commands.IntakeTime;
import frc.robot.commands.ManualAimCommand;
import frc.robot.commands.ShootCommand;
import frc.robot.commands.TurretInitCommand;
import frc.robot.subsystems.ChassisSubsystem;
import frc.robot.subsystems.ClimberSubsystem;
import frc.robot.subsystems.IndexerSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.LimelightSubsystem;
import frc.robot.subsystems.ShooterSubsystem;
import frc.robot.subsystems.TurretSubsystem;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...

  private final Joystick operatorController = new Joystick(ControllerConstants.PORT_ID_OPERATOR_CONTROLLER);
  private final Joystick driverController = new Joystick(ControllerConstants.PORT_ID_DRIVER_CONTROLLER);

  private final ChassisSubsystem chassisSubsystem = new ChassisSubsystem();
  private final ShooterSubsystem shooterSubsystem = new ShooterSubsystem();
  private final TurretSubsystem turretSubsystem = new TurretSubsystem();
  private final IndexerSubsystem indexerSubsystem = new IndexerSubsystem();
  private final IntakeSubsystem intakeSubsystem = new IntakeSubsystem();
  private final LimelightSubsystem limelightSubsystem = new LimelightSubsystem();
  private final ClimberSubsystem climberSubsystem = new ClimberSubsystem();

  private final DriveCommand driveCommand = new DriveCommand(driverController, chassisSubsystem);
  private final ShootCommand shootCommand = new ShootCommand(operatorController, shooterSubsystem);
  private final ManualAimCommand manualAimCommand = new ManualAimCommand(operatorController, turretSubsystem);
  private final FullAutoCommand fullAutoCommand = new FullAutoCommand(operatorController, shooterSubsystem, indexerSubsystem, limelightSubsystem, turretSubsystem,1);
  private final TurretInitCommand turretInitCommand = new TurretInitCommand(turretSubsystem);
  private final ClimbCommand climbCommand = new ClimbCommand(climberSubsystem, operatorController);
  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    new TurretInitCommand(turretSubsystem);
    // Configure the button bindings
    configureDefaultCommands();
    configureButtonBindings();
    
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {

    /*boolean indexButton = operatorController.getRawButton(ControlerConstants.INDEX_BUTTON_ID);
    boolean rumbleButton = operatorController.getRawButton(ControlerConstants.RUMBLE_BUTTON_ID);

    if(rumbleButton){indexerSubsystem.RumbleIndex();}
    else if(indexButton){indexerSubsystem.RunIndex();}*/

      /*new JoystickButton(operatorController, ControlerConstants.INDEX_BUTTON_ID)
      .whenActive(new RunCommand(() -> {
        intakeSubsystem.Intake();
        indexerSubsystem.RunIndex();
      }, indexerSubsystem, intakeSubsystem))
      .whenInactive(new RunCommand(() -> {
        indexerSubsystem.StopIndex();
        intakeSubsystem.IntakeStop();
      }, indexerSubsystem, intakeSubsystem));*/

     
      new JoystickButton(operatorController, ControllerConstants.INDEX_BUTTON_ID)
      .whenActive(new RunCommand(indexerSubsystem::RunIndex, indexerSubsystem))
      .whenInactive(new RunCommand(indexerSubsystem::StopIndex, indexerSubsystem));
      new JoystickButton(operatorController, ControllerConstants.RUMBLE_BUTTON_ID)
      .whenActive(new RunCommand(indexerSubsystem::RumbleIndex, indexerSubsystem))
      .whenInactive(new RunCommand(indexerSubsystem::StopIndex, indexerSubsystem));

      /*new JoystickButton(operatorController, 6)
      .whenActive(new RunCommand(()-> {
        shooterSubsystem.Shoot(1);
        
      }, shooterSubsystem))
      .whenInactive(new RunCommand(shooterSubsystem::StopShoot, shooterSubsystem));
      */

      new JoystickButton(operatorController, ControllerConstants.INTAKE_BUTTON_ID)
      .whenActive(new RunCommand(intakeSubsystem::Intake, intakeSubsystem))
      .whenInactive(new RunCommand(intakeSubsystem::IntakeStop, intakeSubsystem));

      new JoystickButton(operatorController, 4)
      .whenActive(new RunCommand(intakeSubsystem::Reverse, intakeSubsystem))
      .whenInactive(new RunCommand(intakeSubsystem::IntakeStop, intakeSubsystem));

      new JoystickButton(operatorController, ControllerConstants.INTAKE_DEPLOY_BUTTON_ID)
      .whenActive(new RunCommand(intakeSubsystem::Deploy, intakeSubsystem))
      .whenInactive(new RunCommand(intakeSubsystem::DeployStop, intakeSubsystem));
      new JoystickButton(operatorController, ControllerConstants.INTAKE_RETRACT_BUTTON_ID)
      .whenActive(new RunCommand(intakeSubsystem::Retract, intakeSubsystem))
      .whenInactive(new RunCommand(intakeSubsystem::DeployStop, intakeSubsystem));
      
      new JoystickButton(operatorController, 7)
      .whenPressed(new TurretInitCommand(turretSubsystem));

     
     /* new JoystickButton(operatorController, 8)
      .whenPressed(fullAutoCommand.perpetually());

      
      //.whenInactive(new RunCommand(shooterSubsystem::StopShoot, shooterSubsystem));
     
     
      /*new JoystickButton(operatorController, 6)
      .whenActive(new RunCommand(shooterSubsystem::Shoot, shooterSubsystem))
      .whenInactive(new RunCommand(shooterSubsystem::StopShoot, shooterSubsystem));
     // .whenPressed(fullAutoCommand.perpetually());*/
  }

  

  private void configureDefaultCommands() {
    chassisSubsystem.setDefaultCommand(driveCommand);
    shooterSubsystem.setDefaultCommand(shootCommand);
    //indexerSubsystem.setDefaultCommand(fullAutoCommand);
    turretSubsystem.setDefaultCommand(manualAimCommand);
    climberSubsystem.setDefaultCommand(climbCommand);
    //limelightSubsystem.setCamera();
    //limelightSubsystem.setVision();
    //limelightSubsystem.setLedOff();
   }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public CommandBase getAutonomousCommand() {
    //final int type = 0;

    
      return
      new DriveTimeCommand(chassisSubsystem, 1, -.5, 0).andThen(
        new DeployTimeCommand(intakeSubsystem, .5)).andThen(
          new FullAutoCommand(operatorController, shooterSubsystem, indexerSubsystem, limelightSubsystem, turretSubsystem, 5).withTimeout(5)).andThen(
            turretInitCommand.withTimeout(1)
            );
    
    /*
        return

        new DeployTimeCommand(intakeSubsystem, .6).andThen(
          new TurretInitCommand(turretSubsystem).withTimeout(1)).andThen(
            new DriveTimeCommand(chassisSubsystem, 1, .5, 0)).andThen(
              new FullAutoCommand(operatorController, shooterSubsystem, indexerSubsystem, limelightSubsystem, turretSubsystem, 5).withTimeout(5)).andThen(
                new DriveTimeCommand(chassisSubsystem, 3, .5, 0).raceWith(
                new IntakeTime(intakeSubsystem, 3))).andThen(
                  new DriveTimeCommand(chassisSubsystem, 3, -5, 0)).andThen(
                    new FullAutoCommand(operatorController, shooterSubsystem, indexerSubsystem, limelightSubsystem, turretSubsystem, 5).withTimeout(5)
                );*/
           

    }
    // An ExampleCommand will run in autonomous
    //return new WaitCommand(2).andThen(
    //new FullAutoCommand(operatorController, shooterSubsystem, indexerSubsystem, limelightSubsystem, turretSubsystem, 30));
    /*new DriveTimeCommand(chassisSubsystem, 1, -.5, 0).andThen(
     new DeployTimeCommand(intakeSubsystem, .5)
    )
    .andThen(
      new FullAutoCommand(operatorController, shooterSubsystem, indexerSubsystem, limelightSubsystem).withTimeout(5))
    .andThen(
      turretInitCommand);*/
  
}
