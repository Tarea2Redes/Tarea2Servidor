package Data;

import Domain.DiskNode;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DiskNodeData extends DataBaseConf {

    public DiskNodeData() {

    }

    public boolean insert() throws ClassNotFoundException, SQLException {

        String llamadaSQL = "{CALL tarea2.sp_insert_disk}";

        Connection conn = getSQLConnection();

        CallableStatement callState = conn.prepareCall(llamadaSQL);

        try {

            callState.execute();

            return true;

        } catch (SQLException e) {
            System.out.println("Exception reported. Insertion failure detected X");
            return false;
        }

    }

    public ArrayList<DiskNode> getActiveList() throws ClassNotFoundException, SQLException {

        Connection conn = getSQLConnection();
        Statement state = conn.createStatement();

        ArrayList<DiskNode> list = new ArrayList<>();

        String SQL = "SELECT * FROM tarea2.disks where active = 1";

        ResultSet rs = state.executeQuery(SQL);

        while (rs.next()) {
            list.add(new DiskNode(Integer.parseInt(rs.getString("number")), 1));
        }

        return list;

    }

    public ArrayList<DiskNode> getDesactivatedList() throws ClassNotFoundException, SQLException {

        Connection conn = getSQLConnection();
        Statement state = conn.createStatement();

        ArrayList<DiskNode> list = new ArrayList<>();

        String SQL = "SELECT * FROM tarea2.disks where active = 0";

        ResultSet rs = state.executeQuery(SQL);

        while (rs.next()) {
            list.add(new DiskNode(Integer.parseInt(rs.getString("number")), 1));
        }

        return list;

    }

    public boolean disable(int number) throws ClassNotFoundException, SQLException {

        String llamadaSQL = "{CALL tarea2.sp_delete_disk(?)}";

        Connection conn = getSQLConnection();

        CallableStatement callState = conn.prepareCall(llamadaSQL);
        callState.setString(1, String.valueOf(number));

        try {

            callState.execute();

            return true;

        } catch (SQLException e) {
            System.out.println("Exception reported. disable disk failure detected X");
            return false;
        }

    }

    public boolean enable(int diskNumber) throws ClassNotFoundException, SQLException {

        String llamadaSQL = "{CALL tarea2.sp_enable_disk(?)}";

        Connection conn = getSQLConnection();

        CallableStatement callState = conn.prepareCall(llamadaSQL);
        callState.setString(1, String.valueOf(diskNumber));

        try {

            callState.execute();

            return true;

        } catch (SQLException e) {
            System.out.println("Exception reported. disable disk failure detected X");
            return false;
        }

    }

}
