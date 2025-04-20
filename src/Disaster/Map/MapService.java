package Disaster.Map;

import java.awt.Graphics2D;

public interface MapService {
    void showOval();
    void initMarker();
    void myShape(Graphics2D g, double xCoord, double yCoord, double w, double h);
    void saveLoc();
    void setMarker();

}
