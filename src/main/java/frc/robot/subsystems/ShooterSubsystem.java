// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.controls.DutyCycleOut;
import com.ctre.phoenix6.controls.Follower;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.MotorAlignmentValue;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.MotorConstants;

public class ShooterSubsystem extends SubsystemBase {
  /** Creates a new ShooterSubsystem. */
  private final TalonFX shooterLeftMotor = new TalonFX(MotorConstants.kShooterLeftMotor, MotorConstants.CANbus);
  private final TalonFX shooterRightMotor = new TalonFX(MotorConstants.kShooterRightMotor, MotorConstants.CANbus);

  private final DutyCycleOut talonOut = new DutyCycleOut(0);

  public ShooterSubsystem() {
    TalonFXConfiguration config = new TalonFXConfiguration();

    shooterLeftMotor.getConfigurator().apply(config);
    shooterRightMotor.getConfigurator().apply(config);
    
    shooterRightMotor.setControl(new Follower(shooterLeftMotor.getDeviceID(), MotorAlignmentValue.Aligned));
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void shooterSub(double speed){

  }
}
