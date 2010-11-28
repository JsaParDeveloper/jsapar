package exp.jsapar.lists;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import exp.jsapar.filters.Filter;
import exp.jsapar.filters.FilterChain;
import exp.jsapar.filters.ParagraphFilter;
import exp.jsapar.types2.Paragraph;

/**
 * TODO
 * 
 * @author JsaPar Developer
 * 
 * @param <Paragraph>
 *            TODO
 */
public class ParagraphList implements List<Paragraph>,
		FilterableList<Paragraph> {

	/**
	 * The list containing the lines.
	 */
	private List<Paragraph> paragraphs = null;

	/**
	 * The list of filters applicable for the paragraph list.
	 */
	private FilterChain<ParagraphFilter> filterChain = new FilterChain<ParagraphFilter>();

	/**
	 * Constructs a LineList.
	 */
	public ParagraphList() {
		paragraphs = new ArrayList<Paragraph>();
	}

	// ------------------------------------------------------------------------
	// FilterableList<Paragraph> interface method implementations
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

	// ------------------------------------------------------------------------
	// List<Paragraph> interface method implementations
	// ------------------------------------------------------------------------

	@Override
	public int size() {
		// TODO check if filters apply? should the filtered size be returned or
		// the normal size?
		return paragraphs.size();
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
	public Iterator<Paragraph> iterator() {
		// TODO return an implementation of ParagraphIteratorImpl
		// TODO what is the difference between the Iterator and ListIterator
		// definitions?

		// What is the difference b/w Iterator & ListIterator
		// Iterator : Enables you to cycle through a collection,obtaining or
		// removing elements
		// ListIterator :It extends Iterator allow bidirectional traversal of
		// list & the modification of element
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
	public boolean add(Paragraph e) {
		// TODO Auto-generated method stub
		return false;
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
	public boolean addAll(Collection<? extends Paragraph> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addAll(int index, Collection<? extends Paragraph> c) {
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
	public Paragraph get(int index) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Paragraph set(int index, Paragraph element) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void add(int index, Paragraph element) {
		// TODO Auto-generated method stub

	}

	@Override
	public Paragraph remove(int index) {
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
	public ListIterator<Paragraph> listIterator() {
		// TODO return an implementation of ParagraphIteratorImpl
		return null;
	}

	@Override
	public ListIterator<Paragraph> listIterator(int index) {
		// TODO return an implementation of ParagraphIteratorImpl
		return null;
	}

	@Override
	public List<Paragraph> subList(int fromIndex, int toIndex) {
		// TODO Auto-generated method stub
		return null;
	}

	// ------------------------------------------------------------------------
	// ParagraphIterator<Paragraph> interface method implementations
	// ------------------------------------------------------------------------

	/**
	 * TODO
	 */
	private class ParagraphIteratorImpl implements Iterator<Paragraph> {

		@Override
		public boolean hasNext() {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public Paragraph next() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public void remove() {
			// TODO Auto-generated method stub

		}

	}

	// ------------------------------------------------------------------------
	// ParagraphListIterator<Paragraph> interface method implementations
	// ------------------------------------------------------------------------

	/**
	 * TODO
	 */
	private class ParagraphListIteratorImpl implements ListIterator<Paragraph> {

		@Override
		public boolean hasNext() {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public Paragraph next() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public boolean hasPrevious() {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public Paragraph previous() {
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
		public void set(Paragraph e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void add(Paragraph e) {
			// TODO Auto-generated method stub

		}

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
}