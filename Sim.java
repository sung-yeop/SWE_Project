import com.sun.nio.sctp.SendFailedNotification;

public class Sim {
    private Sensor sensor;
    private MovementSystem movementSystem;


    public Sim(){
        sensor = new Sensor();
        movementSystem = new MovementSystem();
    }



    public void Move(Vector2 intendedPosition){
        movementSystem.Move(intendedPosition);
    }

    public Vector2 CheckHazard(){
        Vector2 position = movementSystem.GetCurrentPosition();
        Direction direction = movementSystem.GetDirection();

        return sensor.GetHazardSensor(position,direction);
    }
    public Vector2 CheckColorblob(){
        Vector2 position = movementSystem.GetCurrentPosition();

        return sensor.GetColorblobSensor(position);
    }

    public boolean CheckPosition(Vector2 intendedPosition){

        Vector2 currentPosition = movementSystem.GetCurrentPosition();

        return sensor.GetPositioningSensor(currentPosition , intendedPosition);
    }
}
