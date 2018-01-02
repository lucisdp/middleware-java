package classifier;

public enum Label {
    POSITIVE(1), NEGATIVE(-1);

    private final int id;
    Label(int id) { this.id = id; }
    public int getValue() { return id; }
}
