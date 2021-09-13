package com.kry.servicepollerserver.model;

import com.kry.servicepollerserver.model.metadata.MetadataDocument;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Objects;

@Document
public class ServiceReference extends MetadataDocument {

    @Id
    private String id;

    private String serviceName;

    private String serviceURL;

    public ServiceReference() {
    }

    public ServiceReference(String id, String serviceName, String serviceURL) {
        this.id = id;
        this.serviceName = serviceName;
        this.serviceURL = serviceURL;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getServiceURL() {
        return serviceURL;
    }

    public void setServiceURL(String serviceURL) {
        this.serviceURL = serviceURL;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        ServiceReference that = (ServiceReference) o;
        return Objects.equals(id, that.id) && serviceName.equals(that.serviceName) && serviceURL.equals(
                that.serviceURL);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, serviceName, serviceURL);
    }

    @Override
    public String toString() {
        return "ServiceReference{" + "id=" + id + ", serviceName='" + serviceName + '\'' + ", serviceURL='" + serviceURL
                + '\'' + '}';
    }
}
