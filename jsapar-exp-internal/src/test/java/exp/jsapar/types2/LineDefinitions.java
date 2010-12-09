package exp.jsapar.types2;

import java.math.BigDecimal;
import java.util.Date;

import exp.jsapar.annotations.Cell;
import exp.jsapar.annotations.Line;
import exp.jsapar.utils.DateUtil;

public class LineDefinitions {
	private static LineDefinitions ld = new LineDefinitions();

	public static LineUserClass1 getLineUserClass1() {
		return ld.new LineUserClass1();
	}

	@Line
	class LineUserClass1 {
		@Cell
		private String name;
		@Cell
		protected Date date;
		@Cell(name = "largenumber")
		BigDecimal bigDecimal;
		@Cell
		public int age;

		LineUserClass1() {
			this.name = "just.a.test";
			this.date = DateUtil.now();
			this.bigDecimal = new BigDecimal("16");
			this.age = 40;
		}

		LineUserClass1(String name, Date date, BigDecimal bigDecimal, int age) {
			this.name = name;
			this.date = date;
			this.bigDecimal = bigDecimal;
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

		public int getAge() {
			return age;
		}

		public void setAge(int age) {
			this.age = age;
		}
	}

	@Line
	class LineUserClass2 {
		@Cell
		private String[] names = { "hello", "world" };
		@Cell
		public int[] lotteryNumbers = { 3, 45, 21, 56, 7, 9, 12 };

		LineUserClass2() {
			// Intentionally left blank.
		}

		LineUserClass2(String[] names, int[] lotteryNumbers) {
			this.names = names;
			this.lotteryNumbers = lotteryNumbers;
		}

		public String[] getNames() {
			return names;
		}

		public void setNames(String[] names) {
			this.names = names;
		}

		public int[] getLotteryNumbers() {
			return lotteryNumbers;
		}

		public void setLotteryNumbers(int[] lotteryNumbers) {
			this.lotteryNumbers = lotteryNumbers;
		}
	}
}