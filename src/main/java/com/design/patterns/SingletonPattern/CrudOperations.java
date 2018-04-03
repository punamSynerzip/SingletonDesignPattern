package com.design.patterns.SingletonPattern;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Statement;

public class CrudOperations {
	
	SingletonDatabaseConnection INSTANCE = SingletonDatabaseConnection.getInstance();
	
	Connection connection = INSTANCE.getConnection();
	
	PreparedStatement ps = null;
	ResultSet rs = null;
	
	public void getData(int id) throws SQLException {

		try {
			if(id > 0 ){
				ps = connection.prepareStatement("select * from emp_tbl where emp_id = ?");
				ps.setInt(1, id);
			}else if(id == 0){
				ps = connection.prepareStatement("select * from emp_tbl");
			}
			
			rs = ps.executeQuery();
			System.out.printf("%-20s%-20s%-20s%-20s%s\n","Emp Id","Firstname","Surname", "Position", "Salary");
			System.out.println("-----------------------------------------------------------------------------------------");
			
			while(rs.next()){		 
				System.out.printf("%-20s%-20s%-20s%-20s%s\n",rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
			}	
		} catch (Exception e) {
			System.out.println("Exception occured while accessing employee data " + e);
		} finally {
			if(rs!=null){  
                rs.close();  
            }if (ps!=null){  
              ps.close();  
            }
		}
	}
	
	public int insertData(String firstName, String surName, String position, int salary) throws SQLException {
		PreparedStatement ps = null;
		ResultSet rs = null;
		int recordCounter = 0;
		try {
			ps = connection.prepareStatement("insert into emp_tbl(emp_firstname, emp_surname, emp_position, emp_salary) values(?,?,?,?)",
					Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, firstName);
			ps.setString(2, surName);
			ps.setString(3, position);
			ps.setInt(4, salary);
			recordCounter = ps.executeUpdate();
			rs = ps.getGeneratedKeys();
			if((rs != null) && (rs.next())){
				System.out.println("Generated employee id is: "+ rs.getInt(1));
			}
		} catch (Exception e) {
			System.out.println("Exception occured while inserting employee data " + e);
		} finally {
			 if (ps!=null){  
                 ps.close();  
             }if(rs!=null){  
                 rs.close();  
             } 
		}
		return recordCounter;
	}
	
	public int updateData(int id, String position) throws SQLException {
		PreparedStatement ps = null;
		
		int recordCounter = 0;
		try {
			ps = connection.prepareStatement("update emp_tbl set emp_position = ? where emp_id = ?");
			ps.setString(1, position);
			ps.setInt(2, id);
			recordCounter = ps.executeUpdate();
		} catch (Exception e) {
			System.out.println("Exception occured while updating employee data " + e);
		} finally {
			 if (ps!=null){  
                 ps.close();  
             }
		}
		return recordCounter;
	}
	
	public int deleteData(int id) throws SQLException {
		PreparedStatement ps = null;
		
		int recordCounter = 0;
		try {
			ps = connection.prepareStatement("delete from emp_tbl where emp_id = ?");
			ps.setInt(1, id);
			recordCounter = ps.executeUpdate();
		} catch (Exception e) {
			System.out.println("Exception occured while updating employee data " + e);
		} finally {
			 if (ps!=null){  
                 ps.close();  
             } 
		}
		return recordCounter;			
	}

	
}


