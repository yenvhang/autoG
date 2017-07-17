package model.java;

/**
 * Created by creep on 2017/7/9.
 */
public enum JavaVisibility {
    PUBLIC("public "),
    PRIVATE("private "),
    PROTECTED("protected "),
    DEFAULT("");

    private String value;

    private JavaVisibility(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
