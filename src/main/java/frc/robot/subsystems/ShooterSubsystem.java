// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix6.configs.MotionMagicConfigs;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.controls.DutyCycleOut;
import com.ctre.phoenix6.controls.Follower;
import com.ctre.phoenix6.controls.MotionMagicVelocityVoltage;
import com.ctre.phoenix6.controls.MotionMagicVoltage;
import com.ctre.phoenix6.controls.VelocityVoltage;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.MotorAlignmentValue;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.MotorConstants;

public class ShooterSubsystem extends SubsystemBase {
  /** Creates a new ShooterSubsystem. */
  private final TalonFX shooterLeftMotor = new TalonFX(MotorConstants.kShooterLeftMotor, MotorConstants.CANbus);
  private final TalonFX shooterRightMotor = new TalonFX(MotorConstants.kShooterRightMotor, MotorConstants.CANbus);

  private final DutyCycleOut talonOut = new DutyCycleOut(0);

  private final MotionMagicVelocityVoltage m_motionMagic = new MotionMagicVelocityVoltage(0).withSlot(0);

  private double velocitySetpoint = 0.0;

  public ShooterSubsystem() {
    TalonFXConfiguration config = new TalonFXConfiguration();

    //set slot 0 gains
    var slot0Configs = config.Slot0;
    slot0Configs.kS = 0.25; //Add kS V output to overcome static friction, default 0.25
    slot0Configs.kV = 0.12; //A velocity target of 1 rps results in kV V output, default 0.12
    slot0Configs.kA = 0.01; //An acceleration of 1 rps/s requires kA V output, default 0.01
    slot0Configs.kI = 0; //no output for integrated error
    slot0Configs.kD = 0; //no output for error derivative

    var mm = config.MotionMagic;
    mm.MotionMagicAcceleration = 400; //Target acceleration of 400 rps/s (0.25 seconds to max)
    mm.MotionMagicJerk = 4000; //Target jerk of 4000 rps/s/s (0.1 seconds)

    shooterLeftMotor.getConfigurator().apply(config);
    
    shooterRightMotor.setControl(new Follower(shooterLeftMotor.getDeviceID(), MotorAlignmentValue.Opposed));
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void motionMagicSetVelocity(){
    //set velocity to 8 rps, add 0.5 V to overcome gravity
    shooterLeftMotor.setControl(m_motionMagic.withVelocity(velocitySetpoint).withFeedForward(0.5));
  }

  public double getLeftVelocity(){
    double velocity = shooterLeftMotor.getVelocity().getValueAsDouble();
    return velocity;
  }

  public double getRightVelocity(){
    double velocity = shooterRightMotor.getVelocity().getValueAsDouble();
    return velocity;
  }
  
  public void setVelocitySetpoint(double velocity){
    velocitySetpoint = velocity;
  }

  public void setVelocity(double velocity){
    setVelocitySetpoint(velocity); //velocity constants
  }

  public void manualSpeed(double speed){
    shooterLeftMotor.set(speed);
    shooterRightMotor.set(-speed);
  }
}
