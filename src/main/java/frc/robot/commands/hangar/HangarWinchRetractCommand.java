package frc.robot.commands.hangar;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Hangar;

import static frc.robot.Constants.HangarConstants.C_WINCH_CIRCUMFERENCE;
import static frc.robot.Constants.HangarConstants.C_WINCH_GEARDIFF;

public class HangarWinchRetractCommand extends CommandBase {

    private final Hangar m_hangar;
    private final double distance;
    private final double startRotation;

    public HangarWinchRetractCommand(Hangar hangar, double distance) {
        m_hangar = hangar;
        this.distance = distance;
        this.startRotation = hangar.getWinchRotation();

        addRequirements(hangar);
    }

    @Override
    public void initialize() {
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        m_hangar.setWinchMotorSpeed(-1);
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        m_hangar.setWinchMotorSpeed(0);
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return (startRotation - m_hangar.getWinchRotation()) * C_WINCH_GEARDIFF * C_WINCH_CIRCUMFERENCE > distance;
    }

}
