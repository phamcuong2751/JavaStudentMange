package utils;

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
}
