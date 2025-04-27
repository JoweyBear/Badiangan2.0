package Hazard.Map;

import org.jxmapviewer.JXMapViewer;
import org.jxmapviewer.viewer.GeoPosition;

public interface HazMapService {
    void initMarker(JXMapViewer mapViewer);
    void showMap();
    void saveLoc();
    void setMarker(JXMapViewer mapViewer, GeoPosition gp);
}
