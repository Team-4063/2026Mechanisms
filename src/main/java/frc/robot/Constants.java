// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
  
  public static class SpeedConstants{
    public static final double kShooterSpeed = 0.5; //manual speed
    public static final double kTargetVelocity = 8; //target velocity for motion magic in rps
  }
  
  public static class OperatorConstants {
    public static final int kDriverControllerPort = 0;
    public static final int kOperatorControllerPort = 1;
  }

  public static class MotorConstants {
    public static final String CANbus = "canivore";

    public static final int kShooterLeftMotor = 11;
    public static final int kShooterRightMotor = 12;
    public static final int kTurretMotor = 13;
  }

  public static class TurretConstants {
    public static final double kTurretRatio = 10.0; //100T 10DP driven by 10T 10DP

    //FIXME
    public static final double Front = 0.0;
    public static final double Back = 10.0;
    public static final double Left = 15.0;
    public static final double Right = 5.0;
    public static final double AutoDepot = 17.0;
    public static final double AutoOutpost = 13.0;

    public static final double CruiseVelocity = 3.5; //1 rotations per second cruise
    public static final double Acceleration = 8; //10 Take approx 0.5 seconds to reach max vel
    public static final double Jerk = 100; //Take approximately 0.1 seconds to reach max accel

    public static final double kP = 5;  //5 An error of 1 rotation results in kP V output
    public static final double kI = 0.05; //0 No output for integrated error
    public static final double kD = 0.15; //0.1 A velocity of 1 rps results in kD V output
    public static final double kV = 0.25; //0.12 A velocity target of 1 rps results in kV V output
    public static final double kS = 0.25; //0.25 Approximately kS V to get the mechanism moving, output to overcome static friction

    public static final boolean StatorEnable = true;
    public static final double StatorLimit = 60;

    public static final boolean SupplyEnable = true;
    public static final double SupplyLimit = 60;

    public static final double desiredRotations = 10;
  }

}
