package javaFiles;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmpDao {

	public static Connection getConnection(){  
        Connection conn = null;
        try {
            String url = "jdbc:sqlite:employee.db";
            conn = DriverManager.getConnection(url);
            
            System.out.println("Connection to SQLite has been established.");
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
		return conn;  
    }  
	
	
    public static int save(Emp e){  
        int status=0;  
        try { 
        	Connection con = EmpDao.getConnection();
            PreparedStatement ps=con.prepareStatement(  
                         "insert into employeeList(name,password,email,country) values(?,?,?,?)");  
            ps.setString(1,e.getName());  
            ps.setString(2,e.getPassword());  
            ps.setString(3,e.getEmail());  
            ps.setString(4,e.getCountry());  
              
            status=ps.executeUpdate();  
            con.close();
        }catch(Exception ex){ex.printStackTrace();}  
          
        return status;  
    }  
    
    
    public static int update(Emp e){  
        int status=0;  
        try(Connection con=EmpDao.getConnection()) {  
            PreparedStatement ps=con.prepareStatement(  
                         "update employeeList set name=?,password=?,email=?,country=? where id=?");  
            ps.setString(1,e.getName());  
            ps.setString(2,e.getPassword());  
            ps.setString(3,e.getEmail());  
            ps.setString(4,e.getCountry());  
            ps.setInt(5,e.getId());  
              
            status=ps.executeUpdate();  
              
        }catch(Exception ex){ex.printStackTrace();}  
          
        return status;  
    }  
    
    
    public static int delete(int id){  
        int status=0;  
        try(Connection con=EmpDao.getConnection()) {  
            PreparedStatement ps=con.prepareStatement("delete from employeeList where id=?");  
            ps.setInt(1,id);  
            status=ps.executeUpdate();  
              
        }catch(Exception e){e.printStackTrace();}  
          
        return status;  
    }  
    
    
    public static Emp getEmployeeById(int id){  
        Emp e=new Emp();  
          
        try(Connection con=EmpDao.getConnection()) {  
            PreparedStatement ps=con.prepareStatement("select * from employeeList where id=?");  
            ps.setInt(1,id);  
            ResultSet rs=ps.executeQuery();  
            if(rs.next()){  
                e.setId(rs.getInt(1));  
                e.setName(rs.getString(2));  
                e.setPassword(rs.getString(3));  
                e.setEmail(rs.getString(4));  
                e.setCountry(rs.getString(5));  
            }  
            
        }catch(Exception ex){ex.printStackTrace();}  
          
        return e;  
    }  
    public static List<Emp> getAllEmployees(){  
        List<Emp> list=new ArrayList<Emp>();  
          
        try(Connection con=EmpDao.getConnection()) {  
            PreparedStatement ps=con.prepareStatement("select * from employeeList");  
            ResultSet rs=ps.executeQuery();  
            while(rs.next()){  
                Emp e=new Emp();  
                e.setId(rs.getInt(1));  
                e.setName(rs.getString(2));  
                e.setPassword(rs.getString(3));  
                e.setEmail(rs.getString(4));  
                e.setCountry(rs.getString(5));  
                list.add(e);  
            }  
             
        }catch(Exception e){e.printStackTrace();}  
          
        return list;  
    }
	
}
