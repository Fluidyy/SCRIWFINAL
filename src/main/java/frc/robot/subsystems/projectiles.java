package frc.robot.subsystems;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.revrobotics.CANSparkBase;
import com.revrobotics.CANSparkFlex;
import com.revrobotics.CANSparkLowLevel;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;
import com.ctre.phoenix6.configs.TalonFXConfigurator;
import com.ctre.phoenix6.controls.VelocityVoltage;
import com.ctre.phoenix6.hardware.TalonFX;
import edu.wpi.first.math.controller.PIDController;
// import frc.robot.subsystems.lookuptable.setpoint;
import frc.robot.Constants;
import frc.robot.subsystems.lookuptable.setpoint;

import com.revrobotics.CANSparkFlex;
import com.revrobotics.RelativeEncoder;


public class projectiles extends SubsystemBase{

    private Timer AutoTimer2 = new Timer();
    private CANSparkMax OutakeR = new CANSparkMax(11, MotorType.kBrushless); //bottom
    private CANSparkMax OutakeL = new CANSparkMax(12, MotorType.kBrushless);//top


    private PIDController wristpiPidController = new PIDController(0.03, 0, 0);
    private PIDController elevatorpidcController = new PIDController(0.05, 0, 0);


     private RelativeEncoder OutakeE = OutakeL.getEncoder();
     private final VelocityVoltage m_voltageVelocityLeft = new VelocityVoltage(0.0, 0.0, false, 0.0, 0, false, false, false);
    private final VelocityVoltage m_voltageVelocityRight = new VelocityVoltage(0.0, 0.0, false, 0.0, 0, false, false, false);

     


    

public projectiles(){}
@Override
public void periodic(){
    

}

public void setoutakeLO(double leftv, double rightv){
    OutakeL.set(leftv);
    OutakeR.set(rightv);
    
}

public void setShooterSetpoints(setpoint setpoints) {

    OutakeR.set(setpoints.shooterLeft);
    OutakeL.set(-setpoints.shooterRight);
    
}

// public boolean check(setpoint setpoint){
//     return OutakeE.getVelocity() == setpoint.shooterLeft;
// }

public void setoutakeTE(double speed){
    OutakeL.set(speed);
    OutakeR.set(speed);
    
}
public void setoutakeTEhigher(double speed){
    OutakeL.set(speed);
    OutakeR.set(speed*0.52);
}
public void setoutakeTErun(double speed){
    OutakeL.set(-speed);
    OutakeR.set(speed);
    
}
public void Unjam(){
    OutakeL.set(-.3);
    OutakeR.set(-.3);
    

}

// public double elevatorpid(double setpoint){
//     elevatorpidcController.setSetpoint(setpoint);
//     double move = elevatorpidcController.calculate(elevatorE.getPosition());
//     return move;

// }


// public Command elevatorcmd(double setpoint){
    
        
//     return new Command() {
//         @Override
//         public void initialize() {
//             // Initialization code, such as resetting encoders or PID controllers
//         }

//         @Override
//         public void execute() {
//             double speed =  elevatorpid(setpoint);// Assuming setpid() calculates the speed based on PID
//             elevator.set(speed);


//         }

//         @Override
//         public void end(boolean interrupted) {
//             elevator.set(0); // Stop the motor when the command ends or is interrupted
//         }

//         @Override
//         public boolean isFinished() {
//             return false ; // Check if the setpoint is reached
//         }
//     };
// }
// public boolean check(double setpoint){
//    return elevatorE.getPosition() >= setpoint-3 && elevatorE.getPosition() <= setpoint+3;
// }

// public Command wristcmd(double setpoint){
    
        
//     return new Command() {
//         @Override
//         public void initialize() {
//             // Initialization code, such as resetting encoders or PID controllers
//         }

//         @Override
//         public void execute() {
//             double speed = wristpid(setpoint); // Assuming setpid() calculates the speed based on PID
//             wrist.set(speed);


//         }

//         @Override
//         public void end(boolean interrupted) {
//             wrist.set(0); // Stop the motor when the command ends or is interrupted
//         }

//         @Override
//         public boolean isFinished() {
//             return wristE.getPosition() == setpoint ; // Check if the setpoint is reached
//         }
//     };
// }

public Command lookupCommand(double leftv, double rightv){
    
        
    return new Command() {
        @Override
        public void initialize() {
            // Initialization code, such as resetting encoders or PID controllers
        }

        @Override
        public void execute() {
            OutakeL.set(-leftv);
            OutakeR.set(rightv);


        }

        @Override
        public void end(boolean interrupted) {
           OutakeL.set(0); // Stop the motor when the command ends or is interrupted
           OutakeR.set(0);
        }

        @Override
        public boolean isFinished() {
            return OutakeE.getVelocity() == leftv; // Check if the setpoint is reached
        }
    };
}

//Used 
public Command Outtake(double speed){

        return run(
            

        () -> setoutakeTE(speed)
        );
    }
public Command OuttakeAMP(double speed){

        return run(
            

        () -> setoutakeTEhigher(speed)
        );
    }
    public Command OuttakeAmp(double speed){

        return run(
            

        () -> setoutakeTEhigher(speed)
        );
    }
    // public Command Outtake(fuckdouble speed){

    //     return run(
            

    //     () -> setoutakeTE(speed)
    //     );
    //}
public Command ProjectilesUnjam(double speed){

        return run(
            

        () -> Unjam()
        );
    }    
    
    public Command OtakeAU(double speed){
    
        
        return new Command() {
            @Override
            public void initialize() {
                AutoTimer2.reset();
                AutoTimer2.start();
                // Initialization code, such as resetting encoders or PID controllers
            }
    
            @Override
            public void execute() {
                // Assuming setpid() calculates the speed based on PID
                setoutakeTE(speed);

            
            
            }
    
            @Override
            public void end(boolean interrupted) {
                setoutakeTE(0);
                // Stop the motor when the command ends or is interrupted
            }
    
            @Override
            public boolean isFinished() {
                return AutoTimer2.get() > 1; // Check if the setpoint is reached
            }
        };}
}


