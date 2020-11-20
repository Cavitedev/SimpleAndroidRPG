package com.example.simplerpg.infrastructure.abilities;

import com.example.simplerpg.domain.core.SerializerFlywheel;
import com.example.simplerpg.domain.models.Ability;


public class AbilitySerializerFlywheel extends SerializerFlywheel<Ability,Integer> {


    public AbilitySerializerFlywheel(String json) {
        super(json);
    }
}
