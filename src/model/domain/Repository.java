package model.domain;

public interface Repository {
    List<Toy> getAllToys();

    int createToy(Toy toy);

    void updateToy(Toy toy);

    void deleteToy(Toy toy);

    void saveToy(Toy toy);
}
