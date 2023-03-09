package model.data;

import model.domain.Mapper;
import model.domain.Repository;
import model.domain.Storage;
import model.domain.entity.Toy;

import java.util.ArrayList;
import java.util.List;

public class RepositoryImpl implements Repository {
    private final Mapper mapper;
    private final Storage storage;

    public RepositoryImpl(Mapper mapper, Storage storage) {
        this.mapper = mapper;
        this.storage = storage;
    }

    @Override
    public List<Toy> getAllToys() {
        List<String> lines = storage.readAllLines();
        List<Toy> toys = new ArrayList<>();
        for (String line : lines) {
            toys.add(mapper.map(line));
        }
        return toys;
    }

    @Override
    public int createToy(Toy toy) {
        List<Toy> toys = getAllToys();
        int max = 0;
        for (Toy item : toys) {
            int id = item.getId();
            if (max < id) {
                max = id;
            }
        }
        int newId = max + 1;
        toy.setId(newId);
        toys.add(toy);
        writeDown(toys);
        return newId;
    }

    @Override
    public void updateToy(Toy toy) {
        List<Toy> toys = getAllToys();
        Toy target = toys.stream().filter(i -> i.getId() == toy.getId()).findFirst().get();
        target.setName(toy.getName());
        target.setQuantity(toy.getQuantity());
        target.setChancePercentage(toy.getChancePercentage());
        writeDown(toys);
    }

    @Override
    public void deleteToy(Toy toy) {
        List<Toy> toys = getAllToys();
        Toy target = toys.stream().filter(i -> i.getId() == toy.getId()).findFirst().get();
        toys.remove(target);
        writeDown(toys);
    }

    @Override
    public void saveToy(Toy toy) {
        String fileName = toy.getName() + ".txt";
        System.out.println("игрушка сохранена по именем " + fileName);
        Storage toyStorage = new FileStorage(fileName);
        List<String> lines = new ArrayList<String>();
        lines.add(mapper.map(toy));
        toyStorage.saveAllLines(lines);
    }

    private void writeDown(List<Toy> toys) {
        List<String> lines = new ArrayList<>();
        for (Toy item : toys) {
            lines.add(mapper.map(item));
        }
        storage.saveAllLines(lines);
    }
}
