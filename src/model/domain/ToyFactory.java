package model.domain;

import model.domain.entity.Toy;

public class ToyFactory implements  IToyFactory{
    @Override
    public Toy getNewToy(String name, int quantity, int chancePercentage) {
        return new Toy(
                name,
                quantity,
                chancePercentage);
    }
}
