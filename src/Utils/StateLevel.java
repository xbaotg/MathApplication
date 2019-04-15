package Utils;

public enum StateLevel {

    LEVEL1("Cấp Độ Một"),
    LEVEL2("Cấp Độ Hai"),
    LEVEL3("Cấp Độ Ba"),
    LEVEL4("Cấp Độ Bốn"),
    LEVEL5("Cấp Độ Năm"),
    LEVEL6("Cấp Độ Sáu"),
    DIENDAU("Điền Dấu Nhanh");


    private String s;

    StateLevel(String s) {
        this.s = s;
    }

    public String getTitle() {
        return s;
    }


}
