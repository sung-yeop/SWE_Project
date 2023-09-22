package com.sw.project.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@RequiredArgsConstructor
@Getter
public abstract class Coordinate {

    @Id
    @GeneratedValue
    @Column(name = "coordiante_id")
    private Long id;

    public int x;
    public int y;
}
