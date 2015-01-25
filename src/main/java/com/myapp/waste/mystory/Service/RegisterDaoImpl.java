package com.myapp.waste.mystory.Service;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import javax.sql.rowset.serial.SerialException;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.web.multipart.MultipartFile;

import com.mysql.jdbc.Blob;

public class RegisterDaoImpl {
	JdbcTemplate jdbcTemplate;

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	//Insert new user into table
	public int registration(Register reg)
	{
		String smt = "INSERT INTO USERS(username,first_name,last_name,password,enabled) VALUES(?,?,?,?,?)";
		String smt2 = "INSERT INTO AUTHORITIES(username,authority) VALUES('"+reg.getUsername()+"','ROLE_USER')";
		String smt3 = "INSERT INTO profiles(username,first_name,last_name) VALUES(?,?,?)";
		jdbcTemplate.update(smt, new Object[]{reg.getUsername(),reg.getFirst_name(),reg.getLast_name(),
				reg.getPassword(),reg.getEnabled()});
		jdbcTemplate.update(smt3, new Object[]{reg.getUsername(),reg.getFirst_name(),reg.getLast_name()});
		return jdbcTemplate.update(smt2);

	}

	//Function to check if username already exists
	public boolean userExists(String username){
		String smt = "Select username from USERS WHERE username = '"+username+"'";
		List<String> userList = this.jdbcTemplate.query(smt, new RowMapper<String>(){
			@Override
			public String mapRow(ResultSet rs, int arg1) throws SQLException {
				String tmp = rs.getString(1);
				return tmp;
			}
		});
		if(userList.isEmpty())
			return true;
		else
			return false;
	}
	
	//Getting user profile from database
	public Profile userProfile(String username){
		String smt = "Select * from profiles WHERE username = '"+username+"'";
		Profile userDetails = this.jdbcTemplate.queryForObject(smt, new RowMapper<Profile>(){
			@Override
			public Profile mapRow(ResultSet rs, int arg1) throws SQLException {
				Profile tmp = new Profile(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),
						rs.getInt(6),rs.getString(7),rs.getString(8),rs.getInt(9),rs.getString(10));
				return tmp;
			}
		});
		return userDetails;
	}
	
	
	public int uploadProfilePic(String fileName,String username) throws IOException, SerialException, SQLException{
		String smt = "UPDATE profiles SET profilepicpath='"+fileName+"' where username='"+username+"'";
		return jdbcTemplate.update(smt);
	}
	
	public int updateUserProfile(String first_name,String username,String last_name,String usercity,String country,String interest) throws IOException, SerialException, SQLException{
		String smt = "UPDATE profiles SET first_name='"+first_name+"',last_name='"+last_name+"',"
				+ "city='"+usercity+"',country='"+country+"',interest='"+interest+"' where username='"+username+"'";
		return jdbcTemplate.update(smt);
	}

}
