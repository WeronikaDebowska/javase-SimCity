package sincity.model;

import java.util.*;

public class RoadPuzzle extends Observable {

    private HashMap<Direction, Boolean> roadDirections;
    private RoadType roadType;

    private int indexX; // position in City board
    private int indexY;

    private double size; // in pixels

    private double coX; // position on Scene
    private double coY;

    double halfLaneWidth;

    private double centerY;
    private double centerX; // center of the puzzle - anchor point of path rotation

    private boolean hasTrafficLight;

    private TrafficLights[] trafficLights;


    // TODO could put these lists into hash map, maybe lists are not needed
    List<Vehicle> northVehicleList = new ArrayList<>();
    List<Vehicle> southVehicleList = new ArrayList<>();
    List<Vehicle> westVehicleList = new ArrayList<>();
    List<Vehicle> eastVehicleList = new ArrayList<>();


    RoadPuzzle(int xIndex, int yIndex, int padding, double size, RoadType type, boolean hasTrafficLights) {
        this.roadDirections = type.getPossibleDirection();
        this.roadType = type;
        this.size = size;
        this.indexX = xIndex;
        this.indexY = yIndex;
        this.coX = xIndex * size - (padding * size);
        this.coY = yIndex * size - (padding * size);
        this.centerX = coX + size / 2.0;
        this.centerY = coY + size / 2.0;
        this.halfLaneWidth = 0.1 * size;     // maybe should be final
        this.hasTrafficLight = hasTrafficLights;
        if (hasTrafficLight) {
            this.trafficLights = new TrafficLightGenerator(this).createLights();
        }
    }

    public boolean hasTrafficLight() {
        return hasTrafficLight;
    }

    public TrafficLights[] getTrafficLights() {
        return trafficLights;
    }

    public double getCoX() {
        return coX;
    }

    public double getCoY() {
        return coY;
    }

    public double getSize() {
        return size;
    }

    public int getIndexY() {
        return indexY;
    }

    public int getIndexX() {
        return indexX;
    }

    double getCenterX() {
        return centerX;
    }

    double getCenterY() {
        return centerY;
    }

    public HashMap<Direction, Boolean> getRoadDirections() {
        return roadDirections;
    }

    RoadType getRoadType() {
        return roadType;
    }

    void addVehicleToList(Vehicle vehicle, Direction direction) {
        switch (direction) {
            case E:
                eastVehicleList.add(vehicle);
                break;
            case N:
                northVehicleList.add(vehicle);
                break;
            case S:
                southVehicleList.add(vehicle);
                break;
            case W:
                westVehicleList.add(vehicle);
                break;
        }
        // TODO redundant code
        setChanged();
        notifyObservers();
        clearChanged();
    }

    void removeLastVehicleFromList(Vehicle vehicle, Direction direction) {

        if (direction != null) {
            switch (direction) {
                case E:
                    eastVehicleList.remove(vehicle);
                    break;
                case N:
                    northVehicleList.remove(vehicle);
                    break;
                case S:
                    southVehicleList.remove(vehicle);
                    break;
                case W:
                    westVehicleList.remove(vehicle);
                    break;
            }

            setChanged();
            notifyObservers();
            clearChanged();
        }
    }


    List<Vehicle> getRightHandQueue(Direction arrivalDirection) {
        List<Vehicle> rightHandQueue = null;
        switch (arrivalDirection) {
            case E:
                rightHandQueue = this.northVehicleList;
                break;
            case W:
                rightHandQueue = this.southVehicleList;
                break;
            case S:
                rightHandQueue = this.eastVehicleList;
                break;
            case N:
                rightHandQueue = this.westVehicleList;
                break;
        }
        return rightHandQueue;
    }


    List<Vehicle> getOppositeQueue(Direction arrivalDirection) {
        List<Vehicle> oppositeQueue = null;
        switch (arrivalDirection) {
            case W:
                oppositeQueue = this.eastVehicleList;
                break;
            case E:
                oppositeQueue = this.westVehicleList;
                break;
            case N:
                oppositeQueue = this.southVehicleList;
                break;
            case S:
                oppositeQueue = this.northVehicleList;
                break;
        }
        return oppositeQueue;
    }


}
