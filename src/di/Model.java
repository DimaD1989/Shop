package di;

public class Model {
    public static IStorage provideStorage() {
        return new FileStorage("toys.txt");
    }

    public static IMapper provideMapper() {
        return new MapperIml();
    }

    public static IValidator provideValidator() {
        return new MapperIml();
    }

    public static IRepository provideRepository(
            IStorage storage,
            IMapper mapper
    ) {
        return new RepositoryImpl(mapper, storage);
    }

    public static IShopController provideNoteController(
            IRepository repository,
            IValidator validator
    ) {
        return new ShopController(repository, validator);
    }

    public static ILogger provideLogger() {
        return new LoggerImpl("log.txt");
    }

    public static Promptable providePromptable(
            ILogger logger
    ) {
        return new PromptableLoggingDecorator(
                new PromptableImpl(),
                logger
        );
    }

    public static IToysAdapter provideToysAdapter() {
        return new ToysAdapterImpl();
    }

    public static IToysFactory provideNotesFactory() {
        return new ToysFactory();
    }

    public static IView provideView(
            IShopController controller,
            Promptable promptable,
            IToysAdapter notesAdapter,
            IToysFactory notesFactory
    ) {
        return new ShopView(controller, promptable, notesAdapter, notesFactory);
    }
}
