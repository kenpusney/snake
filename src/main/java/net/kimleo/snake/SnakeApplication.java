package net.kimleo.snake;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import net.kimleo.snake.context.GameContext;
import net.kimleo.snake.model.Direction;
import net.kimleo.snake.model.Snake;

import java.util.HashMap;

public class SnakeApplication extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Canvas canvas = new Canvas(100,100);

        GraphicsContext graphicsContext2D = canvas.getGraphicsContext2D();

        GameContext gameContext = new GameContext(100, 100, 10, graphicsContext2D);

        Group root = new Group();
        root.getChildren().add(canvas);

        HashMap<KeyCode, Direction> actions = new HashMap<KeyCode, Direction>() {{
                put(KeyCode.UP, Direction.UP);
                put(KeyCode.DOWN, Direction.DOWN);
                put(KeyCode.LEFT, Direction.LEFT);
                put(KeyCode.RIGHT, Direction.RIGHT);
            }};

        Snake snake = new Snake();
        snake.move();
        gameContext.draw(snake);
        Scene scene = new Scene(root);

        Thread thread = new Thread(() -> {
            while (true) {
                snake.move();
                try {
                    Thread.sleep(1000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                gameContext.draw(snake);
            }
        });

        scene.setOnKeyPressed(event -> {
            KeyCode code = event.getCode();
            switch (code) {
                case LEFT:
                case RIGHT:
                case UP:
                case DOWN:
                    snake.heading(actions.get(code));
                    break;
            }
        });

        primaryStage.setTitle("Snake");
        primaryStage.setScene(scene);
        primaryStage.show();
        thread.start();
    }



}
