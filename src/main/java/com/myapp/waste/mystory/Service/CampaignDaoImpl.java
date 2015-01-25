package com.myapp.waste.mystory.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

public class CampaignDaoImpl {
	private JdbcTemplate jdbcTemplate;

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}


	public int insertCampaign(Campaign cmp) {

		String smt = "Insert into campaign(username,heading,purpose,category,location,city,date) Values(?,?,?,?,?,?,?)";
		return jdbcTemplate.update(smt,new Object[]{cmp.getUsername(),cmp.getHeading(),cmp.getPurpose(),
				cmp.getCategory(),cmp.getLocation(),cmp.getCity(),cmp.getDate()});
	}

	public List<Campaign> selectCampaigns(String query) {
		String smt;
		if(query.isEmpty())
			smt = "Select * from campaign";
		else
			smt = "Select * from campaign WHERE city LIKE '%"+query+"%'"
					+ " OR heading LIKE '% "+query+" %' OR heading LIKE '%"+query+" %' "
					+ " OR heading LIKE '% "+query+"%' "
					+ " OR category LIKE '% "+query+" %' OR category LIKE '%"+query+" %'"
					+ " OR category LIKE '% "+query+"%'";
		List<Campaign> campaign = this.jdbcTemplate.query(smt,new RowMapper<Campaign>(){
			public Campaign mapRow(ResultSet rs, int rowNum) throws SQLException {
				Campaign cmp = new Campaign(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),
						rs.getString(6),rs.getString(7),rs.getString(8));
				return cmp;
			}
		});
		return campaign;
	}

	//Getting Campaign details
	public Campaign getCampaignDetails(int idcampaign) {
		String smt = "Select * from campaign where idcampaign='"+idcampaign+"'";
		List<Campaign> campaign = this.jdbcTemplate.query(smt, new RowMapper<Campaign>(){
			public Campaign mapRow(ResultSet rs, int rowNum) throws SQLException {
				Campaign cmp = new Campaign(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),
						rs.getString(6),rs.getString(7),rs.getString(8));
				return cmp;
			}
		});
		return campaign.get(0);
	}

	//Checking whether user already joined the campaign
	public int checkIfJoined(int idcampaign,String username) {
		String smt = "Select count(*) from volunteer where idcampaign='"+idcampaign+"' AND username ='"+username+"'";
		return this.jdbcTemplate.queryForInt(smt);
	}
	
	//Adding user to campaign volunteered in database
	public int joincampaign(int idcampaign,String username){
		String smt = "Insert into volunteer Values('"+idcampaign+"','"+username+"')";
		return jdbcTemplate.update(smt);
	}

	public List<Campaign> myactivity(String username)
	{
		String smt = "Select * from campaign where idcampaign IN (Select idcampaign from volunteer where username ='"+username+"')";
		List<Campaign> myactivity = this.jdbcTemplate.query(smt, new RowMapper<Campaign>(){
			public Campaign mapRow(ResultSet rs, int rowNum) throws SQLException {
				Campaign cmp = new Campaign(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),
						rs.getString(6),rs.getString(7),rs.getString(8));
				return cmp;
			}
		});
		return myactivity;
	}
	
	public List<Campaign> mycampaigns(String username)
	{
		String smt = "Select * from campaign where username ='"+username+"'";
		List<Campaign> mycampaigns = this.jdbcTemplate.query(smt, new RowMapper<Campaign>(){
			public Campaign mapRow(ResultSet rs, int rowNum) throws SQLException {
				Campaign cmp = new Campaign(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),
						rs.getString(6),rs.getString(7),rs.getString(8));
				return cmp;
			}
		});
		return mycampaigns;
	}

	public List<String> getVolunteers(int idcampaign){
		String smt = "Select username from volunteer where idcampaign = '"+idcampaign+"'";
		List<String> volunteerList =  this.jdbcTemplate.query(smt, new RowMapper<String>(){
			@Override
			public String mapRow(ResultSet rs, int arg1) throws SQLException{
				return rs.getString(1);
			}
		});
		return volunteerList;
	}


}
