package com.SWEProject.BackEnd.sim;

import com.SWEProject.BackEnd.domain.Vector;
import org.junit.jupiter.api.BeforeEach;

class SimTest {

    private Sim sim;

    @BeforeEach
    void setSim() {
        sim = new Sim(Vector.of(2, 3));
    }

//    @Test
//    void TURN_테스트() {
//        sim.directionSetting(Vector.of(3, 3));
//        sim.move(); //오른쪽 이동
//
//        assertThat(sim.getPosition().equals(Vector.of(3, 3)));
//        assertThat(sim.getDirection()).isEqualTo(Direction.Right);
//
//        sim.directionSetting(Vector.of(3, 2));//아래로 이동
//        assertThat(sim.getPosition().equals(Vector.of(3, 2)));
//        assertThat(sim.getDirection()).isEqualTo(Direction.Down);
//
//    }

}