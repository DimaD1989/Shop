package di;

import model.domain.Repository;
import model.domain.ShopControllers;
import model.domain.Storage;

import javax.swing.text.View;

public class Component {
    public Storage storage;
    public Repository repository;
    public ShopControllers noteController;
    public View shopView;

    public Component() {

        storage = Module.provideStorage();
        repository = Module.provideRepository(storage, Module.provideMapper());
        noteController = Module.provideNoteController(repository, Module.provideValidator());
        shopView = Module.provideView(
                noteController,
                Module.providePromptable(
                        Module.provideLogger()
                ),
                Module.provideToysAdapter(),
                Module.provideNotesFactory()
        );
    }
}
