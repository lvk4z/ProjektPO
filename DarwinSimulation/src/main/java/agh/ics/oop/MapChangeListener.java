package agh.ics.oop;

import agh.ics.oop.Stats.SimulationStatistics;
import agh.ics.oop.model.WorldMap;

public interface MapChangeListener {
    void mapChanged(WorldMap worldMap, SimulationStatistics statistics);
    void dayPassed(SimulationStatistics statistics);
}
