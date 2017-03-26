package net.kimleo.snake.context;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import net.kimleo.snake.model.Point;
import net.kimleo.snake.model.Snake;
import net.kimleo.snake.model.SnakeNode;

public class GameContext {
    GraphicsContext context;
    final int weight;
    final int height;
    final int size;
    public GameContext(int weight, int height, int size, GraphicsContext context) {
        this.context = context;
        this.weight = weight;
        this.height = height;
        this.size = size;
    }

    public void draw(SnakeNode node) {
        context.setFill(Color.BLUE);
        context.setStroke(Color.WHITE);
        context.setLineWidth(2);
        Point position = node.position();
        int offSetX = size * position.x;
        int offSetY = size * position.y;
        if (offSetX + size < weight && offSetY + size < height) {
            context.fillRect(offSetX, offSetY, size, size);
            context.strokeRect(offSetX, offSetY, size, size);
        } else {
            throw new RuntimeException("Out of bound");
        }
    }

    public void draw(Snake snake) {
        context.clearRect(0, 0, weight, height);

        SnakeNode node = snake.head();

        while(!node.isEmptyNode()) {
            draw(node);
            node = node.next();
        }
    }
}
