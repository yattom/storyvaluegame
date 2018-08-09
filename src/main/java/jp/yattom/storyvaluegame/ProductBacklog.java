package jp.yattom.storyvaluegame;

public class ProductBacklog {
    private ProductBacklogItem item;

    public void addItem(ProductBacklogItem item) {
        this.item = item;
    }

    public ProductBacklogItem getFirst() {
        return item;
    }
}
