package example.p11.predicate;

public enum ColorEnum {
    RED("red"),
    GREEN("green");
    private String name;
    ColorEnum(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
