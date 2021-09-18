package com.kry.servicepollerserver.model.metadata;

import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;

public abstract class MetadataDocument implements Persistable {
    private Metadata _metadata = new Metadata();

    public MetadataDocument() {
    }

    @Transient
    public Metadata getMetadata() {
        return this._metadata;
    }

    /** @deprecated */
    @Deprecated
    public void setMetadata(final Metadata metadata) {
        this._metadata = metadata;
    }

    @Transient
    public boolean isNew() {
        return this._metadata == null || this._metadata.getCreatedAt() == null;
    }

    public abstract Object getId();
}
