package enums;

public enum MenuOption {
    GET_ALL("1", "Get all Student"),
    INSERT("2", "Insert new Student"),
    UPDATE("3", "Update Student"),
    DELETE("4", "Delete Student"),
    SORT("5", "Sort Student List"),
    EXPORT("6", "Export to CSV"),
    IMPORT("7", "Import from CSV"),
    EXIT("8", "Exit");

    private final String option;
    private final String description;

    MenuOption(String option, String description) {
        this.option = option;
        this.description = description;
    }

    public String getOption() {
        return option;
    }
    public String getDescription() {
        return description;
    }
}
