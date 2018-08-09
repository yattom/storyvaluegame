package jp.yattom.storyvaluegame;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class SprintTest {

    @Test
    void workOnを呼ぶまで結果は不明() {
        var backlog = new ProductBacklog();
        var sprint = new Sprint();
        sprint.plan(backlog);
        assertThrows(RuntimeException.class, () -> { sprint.isSuccessful(); });
    }

    @Test
    void やることのないスプリント() {
        var backlog = new ProductBacklog();
        var sprint = new Sprint();
        sprint.plan(backlog);
        sprint.workOn();
        assertTrue(sprint.isSuccessful());
    }

    @Test
    void スプリントプランニングでアイテムをプルする() {
        var backlog = new ProductBacklog();
        var item = new ProductBacklogItem("I01", Difficulty.C);
        backlog.addItem(item);
        var sprint = new Sprint();
        sprint.setResolutionRule(i -> false);
        sprint.plan(backlog);
        assertTrue(sprint.getSprintBacklog().contains(item));
    }

    @Test
    void アイテムを1つ完了できないスプリント() {
        var backlog = new ProductBacklog();
        backlog.addItem(new ProductBacklogItem("I01", Difficulty.C));
        var sprint = new Sprint();
        sprint.setResolutionRule(i -> false);
        sprint.plan(backlog);
        sprint.workOn();
        assertFalse(sprint.isSuccessful());
    }

    @Test
    void アイテムを1つ完了できるスプリント() {
        var backlog = new ProductBacklog();
        backlog.addItem(new ProductBacklogItem("I01", Difficulty.A));
        var sprint = new Sprint();
        sprint.setResolutionRule(i -> true);
        sprint.plan(backlog);
        sprint.workOn();
        assertTrue(sprint.isSuccessful());
    }
}
