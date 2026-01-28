package frc.robot;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.wpilibj.TimedRobot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.controls.Follower;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.MotorAlignmentValue;
import com.revrobotics.PersistMode;
import com.revrobotics.ResetMode;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.config.SparkMaxConfig;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;


public class Robot extends TimedRobot {

  //TalonFX 馬達宣告
  private final TalonFX fxmotor1 = new TalonFX(16);
  private final TalonFX fxmotor2 = new TalonFX(18);
  private final TalonFX fxmotor3 = new TalonFX(3);
  private final TalonFX fxmotor4 = new TalonFX(5);

  //SparkMax 馬達控制器宣告
  private final SparkMax sparkMax1 = new SparkMax(9, MotorType.kBrushless);
  private final SparkMax sparkMax2 = new SparkMax(11, MotorType.kBrushless);
  private final SparkMax sparkMax3 = new SparkMax(21, MotorType.kBrushless);
  private final SparkMax sparkMax4 = new SparkMax(2, MotorType.kBrushless);

  //手柄宣告
  private final XboxController controller = new XboxController(0);

  public Robot() {

    //TalonFX 馬達初始化、設定follow
    fxmotor1.getConfigurator().apply(new TalonFXConfiguration());
    fxmotor2.getConfigurator().apply(new TalonFXConfiguration());
    fxmotor3.getConfigurator().apply(new TalonFXConfiguration());
    fxmotor4.getConfigurator().apply(new TalonFXConfiguration());

    fxmotor2.setControl(new Follower(fxmotor1.getDeviceID(), MotorAlignmentValue.Aligned));
    fxmotor3.setControl(new Follower(fxmotor1.getDeviceID(), MotorAlignmentValue.Aligned));
    fxmotor4.setControl(new Follower(fxmotor1.getDeviceID(), MotorAlignmentValue.Aligned));

    //SparkMax 馬達控制器初始化、設定follow
    sparkMax1.configure(
      new SparkMaxConfig().idleMode(IdleMode.kBrake),
      ResetMode.kNoResetSafeParameters,
      PersistMode.kNoPersistParameters
    );
    sparkMax2.configure(
      new SparkMaxConfig().idleMode(IdleMode.kBrake).follow(sparkMax1),
      ResetMode.kNoResetSafeParameters,
      PersistMode.kNoPersistParameters
    );
    sparkMax3.configure(
      new SparkMaxConfig().idleMode(IdleMode.kBrake).follow(sparkMax1),
      ResetMode.kNoResetSafeParameters,
      PersistMode.kNoPersistParameters
    );
    sparkMax4.configure(
      new SparkMaxConfig().idleMode(IdleMode.kBrake).follow(sparkMax1),
      ResetMode.kNoResetSafeParameters,
      PersistMode.kNoPersistParameters
    );
    
  }

 
  @Override
  public void robotPeriodic() {}

  
  @Override
  public void autonomousInit() {}

  
  @Override
  public void autonomousPeriodic() {}
  

  
  @Override
  public void teleopInit() {}

  //使用變數宣告
  int SpeedMode = 1;
  double speed = 0;
  double Maxspeed = 0.3;
  @Override
  public void teleopPeriodic() {

    //變速模塊
    if (controller.getAButton() && controller.getXButtonPressed()){
      SpeedMode++;
      if (SpeedMode > 3) SpeedMode = 1;
    }

    switch (SpeedMode) {
      case 1:
        Maxspeed = 0.3;
        break;
      case 2:
        Maxspeed = 0.6;
        break;
      case 3:
        Maxspeed = 1;
        break;
      default:
        Maxspeed = 0.3;
        break;
    }

    //顯示當前速度檔位
    SmartDashboard.putNumber("Speed Mode", SpeedMode);

    //搖桿數值讀取
    speed = controller.getLeftY() * Maxspeed;

    //防搖桿誤差
    speed = MathUtil.applyDeadband(speed, 0.03);
    
    //馬達輸出
    fxmotor1.set(speed);
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
