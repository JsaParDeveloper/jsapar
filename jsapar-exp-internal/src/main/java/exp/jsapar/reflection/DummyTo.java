package exp.jsapar.reflection;

public class DummyTo {
	private String name;
    private String address;
   
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public DummyTo(String name, String address) {
        this.name = name;
        this.address = address;
    }

    public DummyTo() {
        this.name = new String();
        this.address = new String();
    }

    public String toString(String appendBefore)
    {
        return appendBefore+" "+name+" , "+address;
    }
}
