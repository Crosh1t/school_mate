package codereview.school_mate.enums;

public enum RoleEnum {

    ADMIN("admin"), STUDENT("student"), TEACHER("teacher");

    private final String name;

    RoleEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
