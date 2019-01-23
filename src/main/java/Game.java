import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Scanner;


public class Game {

    public static final String HIT_MESSAGE = "потопили";
    public static final String MISS_MESSAGE = "промахнулись";
    public static final String VIC_MESSAGE = "победа";
    private GameField gameField;
    private String message;

    public static void main(String[] args) {
        Game game = new Game();
        game.startGame();
    }

    Game(){
        gameField = new GameField(10);
        gameField.initPoints();
        message = "формат хода 'X X' где X - число от 1 до 10";
        reportMessage();
    }

    public void startGame(){
        int turnNum = 0;
        InputStream inputStream = System.in;
        Boolean isGaming = true;
        getGamefield().renderGameField();
        do {
            getGamefield().initShip(new Point(3,3));
            Point point = shoot(inputStream);
            if(isHit(point)){
                getGamefield().updatePoint(point, true);
                message = "прошло "+turnNum+" ходов, "+VIC_MESSAGE;
                reportMessage();
                isGaming = false;
            } else {
                getGamefield().updatePoint(point, false);
            }
            getGamefield().renderGameField();
            turnNum++;
        } while (isGaming);
    }

    public Point shoot(InputStream inputStream){
        Scanner scanner = new Scanner(inputStream);
        String s = scanner.nextLine();
        String[] split = s.split(" ");
        Point point = getGamefield().getPoint((Integer.valueOf(split[0])-1), (Integer.valueOf(split[1])-1));//7 Хочу, чтобы ввод номера ячейки начинался с 1 до числа ячеек на поле, чтобы ввод был юзер-френдли, как привык человек.
        return point;
    }

    public boolean isHit(Point point) {
        if(point.getX() == getGamefield().ship.position.getX() && point.getY() == getGamefield().ship.position.getY()){
            message = HIT_MESSAGE;
            reportMessage();
            return true;
        } else {
            message = MISS_MESSAGE;
            reportMessage();
            return false;
        }
    }

    public GameField getGamefield() {
        return gameField;
    }

    public void reportMessage(){
        System.out.println(message);
    }

    public String getMessage() {
        return message;
    }
}