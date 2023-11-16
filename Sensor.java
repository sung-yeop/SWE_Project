import java.util.ArrayList;
import java.util.List;

public class Sensor {
        private List<Vector2> hiddenHazards;
        private List<Vector2> hiddenColorblobs;


        public Sensor(){
            hiddenHazards = new ArrayList<Vector2>();
            hiddenColorblobs = new ArrayList<Vector2>();
        }

        public void AddHazard(Vector2 position){
            hiddenHazards.add(position);
        }
        public void AddSpot(Vector2 position) { hiddenColorblobs.add(position);}
        public Vector2 GetHazardSensor(Vector2 position , Direction dir){
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
            if(!hiddenHazards.isEmpty()) {
                for (Vector2 hazard : hiddenHazards) {
                    if (compareVector.equals(hazard))
                        return hazard;
                }
            }
            return null;
        }

        public Vector2 GetColorblobSensor(Vector2 position){
            int x = position.x;
            int y = position.y;
            Vector2 compareVector = new Vector2(x,y);
            
            if(!hiddenColorblobs.isEmpty()){
                compareVector.x = x;
                compareVector.y = y+1; // 상
                for (Vector2 colorblob : hiddenColorblobs) {
                    if (compareVector.equals(colorblob))
                        return colorblob;
                }
                compareVector.x = x;
                compareVector.y = y-1; // 하
                for (Vector2 colorblob : hiddenColorblobs) {
                    if (compareVector.equals(colorblob))
                        return colorblob;
                }
                compareVector.x = x-1;
                compareVector.y = y; // 좌
                for (Vector2 colorblob : hiddenColorblobs) {
                    if (compareVector.equals(colorblob))
                        return colorblob;
                }
                compareVector.x = x+1;
                compareVector.y = y; // 우
                for (Vector2 colorblob : hiddenColorblobs) {
                    if (compareVector.equals(colorblob))
                        return colorblob;
                }
            }
            return null;
            
        }


        public boolean GetPositioningSensor(Vector2 currentPosition , Vector2 intendedPosition){
            return currentPosition != intendedPosition;
        }
    }
