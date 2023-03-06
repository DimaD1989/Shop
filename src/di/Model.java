package di;

import controllers.ShopController;
import model.domain.*;

public class Model {
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

    public static ShopControllers provideNoteController(
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

    public static ToysAdapter provideToysAdapter() {
        return new ToysAdapterImpl();
    }

    public static IToysFactory provideNotesFactory() {
        return new ToysFactory();
    }

    public static View provideView(
            ShopController controller,
            Promptable promptable,
            ToysAdapter notesAdapter,
            IToysFactory notesFactory
    ) {
        return new ShopView(controller, promptable, notesAdapter, notesFactory);
    }
}
