package model.domain.entity;

public class Toy {
    private int id = -1;
    private String name;
    private int quantity = 0;
    private int chancePercentage = 0;

    public Toy(String name, int quantity, int chancePercentage) {
        this.name = name;
        this.quantity = quantity;
        this.chancePercentage = chancePercentage;
    }

    public Toy(int id, String name, int quantity, int chancePercentage) {
        this(name, quantity, chancePercentage);
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getChancePercentage() {
        return chancePercentage;
    }

    public void setChancePercentage(int chancePercentage) {
        this.chancePercentage = chancePercentage;
    }


    @Override
    public String toString() {
        return "id: " + id + " Наименование: " + name + " Количество: " + quantity + " Шанс: " + chancePercentage + "%";
    }
}
