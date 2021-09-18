package com.kry.servicepollerserver.model.metadata;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
import java.util.Objects;

public class Metadata {
    @CreatedDate
    private LocalDateTime createdAt;

    @CreatedBy
    private String createdBy;

    @LastModifiedDate
    private LocalDateTime lastUpdatedAt;

    @LastModifiedBy
    private String lastUpdatedBy;

    public Metadata() {
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public LocalDateTime getLastUpdatedAt() {
        return lastUpdatedAt;
    }

    public void setLastUpdatedAt(LocalDateTime lastUpdatedAt) {
        this.lastUpdatedAt = lastUpdatedAt;
    }

    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Metadata metadata = (Metadata) o;
        return Objects.equals(createdAt, metadata.createdAt) && Objects.equals(createdBy, metadata.createdBy)
                && Objects.equals(lastUpdatedAt, metadata.lastUpdatedAt) && Objects.equals(lastUpdatedBy,
                metadata.lastUpdatedBy);
    }

    @Override
    public int hashCode() {
        return Objects.hash(createdAt, createdBy, lastUpdatedAt, lastUpdatedBy);
    }

    @Override
    public String toString() {
        return "Metadata{" + "createdAt=" + createdAt + ", createdBy='" + createdBy + '\'' + ", lastUpdatedAt="
                + lastUpdatedAt + ", lastUpdatedBy='" + lastUpdatedBy + '\'' + '}';
    }
}
