// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.Constants.OperatorConstants;
import frc.robot.Constants.PositionConstants;
import frc.robot.Constants.SpeedConstants;
import frc.robot.commands.Autos;
import frc.robot.commands.ExampleCommand;
import frc.robot.commands.PivotCommand;
import frc.robot.commands.ShooterCommand;
import frc.robot.subsystems.ClimberSubsystem;
import frc.robot.subsystems.ExampleSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.PivotSubsystem;
import frc.robot.subsystems.ShooterSubsystem;
import frc.robot.subsystems.SpindexerSubsystem;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final ExampleSubsystem m_exampleSubsystem = new ExampleSubsystem();
  private final ClimberSubsystem m_climberSubsystem = new ClimberSubsystem();
  private final IntakeSubsystem m_intakeSubsystem = new IntakeSubsystem();
  private final ShooterSubsystem m_shooterSubsystem = new ShooterSubsystem();
  private final SpindexerSubsystem m_spindexerSubsystem = new SpindexerSubsystem();
  private final PivotSubsystem m_pivotSubsystem = new PivotSubsystem();

  // Replace with CommandPS4Controller or CommandJoystick if needed
  private final CommandXboxController m_driverController =
      new CommandXboxController(OperatorConstants.kDriverControllerPort);
  private final CommandXboxController m_operatorController =
      new CommandXboxController(OperatorConstants.kOperatorControllerPort);

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the trigger bindings
    configureBindings();
  }

  /**
   * Use this method to define your trigger->command mappings. Triggers can be created via the
   * {@link Trigger#Trigger(java.util.function.BooleanSupplier)} constructor with an arbitrary
   * predicate, or via the named factories in {@link
   * edu.wpi.first.wpilibj2.command.button.CommandGenericHID}'s subclasses for {@link
   * CommandXboxController Xbox}/{@link edu.wpi.first.wpilibj2.command.button.CommandPS4Controller
   * PS4} controllers or {@link edu.wpi.first.wpilibj2.command.button.CommandJoystick Flight
   * joysticks}.
   */
  private void configureBindings() {

    /******************Driver Controller*************************/

    //Intake Rollers In
    m_driverController.rightTrigger().whileTrue(new RunCommand(() -> m_intakeSubsystem.runRollers(SpeedConstants.kRollerSpeed), m_intakeSubsystem))
      .onFalse(new InstantCommand(() -> m_intakeSubsystem.runRollers(0)));
    //Intake Rollers Out
    m_driverController.leftTrigger().whileTrue(new RunCommand(() -> m_intakeSubsystem.runRollers(-SpeedConstants.kRollerSpeed), m_intakeSubsystem))
      .onFalse(new InstantCommand(() -> m_intakeSubsystem.runRollers(0)));
    //Intake Pivot Down
    m_driverController.a().whileTrue(new PivotCommand(PositionConstants.kIntakeDown, m_pivotSubsystem))
      .onFalse(new InstantCommand(() -> m_pivotSubsystem.manualPivot(0)));
    //Intake Pivot Up
    m_driverController.y().whileTrue(new PivotCommand(PositionConstants.kIntakeUp, m_pivotSubsystem))
      .onFalse(new InstantCommand(() -> m_pivotSubsystem.manualPivot(0)));
    
    /*****************Operator Controller***********************/

    //Motion Magic Shooter
    m_operatorController.x().whileTrue(new ShooterCommand(0, m_shooterSubsystem, m_spindexerSubsystem)) //FIXME Need limelight speed calculation
      .onFalse(new InstantCommand(() -> m_shooterSubsystem.manualSpeed(0)));

    m_operatorController.y().whileTrue(new ShooterCommand(SpeedConstants.kMaxSpeed, m_shooterSubsystem, m_spindexerSubsystem))
      .onFalse(new InstantCommand(() -> m_shooterSubsystem.manualSpeed(0)))
      .onFalse(new InstantCommand(() -> m_spindexerSubsystem.stopFuel()));

    //manual shooter
    m_operatorController.a().whileTrue(new RunCommand(() -> m_shooterSubsystem.manualSpeed(SpeedConstants.kShooterSpeed), m_shooterSubsystem))
      .onFalse(new InstantCommand(() -> m_shooterSubsystem.manualSpeed(0)));
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An example command will be run in autonomous
    return Autos.exampleAuto(m_exampleSubsystem);
  }
}
