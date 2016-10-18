package net.kimleo.snake.context;

import javafx.scene.canvas.GraphicsContext;
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
        Point position = node.position();
        int offSetX = size * position.x;
        int offSetY = size * position.y;
        if (offSetX + size < weight && offSetY + size < height) {
            context.fillRect(offSetX, offSetY, size, size);
        } else {
            throw new RuntimeException("Out of bound");
        }
    }

    public void draw(Snake snake) {

    }
}
