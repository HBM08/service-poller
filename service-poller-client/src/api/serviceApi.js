import { handleResponse, handleError } from "./apiUtils";
const baseUrl = "/services/";

export function getServices() {
  return fetch(baseUrl)
    .then(handleResponse)
    .catch(handleError);
}

export function getServiceById(id) {
  debugger
  return fetch(baseUrl + id)
    .then(handleResponse)
    .catch(handleError);
}

export function saveService(service) {
  return fetch(baseUrl + (service.id || ""), {
    method: service.id ? "PUT" : "POST", // POST for create, PUT to update when id already exists.
    headers: { "content-type": "application/json" },
    body: JSON.stringify(service)
  })
    .then(handleResponse)
    .catch(handleError);
}

export function deleteService(serviceId) {
  return fetch(baseUrl + serviceId, { method: "DELETE" });
}
