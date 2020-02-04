import axios from "axios";
import { GET_ERRORS } from "./Types";

export const getAllUserProjects = () => {
    return dispatch => {
        return axios.get("/api/project/all")
            .then(results => {
                dispatch({ type: "GET_ALL_USER_PRJS", payload: results.data })
            }).catch(err => {
                dispatch({ type: "GET_ERRORS", payload: err.response.data })
            })
    }
}

export const getUserProjectByPrjId = (prjId, history) => {
    return dispatch => {
        return axios.get(`/api/project/${prjId}`)
            .then(results => {
                dispatch({ type: "GET_PRJ_BY_ID", payload: results.data })
                history.push(`/project/${prjId}`);
            }).catch(err => {
                dispatch({ type: "GET_ERRORS", payload: err.response.data })
            })
    }
}

export const createProject = (project, history) => {
    return dispatch => {
        return axios.post("/api/project/", project)
            .then(results => {
                dispatch({ type: "CREATE_NEW_PRJ_SUCCESS", payload: results.data });
                history.push("/dashboard");
            }).catch(err => {
                dispatch({ type: "GET_ERRORS", payload: err.response.data })
            })
    }
}

export const deleteProjectById = (prjId, history) => {
    return dispatch => {
        return axios.delete(`/api/project/${prjId}`)
            .then(results => {
                dispatch({ type: "DELETE_PRJ_SUCCESS", payload: results.data })
                history.push("/dashboard");
            }).catch(err => {
                dispatch({ type: "GET_ERRORS", payload: err.response.data })
            })
    }
}

