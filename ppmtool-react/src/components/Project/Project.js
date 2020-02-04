import React from "react";
import { ListGroup, ListGroupItem, Card } from 'react-bootstrap';

const Project = props => {

  const { project } = props;

  return (
    <React.Fragment>
      <Card style={{ width: '18rem' }}>
        <Card.Body>
          <Card.Title> {project.projectName}</Card.Title>
          <Card.Text>
            {project.description}
          </Card.Text>
        </Card.Body>
        <ListGroup variant="flush">
          <ListGroupItem>View</ListGroupItem>
          <ListGroupItem>Delete</ListGroupItem>
        </ListGroup>

      </Card>
    </React.Fragment>
  );
}

export default Project;