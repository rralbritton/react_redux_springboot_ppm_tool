import React from "react";
import { Link } from "react-router-dom";

/*Functional Component*/
const CreateProjectButton = () => {
  return (
    <React.Fragment>
      <Link to="/addProject" className="btn btn-lg btn-secondary mb-4">
        Create New Project
      </Link>
    </React.Fragment>
  );
};

export default CreateProjectButton;