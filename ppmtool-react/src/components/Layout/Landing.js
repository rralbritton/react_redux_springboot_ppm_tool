import React from "react";
import Link from "react-router-dom";

export default class Landing extends React.Component {
    render() {
        return (
            <div className="landing">
                <div className="landing-text-box">
                    <div className="row">
                        <div className="col-md-12 text-center">
                            <h1 className="ow">Project Management Tool</h1>
                            <h3>Create your account to join active projects or start your own </h3>
                            <hr />
                            <a href="/register" className="btn btn-lg btn-primary mr-2">Sign Up</a>
                            <a href="/login" className="btn btn-lg btn-secondary mr-2">Login</a>
                        </div>
                    </div>
                </div>
            </div >

        )
    }
}
