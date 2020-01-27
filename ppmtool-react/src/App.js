import React, { Component } from "react";
import "./App.css";
import "bootstrap/dist/css/bootstrap.min.css";
import { BrowserRouter as Router, Route } from "react-router-dom";
import { Provider } from "react-redux";
import configureStore from "./redux/store"
/*PUBLIC*/
import Header from "./components/Layout/Header";
import Landing from "./components/Layout/Landing";
import Register from "./components/Security/Register";
import Login from "./components/Security/Login";
/*PRIVATE*/
import AddProject from "./components/Project/AddProject";
import Dashboard from "./components/Dashboard";


const store = configureStore();

class App extends Component {
  render() {
    return (
      <Provider store={store}>
        <Router>
          <div className="App">
            <Header />

            <Route exact path="/" render={props => <Landing {...props} />} />
            <Route exact path="/login" render={props => <Login {...props} />} />
            <Route exact path="/register" render={props => <Register {...props} />} />

            <Route exact path="/dashboard" render={props => <Dashboard {...props} />} />
            <Route exact path="/addProject" render={props => <AddProject {...props} />} />

          </div>
        </Router>
      </Provider>
    );
  }
}

export default App;
