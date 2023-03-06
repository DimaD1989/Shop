package views;

import controllers.ShopController;
import model.domain.IToyFactory;
import model.domain.entity.Toy;

import java.util.Arrays;
import java.util.List;

public class ShopView implements  View{
    private final ShopController noteController;

    private final Promptable promptable;

    private final IToysAdapter toysAdapter;

    private final IToyFactory toysFactory;

    public ShopView(
            ShopController noteController,
            Promptable promptable,
            IToysAdapter toysAdapter,
            IToyFactory toysFactory
    ) {
        this.noteController = noteController;
        this.promptable = promptable;
        this.toysAdapter = toysAdapter;
        this.toysFactory = toysFactory;
    }

    public void run() {
        Intentions intention = Intentions.NONE;

        while (true) {
            String command = promptable.prompt("\nВведите команду: " +
                    Arrays.toString(Intentions.values()) + " ");
            intention = Intentions.valueOf(command.toUpperCase());
            if (intention == Intentions.EXIT) return;
            try {
                switch (intention) {
                    case CREATE:
                        noteController.saveToy(createToy());
                        break;
                    case READ:
                        int id = Integer.parseInt(promptable.prompt("Идентификатор игрушки: "));
                        Toy toy = noteController.readToy(id);
                        promptable.print(toy.toString());
                        break;
                    case LIST:
                        List<Toy> lst = noteController.readList();
                        lst.forEach(i -> promptable.print(toysAdapter.getView(i) + "\n"));
                        break;
                    case UPDATE:
                        int numId = Integer.parseInt(promptable.prompt("Какую игрушку редактировать? Введите номер ID: "));
                        noteController.idPresenceValidation(numId);
                        noteController.updateToy(numId, createToy());
                        break;
                    case DELETE:
                        int deleteId = Integer.parseInt(promptable.prompt("Какою игрушку удалить? Введите номер ID: "));
                        noteController.idPresenceValidation(deleteId);
                        noteController.deleteToy(deleteId);
                        noteController.readList()
                                .forEach(i -> promptable.print(toysAdapter.getView(i) + "\n"));
                        break;
                    case PICK:
                        int pickId = Integer.parseInt(promptable.prompt("Какою игрушку разыграть? Введите номер ID: "));
                        noteController.pickToy(pickId).forEach(i -> promptable.print(toysAdapter.getView(i) + "\n"));
                        break;
                    case POP:
                        noteController.popToy().forEach(i -> promptable.print(toysAdapter.getView(i) + "\n"));
                        break;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private Toy createToy() {
        String name = promptable.prompt("Введите наименование: ");
        int quantity = Integer.parseInt(promptable.prompt("Введите количество: "));
        int chancePercentage = Integer.parseInt(promptable.prompt("Введите процент шанса: "));
        return toysFactory.getNewToy(name, quantity, chancePercentage);
    }
}
