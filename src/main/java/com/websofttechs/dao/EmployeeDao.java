package com.websofttechs.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.websofttechs.beans.Employee;
import com.websofttechs.dbcon.DBConnection;

public class EmployeeDao {
	public boolean registerEmployee(Employee emp) {
		boolean status = false;
		Connection con = null;

		try {
			con = DBConnection.getConnection();

			con.setAutoCommit(false);

			PreparedStatement ps1 = con.prepareStatement("SELECT * from employee WHERE email=?");
			ps1.setString(1, emp.getEmail());

			ResultSet rs = ps1.executeQuery();
			if (!rs.next()) {
				PreparedStatement ps2 = con.prepareStatement(
						"INSERT INTO employee(name, phone, email, password, status) VALUES(?,?,?,?,?)");
				ps2.setString(1, emp.getName());
				ps2.setString(2, emp.getPhone());
				ps2.setString(3, emp.getEmail());
				ps2.setString(4, emp.getPassword());
				ps2.setBoolean(5, emp.isStatus());

				int count = ps2.executeUpdate();

				if (count > 0) {
					con.commit();
					status = true;
				}
			}

		} catch (Exception e) {
			try {
				status = false;
				con.rollback();
			} catch (SQLException e2) {
				status = false;
				e2.printStackTrace();
			}
			status = false;
			e.printStackTrace();
		}

		return status;
	}

	public Employee loginEmployee(String email, String password) {
		Employee emp = null;

		Connection con = null;

		try {
			con = DBConnection.getConnection();

			PreparedStatement ps = con.prepareStatement("SELECT * from employee WHERE email=? AND password=?");
			ps.setString(1, email);
			ps.setString(2, password);

			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				emp = new Employee();
				emp.setId(rs.getInt("id"));
				emp.setName(rs.getString("name"));
				emp.setPhone(rs.getString("phone"));
				emp.setEmail(rs.getString("email"));
				emp.setStatus(rs.getBoolean("status"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return emp;
	}
}
