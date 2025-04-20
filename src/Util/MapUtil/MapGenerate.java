 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Util.MapUtil;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.event.MouseInputListener;
import org.jxmapviewer.JXMapViewer;
import org.jxmapviewer.OSMTileFactoryInfo;
import org.jxmapviewer.input.PanKeyListener;
import org.jxmapviewer.input.PanMouseInputListener;
import org.jxmapviewer.input.ZoomMouseWheelListenerCenter;
import org.jxmapviewer.painter.CompoundPainter;
import org.jxmapviewer.painter.Painter;
import org.jxmapviewer.viewer.DefaultTileFactory;
import org.jxmapviewer.viewer.GeoPosition;
import org.jxmapviewer.viewer.TileFactory;
import org.jxmapviewer.viewer.TileFactoryInfo;
import Settings.Settings;

/**
 *
 * @author John Rey Alipe
 */
public class MapGenerate 
{
    public static JXMapViewer generateMap()
    {
        int zoomVal = 6;
        final JXMapViewer mapViewer = new JXMapViewer();
        GeoPosition badiangan = new GeoPosition(10.894825, 122.703795);
        //GeoPosition badiangan = new GeoPosition(10.999783, 122.526848);
        //TileFactoryInfo info = new OSMTileFactoryInfo("ZIP archive", "jar:file:F:/Theses/Badiangan/Project/Badiangan/tiles/tiles.zip!");
        //TileFactoryInfo info = new OSMTileFactoryInfo("", "file:/tiles"); //inside wowowowow
        //TileFactoryInfo info = new OSMTileFactoryInfo("","jar:file:tiles/tiles.zip!"); //outside
        //TileFactoryInfo info = new OSMTileFactoryInfo("ZIP archive", "jar:file:tiles.zip!");
        //TileFactoryInfo info = new OSMTileFactoryInfo(); //online
        //TileFactoryInfo info = new OSMTileFactoryInfo("","file:D:/tiles"); //inside
        TileFactoryInfo info = Settings.info;
        TileFactory tileFactory = new DefaultTileFactory(info);
        
        // Setup JXMapViewer
        mapViewer.setTileFactory(tileFactory);

        // Set the focus
        mapViewer.setZoom(zoomVal);
        mapViewer.setAddressLocation(badiangan);
        mapViewer.setCenterPosition(badiangan);

        //generate border
        GeoPosition b1 = new GeoPosition(10.94776, 122.76151);
        GeoPosition b2 = new GeoPosition(10.94918, 122.76505);
        GeoPosition b3 = new GeoPosition(10.95158, 122.76451);
        GeoPosition b4 = new GeoPosition(10.95080, 122.76063);
        GeoPosition b5 = new GeoPosition(10.95092, 122.75737);
        GeoPosition b6 = new GeoPosition(10.94713, 122.75735);
        GeoPosition b7 = new GeoPosition(10.94705, 122.75366);
        GeoPosition b8 = new GeoPosition(10.95080, 122.74280);
        GeoPosition b9 = new GeoPosition(10.95002, 122.73252);
        GeoPosition b10 = new GeoPosition(10.95084, 122.73014);
        GeoPosition b11 = new GeoPosition(10.95025, 122.72853);
        GeoPosition b12 = new GeoPosition(10.95122, 122.72675);
        GeoPosition b13 = new GeoPosition(10.95114, 122.72505);
        GeoPosition b14 = new GeoPosition(10.95128, 122.72501);
        GeoPosition b15 = new GeoPosition(10.94924, 122.72456);
        GeoPosition b16 = new GeoPosition(10.94751, 122.72123);
        GeoPosition b17 = new GeoPosition(10.94939, 122.71870);
        GeoPosition b18 = new GeoPosition(10.94861, 122.71671);
        GeoPosition b19 = new GeoPosition(10.94897, 122.71535);
        GeoPosition b20 = new GeoPosition(10.95082, 122.71246);
        GeoPosition b21 = new GeoPosition(10.94987, 122.70973);
        GeoPosition b22 = new GeoPosition(10.94970, 122.70729);
        GeoPosition b23 = new GeoPosition(10.95080, 122.70274);
        GeoPosition b24 = new GeoPosition(10.95474, 122.69952);
        GeoPosition b25 = new GeoPosition(10.95400, 122.69845);
        GeoPosition b26 = new GeoPosition(10.95430, 122.69456);
        GeoPosition b27 = new GeoPosition(10.95430, 122.69456);
        GeoPosition b28 = new GeoPosition(10.95223, 122.69473);
        GeoPosition b29 = new GeoPosition(10.95261, 122.69233);
        GeoPosition b30 = new GeoPosition(10.95446, 122.69063);
        GeoPosition b31 = new GeoPosition(10.95406, 122.69018);
        GeoPosition b32 = new GeoPosition(10.95474, 122.68686);
        GeoPosition b33 = new GeoPosition(10.95099, 122.68291);
        GeoPosition b34 = new GeoPosition(10.94985, 122.68415);
        GeoPosition b35 = new GeoPosition(10.94884, 122.68310);
        GeoPosition b36 = new GeoPosition(10.94916, 122.68053);
        GeoPosition b37 = new GeoPosition(10.94484, 122.68072);
        GeoPosition b38 = new GeoPosition(10.94307, 122.67832);
        GeoPosition b39 = new GeoPosition(10.94395, 122.67761);
        GeoPosition b40 = new GeoPosition(10.94389, 122.67759);
        GeoPosition b41 = new GeoPosition(10.94389, 122.67759);
        GeoPosition b42 = new GeoPosition(10.93833, 122.67606);
        GeoPosition b43 = new GeoPosition(10.92944, 122.67012);
        GeoPosition b44 = new GeoPosition(10.92944, 122.67012);
        GeoPosition b45 = new GeoPosition(10.92712, 122.66952);
        GeoPosition b46 = new GeoPosition(10.92463, 122.67059);
        GeoPosition b47 = new GeoPosition(10.92092, 122.67819);
        GeoPosition b48 = new GeoPosition(10.92122, 122.68330);
        GeoPosition b49 = new GeoPosition(10.92366, 122.68810);
        GeoPosition b50 = new GeoPosition(10.92274, 122.69008);
        GeoPosition b51 = new GeoPosition(10.91810, 122.69042);
        GeoPosition b52 = new GeoPosition(10.91650, 122.68982);
        GeoPosition b53 = new GeoPosition(10.91524, 122.68733);
        GeoPosition b54 = new GeoPosition(10.91212, 122.68561);
        GeoPosition b55 = new GeoPosition(10.90942, 122.68587);
        GeoPosition b56 = new GeoPosition(10.90495, 122.68433);
        GeoPosition b57 = new GeoPosition(10.90360, 122.68330);
        GeoPosition b58 = new GeoPosition(10.90462, 122.68175);
        GeoPosition b59 = new GeoPosition(10.90782, 122.68021);
        GeoPosition b60 = new GeoPosition(10.90714, 122.67815);
        GeoPosition b61 = new GeoPosition(10.90445, 122.67772);
        GeoPosition b62 = new GeoPosition(10.90344, 122.67634);
        GeoPosition b63 = new GeoPosition(10.90310, 122.67437);
        GeoPosition b64 = new GeoPosition(10.90394, 122.66991);
        GeoPosition b65 = new GeoPosition(10.89855, 122.66673);
        GeoPosition b66 = new GeoPosition(10.89492, 122.66313);
        GeoPosition b67 = new GeoPosition(10.89315, 122.66338);
        GeoPosition b68 = new GeoPosition(10.89248, 122.66407);
        GeoPosition b69 = new GeoPosition(10.88709, 122.66373);
        GeoPosition b70 = new GeoPosition(10.88355, 122.66089);
        GeoPosition b71 = new GeoPosition(10.87663, 122.66158);
        GeoPosition b72 = new GeoPosition(10.87225, 122.65394);
        GeoPosition b73 = new GeoPosition(10.86492, 122.65437);
        GeoPosition b74 = new GeoPosition(10.85750, 122.65291);
        GeoPosition b75 = new GeoPosition(10.85750, 122.65471);
        GeoPosition b76 = new GeoPosition(10.86093, 122.65632);
        GeoPosition b77 = new GeoPosition(10.86060, 122.65856);
        GeoPosition b78 = new GeoPosition(10.86117, 122.66089);
        GeoPosition b79 = new GeoPosition(10.86235, 122.66171);
        GeoPosition b80 = new GeoPosition(10.86315, 122.66145);
        GeoPosition b81 = new GeoPosition(10.86399, 122.66190);
        GeoPosition b82 = new GeoPosition(10.86479, 122.66113);
        GeoPosition b83 = new GeoPosition(10.86610, 122.66169);
        GeoPosition b84 = new GeoPosition(10.86622, 122.66381);
        GeoPosition b85 = new GeoPosition(10.86772, 122.66716);
        GeoPosition b86 = new GeoPosition(10.86774, 122.67038);
        GeoPosition b87 = new GeoPosition(10.86983, 122.67049);
        GeoPosition b88 = new GeoPosition(10.86991, 122.67167);
        GeoPosition b89 = new GeoPosition(10.86844, 122.67201);
        GeoPosition b90 = new GeoPosition(10.86791, 122.67154);
        GeoPosition b91 = new GeoPosition(10.86551, 122.67244);
        GeoPosition b92 = new GeoPosition(10.86576, 122.67360);
        GeoPosition b93 = new GeoPosition(10.86789, 122.67574);
        GeoPosition b94 = new GeoPosition(10.86671, 122.67812);
        GeoPosition b95 = new GeoPosition(10.86823, 122.68018);
        GeoPosition b96 = new GeoPosition(10.86972, 122.68096);
        GeoPosition b97 = new GeoPosition(10.87050, 122.68233);
        GeoPosition b98 = new GeoPosition(10.87179, 122.68237);
        GeoPosition b99 = new GeoPosition(10.87440, 122.68564);
        GeoPosition b100 = new GeoPosition(10.87455, 122.68673);
        GeoPosition b101 = new GeoPosition(10.87240, 122.68604);
        GeoPosition b102 = new GeoPosition(10.87156, 122.68703);
        GeoPosition b103 = new GeoPosition(10.87208, 122.68754);
        GeoPosition b104 = new GeoPosition(10.87267, 122.68744);
        GeoPosition b105 = new GeoPosition(10.87311, 122.68815);
        GeoPosition b106 = new GeoPosition(10.87474, 122.68872);
        GeoPosition b107 = new GeoPosition(10.87583, 122.68737);
        GeoPosition b108 = new GeoPosition(10.87678, 122.68795);
        GeoPosition b109 = new GeoPosition(10.87760, 122.69023);
        GeoPosition b110 = new GeoPosition(10.87550, 122.69083);
        GeoPosition b111 = new GeoPosition(10.87278, 122.69351);
        GeoPosition b112 = new GeoPosition(10.87547, 122.70042);
        GeoPosition b113 = new GeoPosition(10.87280, 122.70484);
        GeoPosition b114 = new GeoPosition(10.87857, 122.70733);
        GeoPosition b115 = new GeoPosition(10.87853, 122.71778);
        GeoPosition b116 = new GeoPosition(10.88304, 122.72183);
        GeoPosition b117 = new GeoPosition(10.88357, 122.72469);
        GeoPosition b118 = new GeoPosition(10.88287, 122.72580);
        GeoPosition b119 = new GeoPosition(10.88302, 122.72686);
        GeoPosition b120 = new GeoPosition(10.88262, 122.72741);
        GeoPosition b121 = new GeoPosition(10.88319, 122.72866);
        GeoPosition b122 = new GeoPosition(10.88317, 122.72964);
        GeoPosition b123 = new GeoPosition(10.88458, 122.73020);
        GeoPosition b124 = new GeoPosition(10.88561, 122.73649);
        GeoPosition b125 = new GeoPosition(10.88641, 122.73786);
        GeoPosition b126 = new GeoPosition(10.88607, 122.73949);
        GeoPosition b127 = new GeoPosition(10.88803, 122.74153);
        GeoPosition b128 = new GeoPosition(10.88772, 122.74364);
        GeoPosition b129 = new GeoPosition(10.88502, 122.74917);
        GeoPosition b130 = new GeoPosition(10.88513, 122.75035);
        GeoPosition b131 = new GeoPosition(10.88405, 122.75164);
        GeoPosition b132 = new GeoPosition(10.88340, 122.75172);
        GeoPosition b133 = new GeoPosition(10.88268, 122.75136);
        GeoPosition b134 = new GeoPosition(10.88104, 122.74941);
        GeoPosition b135 = new GeoPosition(10.87912, 122.74969);
        GeoPosition b136 = new GeoPosition(10.87874, 122.75012);
        GeoPosition b137 = new GeoPosition(10.87779, 122.75288);
        GeoPosition b138 = new GeoPosition(10.87609, 122.75426);
        GeoPosition b139 = new GeoPosition(10.876881, 122.756821);
        GeoPosition b140 = new GeoPosition(10.876649, 122.757196);
        GeoPosition b141 = new GeoPosition(10.875770, 122.757819);
        GeoPosition b142 = new GeoPosition(10.876397, 122.758564);
        GeoPosition b143 = new GeoPosition(10.876423, 122.758833);
        GeoPosition b144 = new GeoPosition(10.876270, 122.758977);
        GeoPosition b145 = new GeoPosition(10.875712, 122.758774);
        GeoPosition b146 = new GeoPosition(10.875422, 122.758822);
        GeoPosition b147 = new GeoPosition(10.875575, 122.759128);
        GeoPosition b148 = new GeoPosition(10.876144, 122.759787);
        GeoPosition b149 = new GeoPosition(10.876760, 122.761118);
        GeoPosition b150 = new GeoPosition(10.877361, 122.761547);
        GeoPosition b152 = new GeoPosition(10.877413, 122.761869);
        GeoPosition b153 = new GeoPosition(10.877139, 122.762470);
        GeoPosition b154 = new GeoPosition(10.877187, 122.762840);
        GeoPosition b155 = new GeoPosition(10.878088, 122.764476);
        GeoPosition b156 = new GeoPosition(10.877987, 122.765833);
        GeoPosition b157 = new GeoPosition(10.879299, 122.766783);
        GeoPosition b158 = new GeoPosition(10.879657, 122.767335);
        GeoPosition b159 = new GeoPosition(10.879441, 122.768252);
        GeoPosition b160 = new GeoPosition(10.879046, 122.768639);
        GeoPosition b161 = new GeoPosition(10.877840, 122.768923);
        GeoPosition b162 = new GeoPosition(10.877735, 122.769223);
        GeoPosition b163 = new GeoPosition(10.877808, 122.769814);
        GeoPosition b164 = new GeoPosition(10.877582, 122.770253);
        GeoPosition b165 = new GeoPosition(10.876902, 122.770656);
        GeoPosition b166 = new GeoPosition(10.876755, 122.771090);
        GeoPosition b167 = new GeoPosition(10.877434, 122.771820);
        GeoPosition b168 = new GeoPosition(10.877371, 122.772287);
        GeoPosition b169 = new GeoPosition(10.877118, 122.772689);
        GeoPosition b170 = new GeoPosition(10.877476, 122.773590);
        GeoPosition b171 = new GeoPosition(10.878678, 122.775629);
        GeoPosition b172 = new GeoPosition(10.878140, 122.776873);
        GeoPosition b173 = new GeoPosition(10.878209, 122.777726);
        GeoPosition b174 = new GeoPosition(10.877571, 122.779512);
        GeoPosition b175 = new GeoPosition(10.877034, 122.780124);
        GeoPosition b176 = new GeoPosition(10.876123, 122.780639);
        GeoPosition b177 = new GeoPosition(10.875580, 122.781272);
        GeoPosition b178 = new GeoPosition(10.875295, 122.782238);
        GeoPosition b179 = new GeoPosition(10.87777, 122.78471);
        GeoPosition b180 = new GeoPosition(10.88051, 122.78569);
        GeoPosition b181 = new GeoPosition(10.88342, 122.78574);
        GeoPosition b182 = new GeoPosition(10.88557, 122.78668);
        GeoPosition b183 = new GeoPosition(10.88755, 122.78612);
        GeoPosition b184 = new GeoPosition(10.88759, 122.78273);
        GeoPosition b185 = new GeoPosition(10.89092, 122.78329);
        GeoPosition b186 = new GeoPosition(10.89395, 122.78925);
        GeoPosition b187 = new GeoPosition(10.89551, 122.79007);
        GeoPosition b188 = new GeoPosition(10.89724, 122.78955);
        GeoPosition b189 = new GeoPosition(10.89975, 122.78934);
        GeoPosition b190 = new GeoPosition(10.90299, 122.78505);
        GeoPosition b191 = new GeoPosition(10.90813, 122.78340);
        GeoPosition b192 = new GeoPosition(10.91313, 122.78361);
        GeoPosition b193 = new GeoPosition(10.91408, 122.78325);
        GeoPosition b194 = new GeoPosition(10.91473, 122.78245);
        GeoPosition b195 = new GeoPosition(10.91555, 122.78234);
        GeoPosition b196 = new GeoPosition(10.91738, 122.78290);
        GeoPosition b197 = new GeoPosition(10.93057, 122.77702);
        GeoPosition b198 = new GeoPosition(10.93163, 122.77230);
        GeoPosition b199 = new GeoPosition(10.93314, 122.77179);
        GeoPosition b200 = new GeoPosition(10.93479, 122.77252);
        GeoPosition b201 = new GeoPosition(10.93656, 122.77260);
        GeoPosition b202 = new GeoPosition(10.94182, 122.76990);
        GeoPosition b203 = new GeoPosition(10.94633, 122.76582);
        GeoPosition b204 = new GeoPosition(10.94772, 122.76170);
        
        

        // Create a track from the geo-positions
        List<GeoPosition> track = Arrays.asList(
        b1, b2, b3, b4, b5, b6, b7, b8, b9, b10,
        b11, b12, b13, b14, b15, b16, b17, b18, b19, b20, 
        b21, b22, b23, b24, b25, b26, b27, b28, b29, b30,
        b31, b32, b33, b34, b35, b36, b37, b38, b39, b40,
        b41, b42, b43, b44, b45, b46, b47, b48, b49, b50,
        b51, b52, b53, b54, b55, b56, b57, b58, b59, b60,
        b61, b62, b63, b64, b65, b66, b67, b68, b69, b70,
        b71, b72, b73, b74, b75, b76, b77, b78, b79, b80,
        b81, b82, b83, b84, b85, b86, b87, b88, b89, b90,
        b91, b92, b93, b94, b95, b96, b97, b98, b99, b100,
        b101, b102, b103, b104, b105, b106, b107, b108, b109, b110,
        b111, b112, b113, b114, b115, b116, b117, b118, b119, b120,
        b121, b122, b123, b124, b125, b126, b127, b128, b129, b130,
        b131, b132, b133, b134, b135, b136, b137, b138, b139, b140,
        b141, b142, b143, b144, b145, b146, b147, b148, b149, b150,
         b152, b153, b154, b155, b156, b157, b158, b159, b160,
        b161, b162, b163, b164, b165, b166, b167, b168, b169, b170,
        b171, b172, b173, b174, b175, b176, b177, b178, b179, b180,
        b181, b182, b183, b184, b185, b186, b187, b188, b189, b190,
        b191, b192, b193, b194, b195, b196, b197, b198, b199, b200,
        b201, b202, b203, b204);
        RoutePainter routePainter = new RoutePainter(track);
        
        // Create a compound painter that uses both the route-painter and the waypoint-painter
        List<Painter<JXMapViewer>> painters = new ArrayList<Painter<JXMapViewer>>();
        painters.add(routePainter);

        CompoundPainter<JXMapViewer> painter = new CompoundPainter<JXMapViewer>(painters);
        mapViewer.setOverlayPainter(painter);

        // Add interactions
        MouseInputListener mia = new PanMouseInputListener(mapViewer);
        mapViewer.addMouseListener(mia);
        mapViewer.addMouseMotionListener(mia);
        //mapViewer.addMouseListener(new CenterMapListener(mapViewer));
        mapViewer.addMouseWheelListener(new ZoomMouseWheelListenerCenter(mapViewer));
        mapViewer.addKeyListener(new PanKeyListener(mapViewer));

        mapViewer.addMouseWheelListener(new MouseWheelListener() {
            @Override
            public void mouseWheelMoved(MouseWheelEvent e) {
               if(mapViewer.getZoom() > zoomVal )
               {
                   mapViewer.setZoom(zoomVal);
                   mapViewer.setAddressLocation(badiangan);
               }
            }
        });
        
        mapViewer.addMouseMotionListener(new MouseAdapter()
        {
            @Override
             public void mouseDragged(MouseEvent e)
             {
                System.out.println(mapViewer.getCenterPosition().getLatitude());
                //high 10.98130
                //low 10.81864
                
                System.out.println(mapViewer.getCenterPosition().getLongitude());
                //right 122.86629
                //left 122.5659
                if(mapViewer.getCenterPosition().getLatitude() >= 10.98130 ||
                   mapViewer.getCenterPosition().getLatitude() <= 10.81864 ||
                   mapViewer.getCenterPosition().getLongitude() >= 122.86629 ||
                   mapViewer.getCenterPosition().getLongitude() <= 122.5659)
                {
                    JOptionPane.showMessageDialog(null,"You are out of bounds...\n Returning to the center");
                    mapViewer.setZoom(zoomVal);
                    mapViewer.setAddressLocation(badiangan);
                }
             }
             
        });
        
        return mapViewer;
    }
}
