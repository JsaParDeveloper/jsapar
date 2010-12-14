package exp.jsapar.lists;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import exp.jsapar.filters.Filter;
import exp.jsapar.filters.FilterChain;
import exp.jsapar.filters.LineFilter;
import exp.jsapar.types2.Line;

/**
 * Implements a LineList, which contains two types of interfaces: List and FilterableList. The List
 * implementation is used to interface to the external world and make the LineList modifiable. The
 * FiterableList implementation is used to interface to the external world for filtering purposes
 * only, and not giving the user the control to change anything in the filtered list. The filtered
 * results can be used by the user to get knowledge of the elements contained within the complete
 * list. The information received from the filtered list can be used to change the complete list.
 * 
 * TODO maybe we should implement it like this: the complete list and filtered list are both
 * synchronized to each other within ONE class, but have separate package-private classes of their
 * own. This way we can have one public list, the CELLLIST, and two package-private sub lists
 * ALLCELLLIST and FILTERCELLLIST. The ALLCELLLIST and FILTERCELLLIST must be internally
 * synchronized to keep both lists accurate. Next, the normal iterators must be overridden
 * (iterator() and listIterator()) because the normal iterators in the lists cannot be used, due to
 * the fact that the hasNext(), next() and remove() methods DO have effect on both the lists. The
 * remove() method in the iterator for the ALLCELLLIST has influence on the elements in the
 * FILTERCELLLIST and vice versa.
 * 
 * By overriding the default lists iterators, we can program such behavior.
 * 
 * @author JsaPar Developer
 */
public class LineList implements List<Line>, FilterableList<Line> {
    /**
     * The list containing the lines.
     */
    private List<Line> lines = null;

    /**
     * The list of filters applicable for the line list.
     */
    private FilterChain<LineFilter> filterChain = new FilterChain<LineFilter>();

    // ------------------------------------------------------------------------

    /**
     * Constructs a LineList.
     */
    public LineList() {
        lines = new ArrayList<Line>();
    }

    // ------------------------------------------------------------------------

    // ------------------------------------------------------------------------
    // List<Line> interface method implementations
    // ------------------------------------------------------------------------

    @Override
    public boolean add(Line line) {
        lines.add(line);
        return true;
        // TODO can a LineList contain same Lines?
        // if (!contains(line)) {
        // lines.add(line);
        // return true;
        // } else {
        // throw new IllegalArgumentException("Section already contains a line ");
        // }
    }

    @Override
    public int size() {
        // TODO check if filters apply? should the filtered size be returned or
        // the normal size?
        return lines.size();
    }

    @Override
    public boolean isEmpty() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean contains(Object o) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public Iterator<Line> iterator() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Object[] toArray() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <T> T[] toArray(T[] a) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean remove(Object o) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends Line> c) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean addAll(int index, Collection<? extends Line> c) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void clear() {
        // TODO Auto-generated method stub

    }

    @Override
    public Line get(int index) {
        return lines.get(index);
    }

    @Override
    public Line set(int index, Line element) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void add(int index, Line element) {
        lines.add(index, element);
    }

    @Override
    public Line remove(int index) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int indexOf(Object o) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int lastIndexOf(Object o) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public ListIterator<Line> listIterator() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ListIterator<Line> listIterator(int index) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Line> subList(int fromIndex, int toIndex) {
        // TODO Auto-generated method stub
        return null;
    }

    // ------------------------------------------------------------------------
    // FilterableList<Line> interface method implementations
    // ------------------------------------------------------------------------

    @Override
    public void addFilter(Filter... filter) {
        // TODO Auto-generated method stub

    }

    @Override
    public void removeFilter(String filterName) {
        // TODO Auto-generated method stub

    }

    @Override
    public void activateFiltering() {
        // TODO Auto-generated method stub

    }

    @Override
    public void deactivateFiltering() {
        // TODO Auto-generated method stub

    }

    @Override
    public boolean isFilteringActive() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void inverseFiltering() {
        // TODO Auto-generated method stub

    }

    @Override
    public int filteredSize() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public boolean isFilteredEmpty() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean containsFiltered(Object o) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void clearFiltered() {
        // TODO Auto-generated method stub

    }

    @Override
    public boolean containsAllFiltered(Collection<?> c) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public Object[] toArrayFiltered() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <T> T[] toArrayFiltered(T[] a) {
        // TODO Auto-generated method stub
        return null;
    }

    // ------------------------------------------------------------------------
    // LineIterator<Line> interface method implementations
    // ------------------------------------------------------------------------

    /**
     * TODO
     */
    private class LineIteratorImpl implements Iterator<Line> {

        @Override
        public boolean hasNext() {
            // TODO Auto-generated method stub
            return false;
        }

        @Override
        public Line next() {
            // TODO Auto-generated method stub
            return null;
        }

        @Override
        public void remove() {
            // TODO Auto-generated method stub

        }

    }

    // ------------------------------------------------------------------------
    // LineListIterator<Line> interface method implementations
    // ------------------------------------------------------------------------

    /**
     * TODO
     */
    private class LineListIteratorImpl implements ListIterator<Line> {

        @Override
        public boolean hasNext() {
            // TODO Auto-generated method stub
            return false;
        }

        @Override
        public Line next() {
            // TODO Auto-generated method stub
            return null;
        }

        @Override
        public boolean hasPrevious() {
            // TODO Auto-generated method stub
            return false;
        }

        @Override
        public Line previous() {
            // TODO Auto-generated method stub
            return null;
        }

        @Override
        public int nextIndex() {
            // TODO Auto-generated method stub
            return 0;
        }

        @Override
        public int previousIndex() {
            // TODO Auto-generated method stub
            return 0;
        }

        @Override
        public void remove() {
            // TODO Auto-generated method stub

        }

        @Override
        public void set(Line e) {
            // TODO Auto-generated method stub

        }

        @Override
        public void add(Line e) {
            // TODO Auto-generated method stub

        }

    }
}