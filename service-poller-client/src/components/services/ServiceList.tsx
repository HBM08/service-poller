import React from "react";
import { Link } from "react-router-dom";
import { Service } from "./ServicePage";

type ServiceListProps = {
  services: Service[];
  onDeleteClick: (service: Service) => void;
}

const ServiceList = ({ services, onDeleteClick }: ServiceListProps) => (
  <table className="table">
    <thead>
      <tr>
        <th>Service Name</th>
        <th>URL</th>
        <th>Created at</th>
        <th>Last modified</th>
        <th>Status</th>
        <th/>
      </tr>
    </thead>
    <tbody>
      {services.map((service:Service) => {
        return (
          <tr key={service.id}>
            <td>
              <Link to={"/service/" + service.id}>{service.serviceName}</Link>
            </td>
            <td>{service.serviceURL}</td>
            <td>{service.metadata.createdAt}</td>
            <td>{service.metadata.lastUpdatedAt}</td>
            <td>{service.status !== undefined ? service.status : "Fetching..."}</td>
            <td>
              <button
                className="btn btn-outline-danger"
                onClick={() => onDeleteClick(service)}
              >
                Delete
              </button>
            </td>
          </tr>
        );
      })}
    </tbody>
  </table>
);

export default ServiceList;
