package utils;

import enums.MenuOption;
import repositories.StudentRepository;

public class InputUtils {
    InputUtils() {
    }

    private static final class InstanceHolder {
        private static final InputUtils instance = new InputUtils();
    }

    public static InputUtils getInstance() {
        return InputUtils.InstanceHolder.instance;
    }

    public boolean isNumberEmpty(int num) {
        return num == 0;
    }

    public void displayMenu() {
        System.out.println("Welcome to the Console Program!");
        System.out.println("Choose an option:");

        for (MenuOption option : MenuOption.values()) {
            System.out.println(option.getOption() + ". " + option.getDescription());
        }

        System.out.print("Enter your choice: ");
    }
}
