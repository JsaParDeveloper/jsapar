package exp.jsapar.bucket;

public class Bucket {
	// Class to implement a bucket for use by client code to store BucketItems.
	// These bucket items are stored in an event that is fired to the client
	// code the moment that the parser (or any other tool) is faced with read
	// lines that cannot be processed normally by the parser. These lines are
	// then one-by-one, wrapped in a BucketListItem and fired within an event to
	// the client code.
	// The client code implements a BucketEventListener and receives these
	// BucketItems that contains suppressed lines that could not be processed.
	// A BucketItem contains information about the source, like from which tool
	// did this BucketItem come from, what file was being read, which line is
	// suppressed in this file, the line itself, etc.
	// This information is needed when the client code processes more than one
	// file (in a batch of files) and the user must/can accept inaccuracies
	// which causes tool to become a bit less error prone.
	
	// TODO work out this idea more.

}
