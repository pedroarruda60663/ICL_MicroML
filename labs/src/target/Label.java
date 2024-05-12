package target;

public class Label {
    private static int count = 0;
    private String name;

    public Label() {
        this.name = "L" + count++;
    }

    @Override
    public String toString() {
        return name;
    }
}
