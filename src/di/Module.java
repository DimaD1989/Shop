package di;

import controllers.ShopController;
import model.data.FileStorage;
import model.data.RepositoryImpl;
import model.domain.*;
import views.*;


public class Module {

    public static Storage provideStorage() {
        return new FileStorage("toys.txt");
    }

    public static Mapper provideMapper() {
        return new MapperImpl();
    }

    public static Validator provideValidator() {
        return new MapperImpl();
    }

    public static Repository provideRepository(
            Storage storage,
            Mapper mapper
    ) {
        return new RepositoryImpl(mapper, storage);
    }

    public static ShopController provideNoteController(
            Repository repository,
            Validator validator
    ) {
        return new ShopController(repository, validator);
    }

    public static Logger provideLogger() {
        return new LoggerImpl("log.txt");
    }

    public static Promptable providePromptable(
            Logger logger
    ) {
        return new PromptableLoggingDecorator(
                new PromptableImpl(),
                logger
        );
    }

    public static IToysAdapter provideToysAdapter() {
        return new ToysAdapterImpl();
    }

    public static IToyFactory provideNotesFactory() {
        return new ToyFactory();
    }

    public static View provideView(
            ShopController controller,
            Promptable promptable,
            IToysAdapter notesAdapter,
            IToyFactory notesFactory
    ) {
        return new ShopView(controller, promptable, notesAdapter, notesFactory);
    }
}