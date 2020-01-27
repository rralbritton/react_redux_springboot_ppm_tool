import React from "react";
import Link from "react-router-dom";

export default class Landing extends React.Component {
    render() {
        return (
            <div className="landing">
                <div className="light-overlay landing-inner text-dark">
                    <div className="container">
                        <div className="row">
                            <div className="col-md-12 text-center">
                                <h1 className="display-3 mb-4">Personal Project Management Tool</h1>
                                <p className="lead">
                                    Create your account to join active projects or start your own
                            </p>
                                <hr />
                                <a href="/register" className="btn btn-lg btn-primary mr-2">
                                    Sign Up
                            </a>
                                <a href="/login" className="btn btn-lg btn-secondary mr-2">
                                    Login
                            </a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        )
    }
}
