package com.SWEProject.BackEnd.sim;

import com.SWEProject.BackEnd.addOn.Vector;
import lombok.Getter;

import java.util.Random;

@Getter
public class MovementSystem {
    private Vector currentPosition;
    private Direction direction;


    public MovementSystem(Vector currentPosition) {
        this.currentPosition = currentPosition;
        this.direction = Direction.Up;
    }

    public void move() {
        Random random = new Random();
        int percent = random.nextInt(1, 20);

        if (percent == 1) { // 5% 확률로 2현재 Direction으로 2칸 이동
            if (direction == Direction.Up) {
                currentPosition.y += 2;
            }
            if (direction == Direction.Right) {
                currentPosition.x += 2;
            }
            if (direction == Direction.Down) {
                currentPosition.y -= 2;
            }
            if (direction == Direction.Left) {
                currentPosition.x -= 2;
            }
        }

        if (percent > 1 && percent <= 19) { //90% 확률로 정상 동작
            if (direction == Direction.Up) {
                currentPosition.y += 1;
            }
            if (direction == Direction.Right) {
                currentPosition.x += 1;
            }
            if (direction == Direction.Down) {
                currentPosition.y -= 1;
            }
            if (direction == Direction.Left) {
                currentPosition.x -= 1;
            }
        }
//         나머지 5% 확률로 동작 X
    }

    public void turn() { //시계 방향으로 회전하는 기능
        this.direction = Direction.getAfterDirectionWithNow(direction);
    }

    //깊은 복사
    public void setPosition(Vector beforeMovePosition) {
        this.currentPosition.x = beforeMovePosition.x;
        this.currentPosition.y = beforeMovePosition.y;
        this.currentPosition.parent = beforeMovePosition.parent;
        this.currentPosition.neighbors = beforeMovePosition.neighbors;
    }
}

