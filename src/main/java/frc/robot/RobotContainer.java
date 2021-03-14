/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.drivetrainSubsystem;
import frc.robot.commands.driveCommand;
import frc.robot.Constants;
import frc.robot.commands.driveForwardCommand;

public class RobotContainer {

  //217.2944297082

  //constants

  public static Constants m_constants;

  //subsystems
   
  private final drivetrainSubsystem m_drivetrainSubsystem = new drivetrainSubsystem();

  //commands

  public Command GenerateEncoderDriveCommand(double inches, double speed)
  {

    
      double encoder = inches * 217.2944297082;

      Command m_driveStraightUntilEncoderValueCommand = new driveForwardCommand(encoder, speed, m_drivetrainSubsystem);

      return m_driveStraightUntilEncoderValueCommand;
      
  }

  public RobotContainer() {

    configureButtonBindings();

  }

  public void teleopInit() {

    driveCommand m_driveCommand = new driveCommand(m_constants.Xbox1, m_drivetrainSubsystem);

    m_drivetrainSubsystem.setDefaultCommand(m_driveCommand);

  }

  public void driveSetup() {

    CANSparkMax leftFrontMotor = new CANSparkMax(Constants.driveFrontLeftMotor, MotorType.kBrushless);
    CANSparkMax rightFrontMotor = new CANSparkMax(Constants.driveFrontRightMotor, MotorType.kBrushless);
    CANSparkMax leftBackMotor = new CANSparkMax(Constants.driveBackLeftMotor, MotorType.kBrushless);
    CANSparkMax rightBackMotor = new CANSparkMax(Constants.driveBackRightMotor, MotorType.kBrushless);

    SpeedControllerGroup leftDrive = new SpeedControllerGroup(leftFrontMotor, leftBackMotor);
    SpeedControllerGroup rightDrive = new SpeedControllerGroup(rightFrontMotor, rightBackMotor); 

    m_drivetrainSubsystem.driveSetup(leftDrive, rightDrive);

  }

  private void configureButtonBindings() {


  }

  public Command getAutonomousCommand() {
   
    return GenerateEncoderDriveCommand(60, .3);

  }

}
