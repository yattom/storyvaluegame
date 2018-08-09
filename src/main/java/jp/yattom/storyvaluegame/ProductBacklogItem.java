package jp.yattom.storyvaluegame;

public class ProductBacklogItem {
    public final String name;
    public final Difficulty difficulty;
    public State state;

    public ProductBacklogItem(String name, Difficulty difficulty) {
        this.name = name;
        this.difficulty = difficulty;
        this.state = State.TODO;
    }

    public void done() {
        state = State.DONE;
    }
}
