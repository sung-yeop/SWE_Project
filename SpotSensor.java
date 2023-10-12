import java.util.ArrayList;
import java.util.List;
public class SpotSensor {
    private List<Vector2> spotList = new ArrayList<Vector2>();

    public void AddHazard(Vector2 position){
        spotList.add(position);
    }
}
