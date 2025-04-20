package Hazard.Map;

import org.jxmapviewer.JXMapViewer;
import org.jxmapviewer.viewer.GeoPosition;

public interface MapService {
    void initMarker(JXMapViewer mapViewer);
    void saveLoc();
    void setMarker(JXMapViewer mapViewer, GeoPosition gp);    
}
