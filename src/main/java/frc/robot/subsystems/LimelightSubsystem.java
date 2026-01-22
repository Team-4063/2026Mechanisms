// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class LimelightSubsystem extends SubsystemBase {
  /** Creates a new LimelightSubsystem. */
  NetworkTable table;

  private boolean visionLocked = false;

  private boolean validTargets;
  public LimelightSubsystem() {
    table = NetworkTableInstance.getDefault().getTable("limelight");

  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    validTargets = isTargetValid();
  }

  public boolean isTargetValid(){
    return (table.getEntry("tv").getDouble(0) == 1);
  }

  public double getHorizontalOffset(){
    return table.getEntry("tx").getDouble(0);
  }

  public double getVerticalOffset(){
    return table.getEntry("ty").getDouble(0);
  }

  public double getTargetArea() {
    return table.getEntry("ta").getDouble(0);
  }

  public double getTargetLatency() {
    return table.getEntry("tl").getDouble(0);
  }

  public double getCaptureLatency() {
    return table.getEntry("cl").getDouble(0);
  }

  public boolean hasLockedVisionTarget(){
    return visionLocked;
  }
}
