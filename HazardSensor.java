import java.util.ArrayList;
import java.util.List;
public class HazardSensor {
    private List<Vector2> hazardList = new ArrayList<Vector2>();

    public void AddHazard(Vector2 position){
        hazardList.add(position);
    }

    public boolean IsHazard(Vector2 position , Direction dir){
         Vector2 compareVector = new Vector2(position.x , position.y);
        switch(dir){
            case Up:
                compareVector.y += 1;
                break;
            case Down:
                compareVector.y -= 1;
                break;
            case Left:
                compareVector.x -= 1;
                break;
            case Right:
                compareVector.x += 1;
                break;
        }
        if(!hazardList.isEmpty()) {
            for (Vector2 hazard : hazardList) {
                if (compareVector.equals(hazard))
                    return true;
            }
        }
        return false;
    }
}
