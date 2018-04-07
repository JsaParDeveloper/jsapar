package org.jsapar.concurrent;

import org.jsapar.Text2TextConverter;
import org.jsapar.TextComposer;
import org.jsapar.convert.AbstractConverter;
import org.jsapar.parse.text.TextParseConfig;
import org.jsapar.parse.text.TextParseTask;
import org.jsapar.schema.Schema;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;

/**
 * A multi threaded version of {@link org.jsapar.Text2TextConverter} where the composer is started in a separate worker
 * thread.
 * See {@link AbstractConverter} for details about error handling and manipulating data.
 * <p>
 * As a rule of thumb while working with normal files on disc, don't use this concurrent version unless your input
 * normally exceeds at least 1MB of data, as the overhead of starting
 * a new thread and synchronizing threads are otherwise greater than the gain by the concurrency.
 *
 * @see ConcurrentConvertTask
 * @see org.jsapar.Text2TextConverter
 */
public class ConcurrentText2TextConverter extends Text2TextConverter {
    private ConcurrentConvertTaskFactory convertTaskFactory = new ConcurrentConvertTaskFactory();

    public ConcurrentText2TextConverter(Schema parseSchema, Schema composeSchema) {
        super(parseSchema, composeSchema);
    }

    public ConcurrentText2TextConverter(Schema parseSchema, Schema composeSchema, TextParseConfig parseConfig) {
        super(parseSchema, composeSchema, parseConfig);
    }

    /**
     * @param reader The reader to read input from
     * @param writer The writer to write converted result to.
     * @return Number of converted lines.
     * @throws IOException In case of IO error
     */
    public long convert(Reader reader, Writer writer) throws IOException {
        return execute(convertTaskFactory.makeConvertTask(makeParseTask(reader), makeComposer(writer)));
    }

    /**
     * Each registered onStart runnable will be called in the same order that they were registered by consumer thread
     * when it starts up but before it starts handling any event. Use this in order to
     * implement initialization needed for the new
     * thread.
     *
     * @param onStart The runnable that will be called by consumer thread when starting up.
     */
    public void registerOnStart(Runnable onStart) {
        this.convertTaskFactory.registerOnStart(onStart);
    }

    /**
     * Each registered onStop runnable will be called in the same order that they were registered by consumer
     * thread just before it dies. Use this in order to
     * implement resource deallocation etc. These handlers are called also when the thread is terminated with an exception so
     * be aware that you may end up here also when a serious error has occurred.
     *
     * @param onStop The runnable that will be called by consumer thread when stopping.
     */
    public void registerOnStop(Runnable onStop) {
        this.convertTaskFactory.registerOnStop(onStop);
    }

}
