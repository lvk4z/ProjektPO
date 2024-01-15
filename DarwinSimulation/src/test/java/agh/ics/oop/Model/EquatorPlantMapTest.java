package agh.ics.oop.Model;

import agh.ics.oop.Model.Plants.EquatorPlantMap;
import agh.ics.oop.Model.Plants.PlantMap;
import org.junit.jupiter.api.Test;

import java.util.List;

class EquatorPlantMapTest {

    @Test
    void getPreferredPositions() {
        PlantMap plantMap = new EquatorPlantMap(15,20,30);
        List<Vector2D> positions = plantMap.getPreferredPositionsList();
        for(int i=0;i<15;i++){
            for(int j=0;j<20;j++){
                Vector2D position = new Vector2D(j,i);
                if(positions.contains(position))System.out.print("[*]");
                else System.out.print("[0]");
            }
            System.out.println();
        }
    }

}