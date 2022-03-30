package it.polito.tdp.lab04.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Studente;


public class StudenteDAO {
	
	public Studente getStudentebyMatricola(int matricola) {
		String sql="select s.matricola, s.nome, s.cognome, s.CDS "
				+ "from studente "
				+ "where s.matricola=?";
		Studente studente=null;
		try {
			Connection conn=ConnectDB.getConnection();
			PreparedStatement st=conn.prepareStatement(sql);
			st.setInt(1, matricola); 
			ResultSet rs=st.executeQuery();
			
			while(rs.next()) {
				
				studente=new Studente(rs.getInt("matricola"), rs.getString("cognome"),
						rs.getString("nome"), rs.getString("CDS"));
			}
			st.close();
			rs.close();
			conn.close();
			return studente;
		
		
		} catch(SQLException e) {
			System.err.println("Errore nel DAO");
			e.printStackTrace();
			return null;
		}
	}
	
public List<Corso> getCorsiStudente(int matricola){
	String sql="select c.codins, c.crediti, c.nome, c.pd "
			+ "from corso c, studente s, iscrizione i "
			+ "where c.codins=i.codins and i.matricola=s.matricola and s.matricola=? ";
	List <Corso> list= new LinkedList<>();
	
	try {
		Connection conn=ConnectDB.getConnection();
		PreparedStatement st=conn.prepareStatement(sql);
		st.setInt(1, matricola); 
		ResultSet rs=st.executeQuery();
		
		while(rs.next()) {
			
			String codins = rs.getString("codins");
			int numeroCrediti = rs.getInt("crediti");
			String nome = rs.getString("nome");
			int periodoDidattico = rs.getInt("pd");
			list.add(new Corso(codins, numeroCrediti, nome, periodoDidattico));
		}
		st.close();
		rs.close();
		conn.close();
		return list;
	
	
	} catch(SQLException e) {
		System.err.println("Errore nel DAO");
		e.printStackTrace();
		return null;
	}
	}

}
