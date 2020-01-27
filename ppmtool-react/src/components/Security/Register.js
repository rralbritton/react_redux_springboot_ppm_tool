import React from 'react'
import { createNewUser } from "./../../redux/actions/SecurityActions";
import { PropTypes } from "prop-types";
import { connect } from "react-redux";

export default class Register extends React.Component {

    constructor(props) {
        super(props);

        this.state = {
            username: "",
            fullName: "",
            password: "",
            confirmPassword: "",
            errors: {}
        };
    }

    onChange = (e) => {
        this.setState({ [e.target.name]: e.target.value });
    }

    onSubmit = (e) => {
        e.preventDefault();
        let newUser = {
            username: this.state.username,
            fullName: this.state.fullName,
            password: this.state.password,
            confirmPassword: this.state.confirmPassword,
        }
        this.props.createNewUser(newUser, this.props.history);
    }

    render() {
        return (
            <div className="register">
                <div className="container">
                    <div className="row">
                        <div className="col-md-8 m-auto">
                            <h1 className="display-4 text-center">Sign Up</h1>
                            <p className="lead text-center">Create your Account</p>
                            <form action="create-profile.html">
                                <div className="form-group">
                                    <input
                                        type="text"
                                        className="form-control form-control-lg"
                                        placeholder="Name"
                                        name="fullName"
                                        value={this.state.fullName}
                                        onChange={this.onChange}
                                    />
                                </div>
                                <div className="form-group">
                                    <input
                                        type="email"
                                        className="form-control form-control-lg"
                                        placeholder="Email Address"
                                        name="username"
                                        value={this.state.username}
                                        onChange={this.onChange}
                                    />
                                </div>
                                <div className="form-group">
                                    <input
                                        type="password"
                                        className="form-control form-control-lg"
                                        placeholder="Password"
                                        name="password"
                                        value={this.state.password}
                                        onChange={this.onChange}
                                    />
                                </div>
                                <div className="form-group">
                                    <input
                                        type="password"
                                        className="form-control form-control-lg"
                                        placeholder="Confirm Password"
                                        name="confirmPassword"
                                        value={this.state.confirmPassword}
                                        onChange={this.onChange}
                                    />
                                </div>
                                <input type="submit" className="btn btn-info btn-block mt-4" />
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        )
    }
}
