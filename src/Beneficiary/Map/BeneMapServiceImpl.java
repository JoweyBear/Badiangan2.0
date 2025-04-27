package Beneficiary.Map;

import Beneficiary.BenePanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
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
import Util.MapUtil.FancyWaypointRenderer;
import Util.MapUtil.MapDimension;
import Util.MapUtil.MapGenerate;
import Util.MapUtil.MyWaypoint;

public class BeneMapServiceImpl implements BeneMapService {

    BenePanel bp;
    BeneMapPanel mpp;
    //JXMapViewer mapViewer;

    public BeneMapServiceImpl(BeneMapPanel mpp, BenePanel bp) {
        this.mpp = mpp;
        this.bp = bp;

        JXMapViewer mapViewer = MapGenerate.generateMap();
        initMarker(mapViewer);
    }

    public BeneMapServiceImpl(BeneMapPanel mpp, BenePanel bp, double locLat, double locLong) {
        this.mpp = mpp;
        this.bp = bp;

        JXMapViewer mapViewer = MapGenerate.generateMap();
        setMarker(mapViewer, new GeoPosition(locLat, locLong));
        System.out.println("loclatloclong");
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

    public void initMarker(JXMapViewer mapViewer) {
        Painter<JXMapViewer> origOverLay = (Painter<JXMapViewer>) mapViewer.getOverlayPainter();
        List<Painter<JXMapViewer>> painters = new ArrayList<Painter<JXMapViewer>>();

        mapViewer.addMouseListener(new MapClickListener(mapViewer) {
            @Override
            public void mapClicked(GeoPosition gp) {
                Set<MyWaypoint> waypoints = new HashSet<MyWaypoint>(Arrays.asList(
                        new MyWaypoint("", Color.WHITE, gp)));

                // Create a waypoint painter that takes all the waypoints
                WaypointPainter<MyWaypoint> waypointPainter = new WaypointPainter<MyWaypoint>();
                waypointPainter.setWaypoints(waypoints);
                waypointPainter.setRenderer(new FancyWaypointRenderer());

                // Create a compound painter that uses both the route-painter and the waypoint-painter
                painters.clear();
                painters.add(origOverLay);
                painters.add(waypointPainter);

                CompoundPainter<JXMapViewer> painter = new CompoundPainter<JXMapViewer>(painters);
                mapViewer.setOverlayPainter(painter);

                mpp.latLbl.setText("" + gp.getLatitude());
                mpp.longLbl.setText("" + gp.getLongitude());
            }
        });

        //JDialog mapDialog = new JDialog();
        mpp.mapPanel.add(mapViewer);
        System.out.println("InitMaker");
    }

    public void setMarker(JXMapViewer mapViewer, GeoPosition gp) {
        mpp.saveBtn.setVisible(false);
        mpp.latLbl.setText("" + gp.getLatitude());
        mpp.longLbl.setText("" + gp.getLongitude());

        Painter<JXMapViewer> origOverLay = (Painter<JXMapViewer>) mapViewer.getOverlayPainter();
        List<Painter<JXMapViewer>> painters = new ArrayList<Painter<JXMapViewer>>();
        Set<MyWaypoint> waypoints = new HashSet<MyWaypoint>(Arrays.asList(
                new MyWaypoint("", Color.ORANGE, gp)));

        // Create a waypoint painter that takes all the waypoints
        WaypointPainter<MyWaypoint> waypointPainter = new WaypointPainter<MyWaypoint>();
        waypointPainter.setWaypoints(waypoints);
        waypointPainter.setRenderer(new FancyWaypointRenderer());

        // Create a compound painter that uses both the route-painter and the waypoint-painter
        painters.clear();
        painters.add(origOverLay);
        painters.add(waypointPainter);

        CompoundPainter<JXMapViewer> painter = new CompoundPainter<JXMapViewer>(painters);
        mapViewer.setOverlayPainter(painter);

        //JDialog mapDialog = new JDialog();
        mpp.mapDialog.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/images/title.png")));
        mpp.mapDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        mpp.mapDialog.setModal(true);
        mpp.mapDialog.setPreferredSize(new Dimension(MapDimension.W, MapDimension.H));
        mpp.mapDialog.setSize(new Dimension(MapDimension.W, MapDimension.H));
        mpp.mapPanel.add(mapViewer);
        mpp.mapDialog.setLocationRelativeTo(mpp);
        mpp.mapDialog.setTitle("Map Dialog");
        mpp.mapDialog.pack();
        mpp.mapDialog.setVisible(true);
        System.out.println("Markerset");
    }

    @Override
    public void saveLoc() {
        bp.latLongLbl.setText(mpp.latLbl.getText() + "," + mpp.longLbl.getText());
        bp.longLatLbl1.setText(mpp.latLbl.getText() + "," + mpp.longLbl.getText());
        mpp.mapDialog.dispose();
        System.out.println("LocSaved");
    }


}
