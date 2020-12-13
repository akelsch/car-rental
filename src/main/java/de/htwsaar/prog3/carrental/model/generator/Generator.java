package de.htwsaar.prog3.carrental.model.generator;

import de.htwsaar.prog3.carrental.model.BaseEntity;

import java.util.ArrayList;
import java.util.List;

public abstract class Generator<T extends BaseEntity> {
    public abstract T generate();

    public List<T> generate(final int quantity) {
        final List<T> list = new ArrayList<T>();
        for (int i = 0; i < quantity; i++) {
            list.add(generate());
        }
        return list;
    }
}
