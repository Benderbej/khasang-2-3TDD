import org.junit.Assert;

public class GameField {

    private int size;
    private Point[][] cells;
    public Ship ship;

    public GameField(int size) {
        this.size = size;
        cells = new Point[size][size];
        for (int x=0; x < 10; x++)
        {
            for (int y=0; y < 10; y++){
                cells[x][y] = new Point(x,y);
            }
        }
    }

    public void renderGameField(){
        for (int x=0; x < 10; x++)
        {
            StringBuilder s = new StringBuilder("");
            for (int y=0; y < 10; y++){
                s = s.append(getPoint(x,y).getContent());
            }
            System.out.println(s.toString());
        }

    }

    public int getSize() {
        return size;
    }

    public Point getPoint(int x, int y) {
        return cells[x][y];
    }

    public void initPoints(){
        for (int x = 0; x < getSize(); x++) {
            for (int y = 0; y < getSize(); y++) {
                getPoint(x,y).setContent('.');
            }
        }
    }

    public void updatePoint(Point point, Boolean result){
        if(result == true){
            getPoint(point.getX(), point.getY()).setContent('X');
        } else {
            getPoint(point.getX(), point.getY()).setContent('*');
        }
    }


    public void initShip(Point point) {
        ship = new Ship(point);
    }




}