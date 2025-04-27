package EvacSite.Map;

import org.jxmapviewer.JXMapViewer;
import org.jxmapviewer.viewer.GeoPosition;

public interface EvacMapService {
void initMarker(JXMapViewer mapViewer);
void showMap();
void saveLoc();
void setMarker(JXMapViewer mapViewer, GeoPosition gp);
}
