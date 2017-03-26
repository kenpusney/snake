package net.kimleo.snake.model;

import net.kimleo.snake.context.GameContext;

import java.util.LinkedHashSet;
import java.util.Set;

public class Snake {
    private SnakeNode head;
    private SnakeNode tail;

    private Set<Point> ownedPoints = new LinkedHashSet<>();

    private Direction heading;

    public Snake() {
        Point originalPoint = new Point(0, 0);
        head = new SnakeNode(originalPoint);
        tail = new SnakeNode(originalPoint);
        ownedPoints.add(originalPoint);
        head.appendBefore(tail);
        heading = Direction.RIGHT;
    }

    public void move() {
        SnakeNode newHead = tail;
        SnakeNode newTail = tail.previous();

        Point position = nextPosition();
        if (ownedPoints.contains(position)) {
            throw new RuntimeException("Traffic Accident at " + position);
        }

        ownedPoints.remove(tail.position());
        newHead.moveBefore(head);
        newHead.position(position);
        head = newHead;
        tail = newTail;
        ownedPoints.add(head.position());
    }

    public Point nextPosition() {
        return head.position().neighbourOn(heading);
    }

    public void eat() {
        SnakeNode newHead = new SnakeNode(nextPosition());
        newHead.appendBefore(head);
        head = newHead;
        ownedPoints.add(head.position());
    }

    public SnakeNode head() {
        return head;
    }

    public SnakeNode tail() {
        return tail;
    }

    public int size() {
        return ownedPoints.size();
    }

    public void heading(Direction direction) {
        heading = direction;
    }

    public void draw(GameContext context) {
        context.draw(this);
    }
}
