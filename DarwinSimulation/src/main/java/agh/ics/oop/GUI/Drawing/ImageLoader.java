package agh.ics.oop.GUI.Drawing;

import javafx.scene.image.Image;

public class ImageLoader {
    private final Image amongUsRed;
    private final Image amongUsLightGreen;
    private final Image amongUsGreen;
    private final Image amongUsPurple;
    private final Image amongUsBlack;
    private final Image amongUsWhite;
    private final Image grass;
    private final Image blank;

    public ImageLoader() {
        amongUsRed = new Image("/Images/amongUsRed.png");
        amongUsLightGreen = new Image("/Images/amongUsLightGreen.png");
        amongUsGreen = new Image("/Images/amongUsGreen.png");
        amongUsPurple = new Image("/Images/amongUsPurple.png");
        amongUsBlack = new Image("/Images/amongUsBlack.png");
        amongUsWhite = new Image("/Images/amongUsWhite.png");
        grass = new Image("/Images/grass.png");
        blank = new Image("/Images/blank.png");
    }

    public Image getAnimalImage(int energy) {
        if (energy >= 100) return amongUsRed;
        else if (energy >= 80) return amongUsLightGreen;
        else if (energy >= 60) return amongUsGreen;
        else if (energy >= 40) return amongUsPurple;
        else return amongUsBlack;
    }

    public Image getHighlighted() {
        return amongUsWhite;
    }

    public Image getGrass() {
        return grass;
    }

    public Image getBlank() {
        return blank;
    }
}