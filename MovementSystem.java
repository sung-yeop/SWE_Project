public class MovementSystem {
    private Vector2 currentPosition;
    private Direction direction;




    public MovementSystem(){
        currentPosition = new Vector2();
    }

    public void Move(Vector2 intendedPosition){
        // TODO : 10퍼센트 확률로 오동작
        // TODO : 현재 방향과 다를경우 움직임 대신 회전동작
        currentPosition = intendedPosition;
    }

    public Vector2 GetCurrentPosition(){
        return currentPosition;
    }

    public void SetCurrentPosition(Vector2 currentPosition){
        this.currentPosition = currentPosition;
    }

    public Direction GetDirection(){
        return direction;
    }

}
