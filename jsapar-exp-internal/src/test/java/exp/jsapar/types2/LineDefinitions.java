package exp.jsapar.types2;

import java.math.BigDecimal;
import java.util.Date;

import exp.jsapar.annotations.Cell;
import exp.jsapar.annotations.Line;
import exp.jsapar.utils.DateUtil;

public class LineDefinitions {
    private static LineDefinitions ld = new LineDefinitions();

    public static LineDefinitions getLineDefinitions() {
        return ld;
    }

    // ------------------------------------------------------------------------

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

    // ------------------------------------------------------------------------

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

    // ------------------------------------------------------------------------

    // no @Line annotation here!
    class LineUserClass3 {
        @Cell
        private String credtiCardNumber;
        @Cell
        private String creditCardOwner;

        private boolean blocked = false;

        public String getCredtiCardNumber() {
            return credtiCardNumber;
        }

        public void setCredtiCardNumber(String credtiCardNumber) {
            this.credtiCardNumber = credtiCardNumber;
        }

        public String getCreditCardOwner() {
            return creditCardOwner;
        }

        public void setCreditCardOwner(String creditCardOwner) {
            this.creditCardOwner = creditCardOwner;
        }

        public boolean isBlocked() {
            return blocked;
        }

        public void setBlocked(boolean blocked) {
            this.blocked = blocked;
        }
    }

    // ------------------------------------------------------------------------

    @Line
    class LineUserClass4 {
        // no @Cell annotations here!
        private String document;
        private String file;
        private String archive;

        public String getDocument() {
            return document;
        }

        public void setDocument(String document) {
            this.document = document;
        }

        public String getFile() {
            return file;
        }

        public void setFile(String file) {
            this.file = file;
        }

        public String getArchive() {
            return archive;
        }

        public void setArchive(String archive) {
            this.archive = archive;
        }
    }

    // ------------------------------------------------------------------------

    // no annotations at all!
    class LineUserClass5 {
        private boolean useless = true;

        public void setUseless(boolean useless) {
            this.useless = useless;
        }

        public boolean isUseless() {
            return useless;
        }
    }

    // ------------------------------------------------------------------------

    @Line
    class LineUserClass6 {
        @Cell
        private String user = "alfa";
        @Cell
        private Double money = 342.99;
        // no annotation!
        private boolean hasCreditcard = false;

        public LineUserClass6() {
        }

        public LineUserClass6(String user, Double money, boolean hasCard) {
            this.user = user;
            this.money = money;
            this.hasCreditcard = hasCard;
        }

        public String getUser() {
            return user;
        }

        public void setUser(String user) {
            this.user = user;
        }

        public Double getMoney() {
            return money;
        }

        public void setMoney(Double money) {
            this.money = money;
        }

        public boolean isHasCreditcard() {
            return hasCreditcard;
        }

        public void setHasCreditcard(boolean hasCreditcard) {
            this.hasCreditcard = hasCreditcard;
        }
    }
    
 // ------------------------------------------------------------------------
    
    @Line
    class LineUserClass7 {
        @Cell(name="firstName")
        private String name;
        @Cell(name="period")
        private int age;
        
        public LineUserClass7(String name, int age) {
            this.name = name;
            this.age = age;
        }
        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
        public int getAge() {
            return age;
        }
        public void setAge(int age) {
            this.age = age;
        }
    }
}