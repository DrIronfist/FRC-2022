package frc.robot.commands.intake;

import java.time.Clock;
import java.util.ResourceBundle.Control;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.commands.feeder.BottomFeederToggleCommand;
import frc.robot.subsystems.Feeder;
import frc.robot.subsystems.Intake;

public class IntakeConstantControlCommand extends CommandBase {
    
    private final Intake m_intake;
    private final Feeder m_feeder;
    private final XboxController controller;

    private long time;

    public IntakeConstantControlCommand(Intake intake, XboxController controller, Feeder feeder) {

        m_intake = intake;
        m_feeder = feeder;
        this.controller = controller;

        addRequirements(intake);
        
    }

    @Override
    public void initialize() {
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {

        if (controller.getRightTriggerAxis() > 0.5) {

            if (Clock.systemUTC().millis() != time)
                new BottomFeederToggleCommand(m_feeder, false).schedule();

            m_intake.armDown();
            m_intake.beltOn();


            time = Clock.systemUTC().millis();
        } else {
            m_intake.armUp();

            if (Clock.systemUTC().millis() - time > 1500) {
                m_intake.beltOff();
                if (Clock.systemUTC().millis() <= 1520)
                    new BottomFeederToggleCommand(m_feeder, false).schedule();
            }
            
        }

    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        m_intake.brakeMode(true);
        m_intake.armUpEnd();
        m_intake.beltOff();
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return false;
    }




}