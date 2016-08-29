package net.kimleo.snake.model;

public class Snake {
    private SnakeNode head;
    private SnakeNode tail;

    private Direction heading;

    private int size = 1;

    public Snake() {
        head = new SnakeNode(new Point(0, 0));
        tail = new SnakeNode(new Point(0, 0));
        head.appendBefore(tail);
        heading = Direction.RIGHT;
    }

    public void move() {
        SnakeNode newHead = tail;
        SnakeNode newTail = tail.previous();

        newHead.moveBefore(head);
        newHead.position(nextPosition());
        head = newHead;
        tail = newTail;
    }

    public Point nextPosition() {
        return head.position().neighbourOn(heading);
    }

    public void eat() {
        SnakeNode newHead = new SnakeNode(nextPosition());
        newHead.appendBefore(head);
        head = newHead;
        size ++;
    }

    public SnakeNode head() {
        return head;
    }

    public SnakeNode tail() {
        return tail;
    }

    public int size() {
        return size;
    }

    public void heading(Direction direction) {
        heading = direction;
    }
}
