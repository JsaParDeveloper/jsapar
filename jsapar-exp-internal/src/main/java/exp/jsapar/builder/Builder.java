package exp.jsapar.builder;

import exp.jsapar.schema.JsaParSchema;

public abstract class Builder {
    protected JsaParSchema jsaparSchema = null;

    protected Builder(JsaParSchema schema) {
        this.jsaparSchema = schema;
    }
}