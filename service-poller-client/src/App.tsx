import React from 'react';
import { Route, Switch } from "react-router-dom";
import './App.css';
import { ToastContainer } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";
import PageNotFound from './components/PageNotFound';
import { ManageServicePage } from './components/services/ManageServicePage';
import ServicePage from './components/services/ServicePage';

function App() {
  return (
    <div className="container-fluid">
      <Switch>
        <Route exact path="/" component={ServicePage} />
        <Route path="/services" component={ServicePage} />
        <Route path="/service/:slug" component={ManageServicePage} />
        <Route path="/service" component={ManageServicePage} />
        <Route component={PageNotFound} />
      </Switch>
      <ToastContainer autoClose={3000} hideProgressBar />
    </div>
  );
}

export default App;
