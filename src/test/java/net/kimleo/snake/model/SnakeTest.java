package net.kimleo.snake.model;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class SnakeTest {

    private Snake snake;

    @Before
    public void setUp() throws Exception {
        snake = new Snake();
    }

    @Test
    public void shouldMoveAsExpected() throws Exception {
        snake.move();

        assertThat(snake.head().position(), is(new Point(1, 0)));

        snake.heading(Direction.UP);
        snake.move();
        assertThat(snake.head().position(), is(new Point(1, 1)));
    }

    @Test
    public void shouldIncreaseLengthWhenEat() throws Exception {
        assertThat(snake.size(), is(1));

        snake.eat();

        assertThat(snake.size(), is(2));
        assertThat(snake.head().position(), is(new Point(1, 0)));
        assertThat(snake.tail().position(), is(new Point(0, 0)));
    }
}