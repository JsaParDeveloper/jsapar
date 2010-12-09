package exp.jsapar;

import java.math.BigDecimal;
import java.util.Date;

import exp.jsapar.annotations.Cell;
import exp.jsapar.annotations.Line;

@Line
public class UserClass {
	@Cell
	private String name = null;
	@Cell
	protected Date date = null;
	@Cell(name="largenumber")
	public BigDecimal bigDecimal = null;
	@Cell // how to get the property name of primitive?
	int age = 40;
	
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public BigDecimal getBigDecimal() {
		return bigDecimal;
	}
	public void setBigDecimal(BigDecimal bigDecimal) {
		this.bigDecimal = bigDecimal;
	}
}