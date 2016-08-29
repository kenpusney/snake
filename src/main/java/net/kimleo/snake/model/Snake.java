package net.kimleo.snake.model;

public class Snake {
    SnakeNode head;
    SnakeNode tail;

    Direction headDirection;

    Snake() {
        head = new SnakeNode(new Point(0, 0));
        tail = new SnakeNode(new Point(0, 0));
        headDirection = Direction.RIGHT;
    }

}
