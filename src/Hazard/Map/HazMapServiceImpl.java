package Hazard.Map;

import Hazard.HazardPanel;
import Util.MapUtil.FancyWaypointRenderer2;
import Util.MapUtil.MapDimension;
import Util.MapUtil.MapGenerate;
import Util.MapUtil.MyWaypoint;
import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.swing.JDialog;
import org.jxmapviewer.JXMapViewer;
import org.jxmapviewer.input.MapClickListener;
import org.jxmapviewer.painter.CompoundPainter;
import org.jxmapviewer.painter.Painter;
import org.jxmapviewer.viewer.GeoPosition;
import org.jxmapviewer.viewer.WaypointPainter;

public class HazMapServiceImpl implements HazMapService {

    HazardPanel hazp;
    HazMapPanel mpp;

    //JXMapViewer mapViewer;
    public HazMapServiceImpl(HazMapPanel mpp, HazardPanel hazp) {
        this.mpp = mpp;
        this.hazp = hazp;

        JXMapViewer mapViewer = MapGenerate.generateMap();
        initMarker(mapViewer);
    }

    public HazMapServiceImpl(HazMapPanel mpp, HazardPanel hazp, double locLat, double locLong) {
        this.mpp = mpp;
        this.hazp = hazp;

        JXMapViewer mapViewer = MapGenerate.generateMap();
        setMarker(mapViewer, new GeoPosition(locLat, locLong));
    }

    @Override
    public void showMap() {
        mpp.mapDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        mpp.mapDialog.setModal(true);
        mpp.mapDialog.setPreferredSize(new Dimension(MapDimension.W, MapDimension.H));
        mpp.mapDialog.setSize(new Dimension(MapDimension.W, MapDimension.H));
        mpp.mapDialog.setLocationRelativeTo(null);
        mpp.mapDialog.setTitle("Map Dialog");
        mpp.mapDialog.pack();
        mpp.mapDialog.setVisible(true);    }
    @Override
    public void initMarker(JXMapViewer mapViewer) {
        Painter<JXMapViewer> origOverLay = (Painter<JXMapViewer>) mapViewer.getOverlayPainter();
        List<Painter<JXMapViewer>> painters = new ArrayList<Painter<JXMapViewer>>();

        mapViewer.addMouseListener(new MapClickListener(mapViewer) {
            @Override
            public void mapClicked(GeoPosition gp) {
                Set<MyWaypoint> waypoints = new HashSet<MyWaypoint>(Arrays.asList(
                        new MyWaypoint("", Color.ORANGE, gp)));

                // Create a waypoint painter that takes all the waypoints
                WaypointPainter<MyWaypoint> waypointPainter = new WaypointPainter<MyWaypoint>();
                waypointPainter.setWaypoints(waypoints);
                waypointPainter.setRenderer(new FancyWaypointRenderer2());

                // Create a compound painter that uses both the route-painter and the waypoint-painter
                painters.clear();
                painters.add(origOverLay);
                painters.add(waypointPainter);

                CompoundPainter<JXMapViewer> painter = new CompoundPainter<JXMapViewer>(painters);
                mapViewer.setOverlayPainter(painter);

                mpp.longLbl.setText("" + gp.getLongitude());
                mpp.latLbl.setText("" + gp.getLatitude());
            }
        });

        //JDialog mapDialog = new JDialog();
        mpp.mapPanel.add(mapViewer);

    }

    @Override
    public void saveLoc() {
        hazp.latSpin.setValue(Double.parseDouble(mpp.latLbl.getText()));
        hazp.longSpin.setValue(Double.parseDouble(mpp.longLbl.getText()));
        hazp.latSpin1.setValue(Double.parseDouble(mpp.latLbl.getText()));
        hazp.longSpin1.setValue(Double.parseDouble(mpp.longLbl.getText()));
        mpp.mapDialog.dispose();
    }

    @Override
    public void setMarker(JXMapViewer mapViewer, GeoPosition gp) {
        mpp.saveBtn.setVisible(false);
        mpp.longLbl.setText("" + gp.getLongitude());
        mpp.latLbl.setText("" + gp.getLatitude());

        Painter<JXMapViewer> origOverLay = (Painter<JXMapViewer>) mapViewer.getOverlayPainter();
        List<Painter<JXMapViewer>> painters = new ArrayList<Painter<JXMapViewer>>();

        Set<MyWaypoint> waypoints = new HashSet<MyWaypoint>(Arrays.asList(
                new MyWaypoint("H", Color.ORANGE, gp)));

        // Create a waypoint painter that takes all the waypoints
        WaypointPainter<MyWaypoint> waypointPainter = new WaypointPainter<MyWaypoint>();
        waypointPainter.setWaypoints(waypoints);
        waypointPainter.setRenderer(new FancyWaypointRenderer2());

        // Create a compound painter that uses both the route-painter and the waypoint-painter
        painters.clear();
        painters.add(origOverLay);
        painters.add(waypointPainter);

        CompoundPainter<JXMapViewer> painter = new CompoundPainter<JXMapViewer>(painters);
        mapViewer.setOverlayPainter(painter);

        //JDialog mapDialog = new JDialog();
        mpp.mapDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        mpp.mapDialog.setModal(true);
        mpp.mapDialog.setPreferredSize(new Dimension(MapDimension.W, MapDimension.H));
        mpp.mapDialog.setSize(new Dimension(MapDimension.W, MapDimension.H));
        mpp.mapPanel.add(mapViewer);
        mpp.mapDialog.setLocationRelativeTo(null);
        mpp.mapDialog.setTitle("Map Dialog");
        mpp.mapDialog.pack();
        mpp.mapDialog.setVisible(true);
    }


}
