package com.websofttechs.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.websofttechs.beans.Experience;
import com.websofttechs.dbcon.DBConnection;

public class ExperienceDao {
	public boolean addNewExperience(Experience exp, int empId) {
		boolean status = false;
		Connection con = null;

		try {
			con = DBConnection.getConnection();

			con.setAutoCommit(false);

			PreparedStatement ps = con.prepareStatement(
					"INSERT INTO experience(employee_id, company_name, role, date_of_joining, last_date) VALUES(?,?,?,?,?)");
			ps.setInt(1, empId);
			ps.setString(2, exp.getCompanyName());
			ps.setString(3, exp.getRole());
			ps.setString(4, exp.getDateOfJoining());
			ps.setString(5, exp.getLastDate());

			int count = ps.executeUpdate();

			if (count > 0) {
				con.commit();
				status = true;
			}
		} catch (Exception e) {
			try {
				con.rollback();
				status = false;
			} catch (Exception e2) {
				status = false;
				e2.printStackTrace();
			}
			status = false;
			e.printStackTrace();
		}

		return status;
	}
	
	public List<Experience> getAllExperience(int empId) {
		List<Experience> list = new ArrayList<Experience>();
		Experience exp = null;
		Connection con = null;
		
		try {
			con = DBConnection.getConnection();
			
			PreparedStatement ps = con.prepareStatement("SELECT * FROM experience WHERE employee_id=?");
			ps.setInt(1, empId);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {				
				exp = new Experience();
				
				exp.setId(rs.getInt("id"));
				exp.setEmployeeId(rs.getInt("employee_id"));
				exp.setCompanyName(rs.getString("company_name"));
				exp.setRole(rs.getString("role"));
				exp.setDateOfJoining(rs.getString("date_of_joining"));
				exp.setLastDate(rs.getString("last_date"));
				
				list.add(exp);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	public boolean deleteExperience(int expId) {
		boolean status = false;
		Connection con = null;
		
		try {
			con = DBConnection.getConnection();
			
			con.setAutoCommit(false);
			
			PreparedStatement ps = con.prepareStatement("DELETE FROM experience WHERE id=?");
			ps.setInt(1, expId);
			
			int count = ps.executeUpdate();
			
			if(count > 0) {
				status = true;
				con.commit();
			}
		} catch (Exception e) {
			try {
				status = false;
				con.rollback();
			} catch (Exception e2) {
				status = false;
				e2.printStackTrace();
			}
			status = false;
			e.printStackTrace();
		}
		
		return status;
	}
}
