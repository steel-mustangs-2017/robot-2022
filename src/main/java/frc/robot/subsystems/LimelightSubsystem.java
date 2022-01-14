// turretMotor.configNominalOutputForward(.1);
// turretMotor.configNominalOutputReverse(-.1);

package frc.robot.subsystems;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class LimelightSubsystem extends SubsystemBase {
  /** Creates a new Limelight. */
  public LimelightSubsystem() {}

  private NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
    private boolean ledOn = true;
    private boolean  visionOn = true;

    

    public void  setLedOn() {
        if (!ledOn)
        {
            table.getEntry("ledMode").setNumber(3);
            ledOn = true;

        }
    }    
    public void  setLedOff() {
        if (ledOn)
        {
            table.getEntry("ledMode").setNumber(1);
            ledOn = false;

        }
    }    
    public void  setVision() {
        if (!visionOn)
        {
            table.getEntry("camMode").setNumber(0);
            visionOn = true;

        }
        
    }    
    public void  setCamera() {
        if (visionOn)
        {
            table.getEntry("camMode").setNumber(1);
            visionOn = false;

        }
    }    

    public double validTarget() {
        NetworkTableEntry tv = table.getEntry("tv");
        return tv.getDouble(0);
    }
    public double getAngle() {
        NetworkTableEntry tx = table.getEntry("tx");
        return tx.getDouble(0.0);       
    }
    public void printValues () {
        

        NetworkTableEntry tx = table.getEntry("tx");
        NetworkTableEntry ty = table.getEntry("ty");
        NetworkTableEntry ta = table.getEntry("ta");
        NetworkTableEntry tv = table.getEntry("tv");
        double x = tx.getDouble(0.0);
        double y = ty.getDouble(0.0);
        double area = ta.getDouble(0.0);
        double validTarget = tv.getDouble(0.0);
        //post to smart dashboard periodically
        SmartDashboard.putNumber("LimelightX", x);
        SmartDashboard.putNumber("LimelightY", y);
        SmartDashboard.putNumber("valid Target", validTarget);
        SmartDashboard.putNumber("LimelightArea", area);

          
    }


  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    setLedOn();
    setVision();
    NetworkTableEntry tx = table.getEntry("tx");
        NetworkTableEntry ty = table.getEntry("ty");
        NetworkTableEntry ta = table.getEntry("ta");
        NetworkTableEntry tv = table.getEntry("tv");
        double x = tx.getDouble(0.0);
        double y = ty.getDouble(0.0);
        double area = ta.getDouble(0.0);
        double validTarget = tv.getDouble(0.0);
        //post to smart dashboard periodically
        SmartDashboard.putNumber("LimelightX", x);
        SmartDashboard.putNumber("LimelightY", y);
        SmartDashboard.putNumber("valid Target", validTarget);
        SmartDashboard.putNumber("LimelightArea", area);
  }
}
