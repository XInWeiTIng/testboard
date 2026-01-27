

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;

import edu.wpi.first.wpilibj.XboxController;


import com.ctre.phoenix6.hardware.TalonFX;
import com.revrobotics.spark.SparkMax;







public class Robot extends TimedRobot {
  private final TalonFX fxmotor1 = new TalonFX(6);
  private final TalonFX fxmotor2 = new TalonFX(16);
  private final TalonFX fxmotor3 = new TalonFX(8);
  private final TalonFX fxmotor4 = new TalonFX(0);
  private final SparkMax sparkMax1 = new SparkMax(9, null);
  private final SparkMax sparkMax2 = new SparkMax(11, null);
  private final SparkMax sparkMax3 = new SparkMax(21, null);
  private final SparkMax sparkMax4 = new SparkMax(2, null);
  private final XboxController controller = new XboxController(0);

  public Robot() {
    

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
    fxmotor3.set(speed);
    sparkMax1.set(speed);
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
