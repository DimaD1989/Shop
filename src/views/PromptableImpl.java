package views;

import java.util.Scanner;

public class PromptableImpl implements  Promptable {
    @Override
    public String prompt(String message) {
        Scanner in = new Scanner(System.in);
        System.out.print(message);
        return in.nextLine();
    }

    @Override
    public void print(String message) {
        System.out.print(message);
    }
}
