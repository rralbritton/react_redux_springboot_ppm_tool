import React from "react";
import { PropTypes } from "prop-types";
import { connect } from "react-redux";
import { bindActionCreators } from "redux";
import Project from "./Project/Project";
import CreateProjectButton from "./Project/CreateProjectButton";
import * as projectActions from "../redux/actions/ProjectActions";
import Spinner from "./Utils/Spinner";

class Dashboard extends React.Component {
  constructor(props) {
    super(props);

    this.state = {
    }
  }

  componentDidMount() {
    this.props.actions.getAllUserProjects();
  }



  render() {
    const { projects, user } = this.props;

    if (!projects || projects === undefined || !user || user === undefined) {
      return (
        <Spinner />
      )
    }

    return (
      <div>
        <div className="projects">
          <div className="container">
            <div className="row">
              <div className="col-md-12">
                <h1 className="display-4 text-center">{user.firstName} {user.lastName}'s Projects</h1>
              </div>
            </div>
            <hr />
            <CreateProjectButton />
            <div className="row">
              <div className="col=md-4">
                {projects.length > 0 ? projects.map(project => (
                  <Project project={project} />
                )) : ""}
              </div>
            </div>
          </div>
        </div>
      </div>
    );
  }
}

Dashboard.propTypes = {
  errors: PropTypes.object.isRequired,
  user: PropTypes.object.isRequired,
  projects: PropTypes.object.isRequired
}

const mapStateToProps = state => ({
  errors: state.errors,
  user: state.user.payload,
  projects: state.projects.payload
});

const mapDispatchToProps = dispatch => ({
  actions: bindActionCreators(projectActions, dispatch)
});

export default connect(mapStateToProps, mapDispatchToProps)(Dashboard);
