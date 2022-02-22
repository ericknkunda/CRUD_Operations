/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package students_management_app;
import java.awt.Dimension;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

//class
public class StudentsDB {
    JTable table;
     Connection connection;
     Statement statement;
     String[] columnNames ={"STUDENT NUMBER","REG_NO","FACULT","YEARL"};
     public StudentsDB(){
         table =new JTable();
         
     }
    public void createConn(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/students?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root",null );
            System.out.println("Connection established");
            
        }
        catch(SQLException ex){
            ex.printStackTrace();
            
        } catch (ClassNotFoundException ex) {
             Logger.getLogger(StudentsDB.class.getName()).log(Level.SEVERE, null, ex);
         }
        ///return connection;
    }
    
     
    public void InsertData(StudentModel model) throws SQLException{
        try{
            createConn();
            PreparedStatement ps =connection.prepareStatement("INSERT INTO `university`(`Registration`, `Facult`, `Year`)"
                    + "VALUES(?,?,?)");
            ps.setString(1, model.getREG_NO());
            ps.setString(2, model.getFacult());
            ps.setString(3, model.getYearOfStudy());
            ps.executeUpdate();
            connection.close();
        }
        catch(Exception ex){
            ex.printStackTrace();
        }   
        }
        
        
    
public boolean updateUser(StudentModel model) throws SQLException {
		boolean rowUpdated =true;
		try{
                    PreparedStatement statement = connection.prepareStatement("UPDATE university set Registration = ?,Facult= ?, Year =? WHERE Registration = ?;");
                        
			statement.setString(1, model.getREG_NO());
			statement.setString(2, model.getFacult());
			statement.setString(3, model.getYearOfStudy());
			

			rowUpdated = statement.executeUpdate() > 0;
		}
                catch(Exception ex){
                    ex.printStackTrace();
                }
		return rowUpdated;
	}
//    public boolean DeleteQuerry(String reg){
//        String querry ="DELETE FROM university WHERE Regitration ="+reg+";";
//        return executeSet(querry);
//    }
    public ResultSet executeGet(String querry){
        ResultSet resultset =null;
        try{ 
            resultset =statement.executeQuery(querry);
            
        }
        catch(SQLException exc){
            exc.printStackTrace();
        }
        return resultset;
    }
    public List<StudentModel> selectAllUsers()throws SQLException {
        createConn();
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(columnNames);
        table.setModel(model);
        table.setPreferredScrollableViewportSize(new Dimension(300, 100));
        table.setFillsViewportHeight(true);
        JScrollPane pane =new JScrollPane(table);
        int num ;
        String reg ="";
        String fac ="";
        String year ="";
		List<StudentModel> users = new ArrayList<>();
		try{
                    createConn();
                    PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM university"); 
		    ResultSet rs = preparedStatement.executeQuery();
                        while (rs.next()) {
				num = rs.getInt("StudentNum");
				reg = rs.getString("Registration");
				 fac = rs.getString("Facult");
				 year = rs.getString("Year");
                                 model.addRow(new Object[]{num,reg,fac,year});
				users.add(new StudentModel(reg, fac, year));
			}
                    
                }
                catch(Exception exc){
                  exc.printStackTrace();
                }
		return users;
	}

    
}
