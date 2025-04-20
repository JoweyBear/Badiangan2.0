package Map;

import java.util.ArrayList;

public interface MapDAO {
    ArrayList<Integer> getAllBene();
    ArrayList<String> getAllBeneGeo();
    ArrayList<Integer> getAllEvac();
    ArrayList<String> getAllEvacGeo();
    ArrayList<Integer> getAllHazard();
    ArrayList<String> getAllHazardGeo();
    ArrayList<String> getDisaster(int id);
}
