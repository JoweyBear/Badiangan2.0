package LiveStock.Disposal;

import java.sql.ResultSet;
import java.util.ArrayList;

public interface DisposalDAO {
    ResultSet getAllDisposal();
    void saveDisposal(DisposalModel dis);
    void updateDisposal(DisposalModel dis);
    void deleteDisposal(String id);
     ArrayList<Integer> getLSID();
     void updateLSDisposed(int id);
}
