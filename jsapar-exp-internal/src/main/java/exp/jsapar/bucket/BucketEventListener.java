package exp.jsapar.bucket;

import java.util.EventListener;

public interface BucketEventListener extends EventListener {

	public void bucketItemEvent(BucketItemEvent bucketItemEvent);
}
