package enums;

public enum MenuOption {
    GET_ALL("1"),
    INSERT("2"),
    UPDATE("3"),
    DELETE("4"),
    SORT("5"),
    EXIT("6");
    private final String type;

    MenuOption(String type) {
        this.type = type;
    }

    public String getProgram() {
        return type;
    }
}
