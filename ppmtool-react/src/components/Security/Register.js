import React from "react";
import * as registrationActions from "./../../redux/actions/SecurityActions";
import { PropTypes } from "prop-types";
import { connect } from "react-redux";
import { bindActionCreators } from 'redux';

class Register extends React.Component {

    constructor(props) {
        super(props);

        this.state = {
            username: "",
            firstName: "",
            lastName: "",
            password: "",
            confirmPassword: "",
            errors: {}
        };
    }

    componentWillReceiveProps(nextProps) {
        if (nextProps.errors) {
            this.setState({ errors: nextProps.errors })
        }
    }

    onChange = (e) => {
        this.setState({ [e.target.name]: e.target.value });
    }

    onSubmit = (e) => {
        e.preventDefault();
        let newUser = {
            firstName: this.state.firstName,
            lastName: this.state.lastName,
            username: this.state.username,
            password: this.state.password,
            confirmPassword: this.state.confirmPassword,
        }
        this.props.actions.createNewUser(newUser, this.props.history);
    }

    render() {
        const { errors, firstName, lastName, username, password, confirmPassword } = this.state;

        return (
            <div className="register">
                <div className="container">
                    <div className="row">
                        <div className="col-md-8 m-auto">
                            <h1 className="display-4 text-center">Sign Up</h1>
                            <p className="lead text-center">Create your Account</p>
                            <form onSubmit={this.onSubmit}>
                                <div className="form-group">
                                    <input
                                        type="text"
                                        className="form-control form-control-lg"
                                        placeholder="First Name"
                                        name="firstName"
                                        value={firstName}
                                        onChange={this.onChange}
                                    />
                                    {errors.firstName && (<div className="invalid-feedback d-block">{errors.firstName}</div>)}
                                </div>
                                <div className="form-group">
                                    <input
                                        type="text"
                                        className="form-control form-control-lg"
                                        placeholder="Last Name"
                                        name="lastName"
                                        value={lastName}
                                        onChange={this.onChange}
                                    />
                                    {errors.lastName && (<div className="invalid-feedback d-block">{errors.lastName}</div>)}
                                </div>
                                <div className="form-group">
                                    <input
                                        type="email"
                                        className="form-control form-control-lg"
                                        placeholder="Email Address (username)"
                                        name="username"
                                        value={username}
                                        onChange={this.onChange}
                                    />
                                    {errors.username && (<div className="invalid-feedback d-block">{errors.username}</div>)}
                                </div>
                                <div className="form-group">
                                    <input
                                        type="password"
                                        className="form-control form-control-lg"
                                        placeholder="Password"
                                        name="password"
                                        value={password}
                                        onChange={this.onChange}
                                    />
                                    {errors.password && (<div className="invalid-feedback d-block">{errors.password}</div>)}
                                </div>
                                <div className="form-group">
                                    <input
                                        type="password"
                                        className="form-control form-control-lg"
                                        placeholder="Confirm Password"
                                        name="confirmPassword"
                                        value={confirmPassword}
                                        onChange={this.onChange}
                                    />
                                    {errors.confirmPassword && (<div className="invalid-feedback d-block">{errors.confirmPassword}</div>)}
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

Register.propTypes = {
    createNewUser: PropTypes.func.isRequired,
    errors: PropTypes.object.isRequired
}

const mapStateToProps = state => ({
    errors: state.errors
});

const mapDispatchToProps = dispatch => ({
    actions: bindActionCreators(registrationActions, dispatch)
})

export default connect(mapStateToProps, mapDispatchToProps)(Register);
