package pe.eyenoon.ourlunch.entity.enums;

public enum BoardType {
    question("질문"),
    answer("답변");

    private String value;

    BoardType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
