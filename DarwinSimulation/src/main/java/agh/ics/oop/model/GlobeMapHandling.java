package agh.ics.oop.model;

public class GlobeMapHandling {
    private final int height;
    private final int width;

    public GlobeMapHandling(int height, int width) {
        this.height = height;
        this.width = width;
    }

    public boolean isInBorder(Vector2D position){
        if(position.follows(new Vector2D(width,height)))return false;
        return !position.precedes(new Vector2D(0, 0));
    }

    public Animal handleBorder(Animal animal){
        Vector2D currentPosition = animal.getPosition();
        Direction orientation = animal.getOrientation();
        int X = currentPosition.getX();
        int Y = currentPosition.getY();
        if (currentPosition.getX() < 0 || currentPosition.getX() > width) {
            X = (currentPosition.getX() + width + 1) % (width + 1);
        }
        if (currentPosition.getY() > height || currentPosition.getY() < 0) {
            Y = (currentPosition.getY() + (currentPosition.getY() > height ? height : 1)) % (height + 1);
            orientation = orientation.rotate(4);
        }
        Vector2D newPosition = new Vector2D(X, Y);
        return new Animal(newPosition, animal.getGenes(), animal.getEnergy(), orientation);
    }
}
