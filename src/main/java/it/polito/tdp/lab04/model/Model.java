package it.polito.tdp.lab04.model;

import java.util.LinkedList;
import java.util.List;

import it.polito.tdp.lab04.DAO.CorsoDAO;
import it.polito.tdp.lab04.DAO.StudenteDAO;

public class Model {
	
	private CorsoDAO corsoDao;
	private StudenteDAO studenteDao;
	
	public Model () {
		this.corsoDao= new CorsoDAO();
		this.studenteDao= new StudenteDAO();
	}
	
	public List<Corso> getTuttiICorsi() {
		
		return this.corsoDao.getTuttiICorsi();
	}
	
	public Studente getStudenteByMatricola(int matricola) {
		return this.studenteDao.getStudentebyMatricola(matricola);
	}
	
	public List <Studente> getStudentibyCorso(Corso c){
			
		return this.corsoDao.getStudentiIscrittiAlCorso(c) ;
	}
	
	public Corso getCorso(String c) {
		return this.corsoDao.getCorso(c);
	}
	
	public List<Corso> getCorsiStudente(int matricola){
		return this.studenteDao.getCorsiStudente(matricola);
	}
	
	public boolean isIscrittoAlCorso(int matricola, Corso corso) {
		List<Corso> list=this.getCorsiStudente(matricola);
		if(list.contains(corso)) {
			return true;
		} else {
			return false;
		}
	}

}
