package codereview.school_mate.enums;

public enum DayOfWeekEnum {
    MONDAY("Понедельник"),
    TUESDAY("Вторник"),
    WEDNESDAY("Среда"),
    THURSDAY("Четверг"),
    FRIDAY("Пятница"),
    SATURDAY("Суббота"),
    SUNDAY("Воскресенье");

    private final String name;

    DayOfWeekEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
