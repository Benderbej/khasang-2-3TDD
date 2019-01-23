import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class GameFieldTest {

    GameField gameField;

    @Before
    public void setUp(){
        gameField = new GameField(10);
    }

    @Test
    public void getSize() throws Exception {
    }

    @Test
    public void getPoint(){
        int x = 5;
        int y = 3;
        Point point = gameField.getPoint(x,y);
        Assert.assertEquals(point.getX(),x);
        Assert.assertEquals(point.getY(),y);
    }

    @Test
    public void gameFieldSize(){
        assertEquals(10, gameField.getSize());
    }

    @Test
    public void initPoints(){
        gameField.initPoints();
        for (int i = 0; i < gameField.getSize(); i++) {
            for (int j = 0; j < gameField.getSize(); j++) {
                Assert.assertEquals('.', gameField.getPoint(i,j).getContent());//
            }
        }
    }

    @Test
    public void initShips(){//3 Хочу, чтобы на поле располагался 1 однопалубный корабль, расположение которого не видно игроку, чтобы отслеживать игровую динамику
        Ship ship = new Ship(new Point(5,5));
        Point position = new Point(5,4);
        ship.setPosition(position);
        int x = ship.getPosition().getX();
        int y = ship.getPosition().getY();
        gameField.getPoint(x,y).setContent('.');
        Assert.assertEquals('.', gameField.getPoint(x,y).getContent());//допустим корабли только одноклеточные - одноклеточный корабль должен существовать только "в памяти игры"
    }

    @Test
    public void updatePoint() throws Exception {//5 Хочу, чтобы при попадании в корабль на поле отображался "х", при промахе "*", чтобы игрок мог визуально увидеть результат своего хода и отличить промах от попадания
        gameField.initPoints();
        gameField.updatePoint(new Point(5,5), false);
        Assert.assertEquals(gameField.getPoint(5,5).getContent(), '*');
        gameField.updatePoint(new Point(4,4), true);
        Assert.assertEquals(gameField.getPoint(4,4).getContent(), 'X');
    }
}