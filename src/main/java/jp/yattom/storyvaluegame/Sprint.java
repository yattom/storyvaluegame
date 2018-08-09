package jp.yattom.storyvaluegame;

import java.util.ArrayList;
import java.util.Collection;

public class Sprint {
    private boolean end;
    private Collection<ProductBacklogItem> sprintBacklog;
    private PBIResolution resolution;

    public Sprint() {
        sprintBacklog = new ArrayList<>();
    }

    public boolean isSuccessful() {
        if (!end) {
            throw new IllegalStateException("Sprint is not end");
        }
        return sprintBacklog.stream().allMatch((ProductBacklogItem item) -> { return item.state == State.DONE; });
    }

    public void plan(ProductBacklog backlog) {
        var first = backlog.getFirst();
        if (first != null) {
            sprintBacklog.add(first);
        }
    }

    public Collection<ProductBacklogItem> getSprintBacklog() {
        return sprintBacklog;
    }

    public void workOn() {
        for (var item: sprintBacklog) {
           if(resolution.method(item)) {
               item.done();
           }
       }
        end = true;
    }

    public void setResolutionRule(PBIResolution resolution) {
        this.resolution = resolution;
    }
}
