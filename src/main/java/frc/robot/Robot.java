

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;

import edu.wpi.first.wpilibj.XboxController;

import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.hardware.TalonFX;
//import com.revrobotics.spark.SparkMax;
//import com.revrobotics.spark.SparkLowLevel.MotorType;







public class Robot extends TimedRobot {
  private final TalonFX fxmotor1 = new TalonFX(8);
  private final TalonFX fxmotor2 = new TalonFX(18);
  //private final TalonFX fxmotor3 = new TalonFX(8);
  //private final TalonFX fxmotor4 = new TalonFX(0);
  //private final SparkMax sparkMax1 = new SparkMax(9, MotorType.kBrushless);
  //private final SparkMax sparkMax2 = new SparkMax(11, MotorType.kBrushless);
  //private final SparkMax sparkMax3 = new SparkMax(21, MotorType.kBrushless);
  //private final SparkMax sparkMax4 = new SparkMax(2, MotorType.kBrushless);
  private final XboxController controller = new XboxController(0);

  public Robot() {
    fxmotor1.getConfigurator().apply(new TalonFXConfiguration());
    fxmotor2.getConfigurator().apply(new TalonFXConfiguration());
    //fxmotor3.getConfigurator().apply(new TalonFXConfiguration());
    //fxmotor4.getConfigurator().apply(new TalonFXConfiguration());
    
  }

 
  @Override
  public void robotPeriodic() {}

  
  @Override
  public void autonomousInit() {
    
  }

  
  @Override
  public void autonomousPeriodic() {}
  

  
  @Override
  public void teleopInit() {
   
  }
  boolean isHighSpeed = false;
  double speed = 0;
  @Override
  public void teleopPeriodic() {
    if(controller.getAButton() && controller.getXButtonPressed()){
      isHighSpeed = !isHighSpeed;
  }
    if(isHighSpeed){
      speed = controller.getLeftY() * 0.6;
    }else{
      speed = controller.getLeftY() * 0.3;
    }
    if (Math.abs(speed) <= 0.03) {
      speed = 0;
    }
    
    fxmotor1.set(speed);
    fxmotor2.set(speed);
    //fxmotor3.set(speed);
    //sparkMax1.set(speed);
  }

 
  @Override
  public void disabledInit() {}

 
  @Override
  public void disabledPeriodic() {}

 
  @Override
  public void testInit() {}

 
  @Override
  public void testPeriodic() {}

  
  @Override
  public void simulationInit() {}

 
  @Override
  public void simulationPeriodic() {}
}
