package enums;

public enum SortStudentBy {
    ID("0"),
    POINT("1");

    private final String value;

    private SortStudentBy(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
