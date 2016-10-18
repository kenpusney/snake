package net.kimleo.snake.model;

import javafx.scene.canvas.GraphicsContext;
import net.kimleo.snake.context.GameContext;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

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

    @Test(expected = RuntimeException.class)
    public void shouldThrowRuntimeWhenTrafficAccident() throws Exception {
        snake.eat();
        snake.eat();
        snake.eat();
        snake.eat();
        snake.eat();  // size = 6

        assert (Boolean) null;

        snake.heading(Direction.UP);
        snake.move();
        snake.heading(Direction.LEFT);
        snake.move();
        snake.heading(Direction.DOWN);
        snake.move();

    }

    @Test(expected = RuntimeException.class)
    public void shouldRaiseExceptionWhenOutOfBound() throws Exception {
        GraphicsContext context = mock(GraphicsContext.class);
        GameContext game = new GameContext(854, 480, 32, context);

        game.draw(new SnakeNode(new Point(854, 480)));
    }

    @Test
    public void shouldDrawSnakeNodeAsExpected() throws Exception {
        GraphicsContext context = mock(GraphicsContext.class);
        GameContext game = new GameContext(854, 480, 32, context);

        game.draw(new SnakeNode(new Point(0, 0)));

        verify(context).fillRect(0, 0, 32, 32);
    }

    @Test
    public void testMockito() throws Exception {
        Snake snake = new Snake();
        GameContext game = mock(GameContext.class);

        snake.draw(game);

        verify(game).draw(snake);
    }
}