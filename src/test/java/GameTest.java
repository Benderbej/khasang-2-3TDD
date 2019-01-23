import org.junit.Assert;
import org.junit.Test;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

import static org.junit.Assert.*;

public class GameTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;
    Game game;

    @org.junit.Before
    public void setUp() throws Exception {
        game = new Game();
    }

    @org.junit.Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @org.junit.After
    public void tearDown() throws Exception {
    }

    @org.junit.After
    public void restoreStreams() {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }

    @Test
    public void isHitTest() throws UnsupportedEncodingException {//6 Хочу, чтобы появлялось сообщение: "потопили" или "промахнулись" при, соответственно, потоплении корабля, промахе, чтобы игрок сразу видел результат своего хода, если поле большое и визуально изменить его изменение сложно.
        game.getGamefield().initShip(new Point(4,3));
        int x1 = 5;
        int y1 = 5;
        String mockInputForUser = x1+" "+y1+"\n";
        game.shoot(new ByteArrayInputStream(mockInputForUser.getBytes(StandardCharsets.UTF_8.name())));
        if(game.isHit(new Point(x1, y1))){
            Assert.assertEquals(game.MISS_MESSAGE, game.getMessage());
        }
        Assert.assertEquals(game.MISS_MESSAGE, outContent.toString().replaceAll("(\\r|\\n)", ""));
        int x2 = 4; int y2 = 3;
        mockInputForUser = x1+" "+y1+"\n";
        game.shoot(new ByteArrayInputStream(mockInputForUser.getBytes(StandardCharsets.UTF_8.name())));
        if(game.isHit(new Point(x2, y2))){
            Assert.assertEquals(game.HIT_MESSAGE, game.getMessage());
        }
        Assert.assertEquals(game.MISS_MESSAGE + game.HIT_MESSAGE, outContent.toString().replaceAll("(\\r|\\n)", ""));
    }

}