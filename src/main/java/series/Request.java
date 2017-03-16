package series;
import java.util.Calendar;

public class Request {

	private String api;
	private Calendar dateObs;
	private int dureeEnJours;
	private String variableWhere;
	private String valeurWhere;
	
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
}
