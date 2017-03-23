package main;

import java.util.Calendar;
import java.util.List;

public class Request {

	private String api;
	private Calendar dateObs;
	private int dureeEnJours;
	private String variableWhere;
	private String valeurWhere;
//	private List<String> variableWhere;
//	private List<String> valeurWhere;
	
	
//	public Request(String api, Calendar dateObs, int dureeEnJours, List<String> variableWhere,
//			List<String> valeurWhere) {
//		super();
//		this.api = api;
//		this.dateObs = dateObs;
//		this.dureeEnJours = dureeEnJours;
//		this.variableWhere = variableWhere;
//		this.valeurWhere = valeurWhere;
//	}

	public String getVariableWhere() {
		return variableWhere;
	}

	public void setVariableWhere(String variableWhere) {
		this.variableWhere = variableWhere;
	}

	public String getValeurWhere() {
		return valeurWhere;
	}

	public void setValeurWhere(String valeurWhere) {
		this.valeurWhere = valeurWhere;
	}

	public String getApi() {
		return api;
	}

	public void setApi(String api) {
		this.api = api;
	}

	public Calendar getDateObs() {
		return dateObs;
	}

	public void setDateObs(Calendar dateObs) {
		this.dateObs = dateObs;
	}

	public int getDureeEnJours() {
		return dureeEnJours;
	}

	public void setDureeEnJours(int dureeEnJours) {
		this.dureeEnJours = dureeEnJours;
	}

//	public List<String> getVariableWhere() {
//		return variableWhere;
//	}
//
//	public void setVariableWhere(List<String> variableWhere) {
//		this.variableWhere = variableWhere;
//	}
//
//	public List<String> getValeurWhere() {
//		return valeurWhere;
//	}
//
//	public void setValeurWhere(List<String> valeurWhere) {
//		this.valeurWhere = valeurWhere;
//	}
}