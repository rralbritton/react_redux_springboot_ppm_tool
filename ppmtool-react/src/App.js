import React, { Component } from "react";

/*CSS - ORDER MATTERS
Files below bootstrap will over ride bootstrap configs*/
import "bootstrap/dist/css/bootstrap.min.css";
import "./css/App.css";

import { BrowserRouter as Router, Route } from "react-router-dom";
import { Provider } from "react-redux";
import configureStore from "./redux/store"
import jwtDecode from "jwt-decode";

/*PUBLIC*/
import Header from "./components/Layout/Header";
import Landing from "./components/Layout/Landing";
import Register from "./components/Security/Register";
import Login from "./components/Security/Login";

/*PRIVATE*/
import AddProject from "./components/Project/AddProject";
import Dashboard from "./components/Dashboard";
import setJwtToken from "./components/Security/securityUtils";
import { logoutUser } from "./redux/actions/SecurityActions";

const store = configureStore();

//Check JWT Token
const jwtToken = localStorage.getItem("jwtToken")

if (jwtToken) {
  setJwtToken(jwtToken) //sets headers
  const decodedToken = jwtDecode(jwtToken);
  const currentTime = Date.now() / 1000;

  //check if token is still current
  if (decodedToken.exp < currentTime) {
    //if time is expired - log user out
    store.dispatch(logoutUser());
  } else {
    //set user in store
    store.dispatch({ type: "SET_USER", payload: decodedToken })
    store.dispatch({ type: "VERIFY_USER", payload: true });
    store.dispatch({ type: "STORE_TOKEN", payload: jwtToken });
  }
}

class App extends Component {
  render() {
    return (
      <Provider store={store}>
        <Router>
          <div className="container-fluid">
            <div className="App">

              <Header />

              <Route exact path="/" render={props => <Landing {...props} />} />
              <Route exact path="/login" render={props => <Login {...props} />} />
              <Route exact path="/register" render={props => <Register {...props} />} />

              <Route exact path="/dashboard" render={props => <Dashboard {...props} />} />
              <Route exact path="/addProject" render={props => <AddProject {...props} />} />

            </div>
          </div>
        </Router>
      </Provider>
    );
  }
}

export default App;
