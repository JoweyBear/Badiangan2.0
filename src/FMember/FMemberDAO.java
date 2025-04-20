package FMember;

import java.sql.ResultSet;

public interface FMemberDAO {
    ResultSet getAllFM();
    void saveFM(FMemberModel fm);
    void update(FMemberModel fm);
    void deleteFM(String id);
}
