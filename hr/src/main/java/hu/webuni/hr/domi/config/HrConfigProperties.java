package hu.webuni.hr.domi.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "hr")
@Component
public class HrConfigProperties {

	private Salary salary = new Salary();

	public Salary getSalary() {
		return salary;
	}

	public void setSalary(Salary sallary) {
		this.salary = sallary;
	}

	public static class Salary {

		private Def def = new Def();
		private Smart smart = new Smart();

		public Def getDef() {
			return def;
		}

		public void setDef(Def def) {
			this.def = def;
		}

		public Smart getSmart() {
			return smart;
		}

		public void setSmart(Smart smart) {
			this.smart = smart;
		}

	}

	public static class Def {

		private int percent;

		public int getPercent() {
			return percent;
		}

		public void setPercent(int percent) {
			this.percent = percent;
		}

	}

	public static class Smart {

		private Def def = new Def();

		private Map<Integer, Year> years = new HashMap<>();

		public Map<Integer, Year> getYears() {
			return years;
		}

		public Def getDef() {
			return def;
		}

		public void setDef(Def def) {
			this.def = def;
		}

	}

	public static class Year {

		private float limit;
		private int percent;

		public float getLimit() {
			return limit;
		}

		public int getPercent() {
			return percent;
		}

		public void setLimit(float limit) {
			this.limit = limit;
		}

		public void setPercent(int percent) {
			this.percent = percent;
		}

		@Override
		public String toString() {
			return "Year [limit=" + limit + ", percent=" + percent + "]";
		}

	}

}
