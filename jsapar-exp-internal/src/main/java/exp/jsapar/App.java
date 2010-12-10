package exp.jsapar;

import exp.jsapar.filters.CellFilter;
import exp.jsapar.filters.MyElement;

public class App {

    /**
     * @param args
     */
    public static void main(String[] args) {
        // TODO use this method for stand alone operations in the jsapar-lib project!
        runApp();
    }

    public static void runApp() {
        MyElement element = new MyElement();
        element.printList();
        CellFilter filter = new CellFilter(Integer.class);
        filter.range(); // FIXME use restriction for this!
        element.addFilters(filter);
    }
}
