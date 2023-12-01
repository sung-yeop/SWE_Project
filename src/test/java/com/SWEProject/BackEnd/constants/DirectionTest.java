package com.SWEProject.BackEnd.constants;

import com.SWEProject.BackEnd.sim.Direction;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class DirectionTest {

    Direction direction;

    @Test
    void 턴_디렉션_테스트() {
        Assertions.assertThat(Direction.getAfterDirectionWithNow(Direction.Up)).isEqualTo(Direction.Right);
        Assertions.assertThat(Direction.getAfterDirectionWithNow(Direction.Right)).isEqualTo(Direction.Down);
        Assertions.assertThat(Direction.getAfterDirectionWithNow(Direction.Down)).isEqualTo(Direction.Left);
        Assertions.assertThat(Direction.getAfterDirectionWithNow(Direction.Left)).isEqualTo(Direction.Up);

    }

}