package com.sw.project.domain;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MapInitTest {

    @Autowired
    private MapInitService mapInitService;

    @Test
    public void INIT(){
        //given
        MapInit mapInit = new MapInit();
        String result = mapInit.createMap("(4 5)");

        //when
        mapInitService.save(mapInit);
        MapInit find = mapInitService.find(mapInit.getId());

        System.out.println(result);

    }


}