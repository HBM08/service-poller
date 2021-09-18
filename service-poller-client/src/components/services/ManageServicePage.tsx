import { History } from "history";
import React, { useEffect, useReducer, useState } from "react";
import { useHistory, useParams } from "react-router-dom";
import Spinner from "../common/Spinner";
import ServiceForm from "./SerivceForm";
import { Service } from "./ServicePage";
import { toast } from "react-toastify";
import { getServiceById, saveService } from "../../api/serviceApi";

type ManageServicePageProps = {
  service: Service;
  services: Service[];
  history: History;
}

interface RouteParams {
  slug: string
}

export interface Errors {
  serviceName: string;
  serviceURL: string;
  onSave: string;
};

let emptyService: Service = {
  id: "",
  serviceName: "",
  serviceURL: "",
  metadata: {
    createdAt: "",
    lastUpdatedAt: ""
  },
  status: "Fetching..."
}


export function ManageServicePage({ services }: ManageServicePageProps) {
  const [service, setService] = useState(emptyService);
  const [errors, setErrors] = useState<Errors>({ serviceName: "", serviceURL: "", onSave: "" });
  const [isLoading, setIsLoading] = useState(false);
  const [saving, setSaving] = useState(false);
  const [_, forceUpdate] = useReducer((x) => x + 1, 0);

  let history = useHistory();
  let { slug } = useParams<RouteParams>();

  useEffect(() => {
    if (slug !== undefined) {
      setIsLoading(true);
      getServiceById(slug)
        .then(data => { 
          setService(data);
          setIsLoading(false);
        })
    }

  }, [slug]);

  function handleChange(event: React.ChangeEvent<HTMLInputElement>) {
    const { name, value } = event.target;
    setErrors(prevErrors => ({
      ...prevErrors,
      [name]: ""
    }));
    
    if (name === "serviceURL") {
      if (!event.target.checkValidity()) {
        errors.serviceURL = "Invalid URL";
        setErrors(errors);
      }
    }
    setService(prevService => ({
      ...prevService,
      [name]: value
    }));
  }

  function formIsValid() {
    debugger
    const { serviceName, serviceURL } = service;

    if (!serviceName) errors.serviceName = "Service name is required.";
    if (!serviceURL) errors.serviceURL = "Service URL is required";

    setErrors(errors);
    // Form is valid if the errors object has all properties empty strings
    let isValid = true;
    Object.values(errors).forEach(value => {
      if (value !== "" && value !== null && value !== undefined) {
        isValid = false;
      }
    })
    return isValid;
  }

  function handleSave(event: React.FormEvent<HTMLFormElement>) {
    event.preventDefault();
    if (formIsValid()) {
      setSaving(true);
      saveService(service)
      .then(() => {
        toast.success("Service saved.");
        history.push("/services");
      })
      .catch(error => {
        setSaving(false);
        errors.onSave = error.message;
        setErrors(errors);
      });
    } else {
      forceUpdate();
    }
  }

  return isLoading ? (
    <Spinner />
  ) : (
    <ServiceForm
      service={service}
      errors={errors}
      onChange={handleChange}
      onSave={handleSave}
      saving={saving}
    />
  );
}