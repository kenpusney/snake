package net.kimleo.snake.model;

import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

public class SnakeNode {
    private SnakeNode previous = EMPTY;
    private SnakeNode next = EMPTY;

    public static final SnakeNode EMPTY = new SnakeNode(null);

    private Point position;

    public SnakeNode(Point position) {
        this.position = position;
    }

    public SnakeNode previous() {
        return previous;
    }

    public void previous(SnakeNode previous) {
        this.previous = previous;
    }

    public SnakeNode next() {
        return next;
    }

    public void next(SnakeNode next) {
        this.next = next;
    }

    public void appendBefore(SnakeNode node) {
        assert (node.previous() == EMPTY);
        this.next(node);
        node.previous(this);
        this.previous(EMPTY);
    }

    public void moveBefore(SnakeNode node) {
        if (this.previous != EMPTY) {
            this.previous().next(EMPTY);
        }
        this.appendBefore(node);
    }

    public boolean isEmptyNode() {
        return this == EMPTY;
    }

    public Point position() {
        return position;
    }

    public void position(Point position) {
        this.position = position;
    }
}
