package di;

import controllers.ShopController;
import model.domain.Repository;
import model.domain.Storage;
import views.View;


public class Component {
    public Storage storage;
    public Repository repository;
    public ShopController noteController;
    public View shopView;

    public Component() {

        storage = Module.provideStorage();
        repository = Module.provideRepository(storage, Module.provideMapper());
        noteController = Module.provideNoteController(repository, Module.provideValidator());
        shopView = (View) Module.provideView(
                noteController,
                Module.providePromptable(
                        Module.provideLogger()
                ),
                Module.provideToysAdapter(),
                Module.provideNotesFactory()
        );
    }
}