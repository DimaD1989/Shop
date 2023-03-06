package model.domain;

import model.domain.entity.Toy;

public interface IToyFactory {
    Toy getNewToy(String name, int quantity, int chancePercentage);
}
