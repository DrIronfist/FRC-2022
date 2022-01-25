// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.subsystems.Feeder;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj.command.InstantCommand;


/** An example command that uses an example subsystem. */
public class RunFeeda extends InstantCommand {

  private Feeder s_feeder;


  /**
   * Creates a new ExampleCommand.
   *
   * The subsystem used by this command.
   */


  public RunFeeda(Feeder s_feeder) {

    this.s_feeder = s_feeder;
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  /*  if(shooterOn == true)
    {

    }
    else{
      continue;
    }
*/
    s_feeder.feederOn();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {}

  // Called once the command ends or is interrupted.
  @Override
  public void end() {
    s_feeder.feederOff();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return true;
  }
}
