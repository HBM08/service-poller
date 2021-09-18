import React from "react";
import TextInput from "../common/TextInput";
import { Service } from "./ServicePage";
type ServiceFormProps = {
  service: Service;
  onSave: (event: React.FormEvent<HTMLFormElement>) => void;
  onChange: (event: React.ChangeEvent<HTMLInputElement>) => void;
  saving: boolean;
  errors: {
    serviceName: string;
    serviceURL: string;
    onSave: string;
  };
}

const ServiceForm = ({service, onSave, onChange, saving, errors} : ServiceFormProps) => {

  return ( 
    <form onSubmit={onSave} style={{width : 500}}>
      <h2>{service.id ? "Edit" : "Add"} Service</h2>
      {errors.onSave && (
        <div className="alert alert-danger" role="alert">
          {errors.onSave}
        </div>
      )}
      <TextInput
        type="text"
        name="serviceName"
        label="Service Name"
        value={service.serviceName}
        placeholder="Some service name"
        onChange={onChange}
        error={errors.serviceName}
      />

      <TextInput
        type="url"
        name="serviceURL"
        label="URL"
        value={service.serviceURL}
        placeholder="Some valid URL"
        onChange={onChange}
        error={errors.serviceURL}
      />

      <button type="submit" style={{ marginTop: 20 }} disabled={saving} className="btn btn-primary">
        {saving ? "Saving..." : "Save"}
      </button>
    </form>
  );
};

export default ServiceForm;
