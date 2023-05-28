package enums;

public enum SortType {
    ASC("0"),
    DESC("1");

    private String value;

    private SortType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
