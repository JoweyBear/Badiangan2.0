package LiveStock;

import java.sql.ResultSet;

public interface LSDAO {
    ResultSet getAllLS();
    void saveLS(LSModel ls);
    void updateLS(LSModel ls);
    void deleteLS(String id);
}
