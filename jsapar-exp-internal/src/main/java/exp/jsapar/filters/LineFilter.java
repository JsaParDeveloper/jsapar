package exp.jsapar.filters;

// how to restrict the filter from being used in the wrong context
// like: a linefilter used in a paragraph!
public class LineFilter extends Filter {
	// this filter is used in to line context, and should be included in the
	// Line object
	// as a chain of linefilters List<LineFilter> filterChain

	// when the line object is iterated, the list of cells is iterated. But
	// before the iterator moves, the filterChain is run through.
	// should be really go through the list every time the iterator moves, or 
	// should we build a composite structure?
}
