package Map;

import Util.MapUtil.MyWaypoint;
import java.awt.Graphics2D;
import org.jxmapviewer.viewer.WaypointPainter;

public interface MapService {
    WaypointPainter<MyWaypoint> putAllBeneInMap();
    WaypointPainter<MyWaypoint> putAllEvacInMap();
    WaypointPainter<MyWaypoint> putAllHazardInMap();
    void showDisaster(double lt, double lg, double rad);
    void myShape(Graphics2D g, double xCoord, double yCoord, double w, double h);
    void scanOverLay();
    boolean isAffected();
    void displayEvacInfo();
    void viewMap();
    void evacBtn();
    void viewEvac();
}
