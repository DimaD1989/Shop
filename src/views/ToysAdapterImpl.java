package views;

import model.domain.entity.Toy;

public class ToysAdapterImpl  implements  IToysAdapter{

    @Override
    public String getView(Toy toy) {
        return toy.toString();
    }
}
