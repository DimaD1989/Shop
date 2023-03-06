package views;

import model.domain.Logger;

public class PromptableLoggingDecorator  implements  Promptable{
    private Promptable promptable;

    private Logger logger;

    public PromptableLoggingDecorator(Promptable promptable, Logger logger) {
        this.promptable = promptable;
        this.logger = logger;
    }

    @Override
    public String prompt(String message) {
        logger.log("prompt: " + message);
        String response = promptable.prompt(message);
        logger.log("response: " + response);
        return response;
    }

    @Override
    public void print(String message) {
        logger.log("print: " + message);
        promptable.print(message);
    }
}
