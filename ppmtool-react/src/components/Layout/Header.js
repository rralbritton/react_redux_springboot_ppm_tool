import React from "react";
import { Link } from "react-router-dom"
import PropTypes from "prop-types";
import { connect } from "react-redux";
import * as loginActions from "./../../redux/actions/SecurityActions";
import { bindActionCreators } from "redux";

class Header extends React.Component {

  render() {
    const { user } = this.props;

    if (!user || user === undefined) {
      return (
        <nav className="navbar navbar-expand-sm navbar-dark bg-primary mb-4">
          <div className="container">
            <Link className="navbar-brand" to="/">
              Project Management Tool
      </Link>
            <button
              className="navbar-toggler"
              type="button"
              data-toggle="collapse"
              data-target="#mobile-nav"
            >
              <span className="navbar-toggler-icon" />
            </button>
          </div>
        </nav>
      )
    } else {
      return (
        <nav className="navbar navbar-expand-sm navbar-dark bg-primary mb-4">
          <div className="container">
            <Link className="navbar-brand" to="/">
              Project Management Tool
          </Link>
            <button
              className="navbar-toggler"
              type="button"
              data-toggle="collapse"
              data-target="#mobile-nav"
            >
              <span className="navbar-toggler-icon" />
            </button>

            <div className="collapse navbar-collapse" id="mobile-nav">
              <ul className="navbar-nav mr-auto">
                <li className="nav-item">
                  <span className="prj-nav-link">Welcome {user.firstName} {user.lastName}</span>
                </li>
              </ul>

              <ul className="navbar-nav ml-auto">
                <li className="nav-item">
                  <Link className="prj-nav-link nav-link" to="/dashboard">
                    Dashboard
              </Link>

                </li>
                <li className="nav-item">
                  <Link className="nav-link" to="/" onClick={() => this.props.actions.logoutUser()}>
                    Logout
                  </Link>
                </li>
              </ul>
            </div>

          </div>
        </nav>
      )
    }
  }
}

Header.propTypes = {
  user: PropTypes.object.isRequired,
}

const mapStateToProps = state => ({
  user: state.user.payload,
});

const mapDispatchToProps = dispatch => ({
  actions: bindActionCreators(loginActions, dispatch)
});

export default connect(mapStateToProps, mapDispatchToProps)(Header);