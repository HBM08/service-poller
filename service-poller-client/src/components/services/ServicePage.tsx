import React, { Component } from 'react';
import ServiceList from './ServiceList';
import { toast } from "react-toastify";
import Spinner from '../common/Spinner';
import { Redirect } from 'react-router';
import { deleteService, getServices } from '../../api/serviceApi';

interface Metadata {
  createdAt: string;
  lastUpdatedAt: string;
}

export interface Service {
  id: string;
  serviceName: string;
  serviceURL: string;
  metadata: Metadata;
  status: string;
}

interface ServicePageProps {
}

interface ServicePageState {
  services: Array<Service>;
  isLoading: boolean;
  redirectToAddServicePage: boolean;
}

class ServicePage extends Component<ServicePageProps, ServicePageState> {

  constructor(props: ServicePageProps) {
    super(props);

    this.state = {
      services: [],
      isLoading: false,
      redirectToAddServicePage: false
    };
  }

  componentDidMount() {
    this.setState({isLoading: true});

    getServices().then(data => this.setState({services: data, isLoading: false}));

    const eventSource = new EventSource('http://localhost:8080/services/sse/status'); 
    eventSource.onopen = (event: any) => console.log('open', event); 
    eventSource.onmessage = (event: any) => {
      const statusEvent = JSON.parse(event.data); 
      console.log(statusEvent)
      if (statusEvent !== undefined) {
        this.state.services.filter(s => s.id === statusEvent.id).map(s => s.status = statusEvent.status);
        this.setState({services: this.state.services}); 
      }
    };
    // eventSource.onerror = (event: any) => console.log('error', event);
  }

  handleDeleteService = (service: Service) => {
    const { services } = this.state;

    deleteService(service.id)
      .then(success => {
        toast.success("Service deleted");
        this.setState({services: services.filter(s => s.id !== service.id)});
      })
      .catch(err => toast.error("Delete failed. ", { autoClose: false }))
  };

  render() {
    const {services, isLoading} = this.state;

    if (isLoading) {
      return <Spinner />;
    }

    return (
      <>
        {this.state.redirectToAddServicePage && <Redirect to="/service" />}
        <h2>Service List</h2>
        {isLoading ? (
          <Spinner />
        ) : (
          <>
            <button
              style={{ marginBottom: 20 }}
              className="btn btn-primary"
              onClick={() => this.setState({ redirectToAddServicePage: true })}
            >
              Add Service
            </button>
            <ServiceList services={services} onDeleteClick={this.handleDeleteService} />
          </>)}
      </>
    );
  }
}

export default ServicePage;