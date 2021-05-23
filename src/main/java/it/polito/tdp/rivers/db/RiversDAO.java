package it.polito.tdp.rivers.db;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import it.polito.tdp.rivers.model.Flow;
import it.polito.tdp.rivers.model.River;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class RiversDAO {

	public List<River> getAllRivers() {
		
		final String sql = "SELECT id, name "
				+ "FROM river ";

		List<River> rivers = new LinkedList<River>();

		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();

			while (res.next()) {
				rivers.add(new River(res.getInt("id"), res.getString("name")));
			}

			conn.close();
			
		} catch (SQLException e) {
			//e.printStackTrace();
			throw new RuntimeException("SQL Error");
		}

		return rivers;
	}
	
	public List<Flow> getAllFlows(Map<Integer, River> idMap){
		final String sql= "SELECT day, flow, river "
				+ "FROM flow";
		
		List<Flow> flows = new LinkedList<Flow>();
		
		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();

			while (res.next()) {
				River river = idMap.get(res.getInt("river"));
				Flow flow = new Flow(res.getDate("day").toLocalDate(), res.getDouble("flow"), river);
				flows.add(flow);
				river.addFlow(flow);
			}

			conn.close();
			
		} catch (SQLException e) {
			//e.printStackTrace();
			throw new RuntimeException("SQL Error");
		}
		
		return flows;
		
	}
	
	
}
