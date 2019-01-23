public class Ship {
    
    Point position;
    
    public void setPosition(Point position) {
        this.position = position;
    }

    public Point getPosition() {
        return position;
    }

    public Ship(Point position){
        this.position = position;
    }
}
