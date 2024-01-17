package agh.ics.oop.model.Observers;

import agh.ics.oop.model.WorldMap;
import agh.ics.oop.Stats.SimulationStatistics;

public interface MapChangeListener {
    void mapChanged(WorldMap worldMap, SimulationStatistics statistics);
}
