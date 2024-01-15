package agh.ics.oop.Model.Observers;

import agh.ics.oop.Model.WorldMap;
import agh.ics.oop.Stats.SimulationStatistics;

public interface MapChangeListener {
    void mapChanged(WorldMap worldMap, SimulationStatistics statistics);
    void dayPassed(SimulationStatistics statistics);
}
