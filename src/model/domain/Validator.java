package model.domain;

import model.domain.entity.Toy;

public interface Validator {
    boolean validateToy(Toy toy);
}
