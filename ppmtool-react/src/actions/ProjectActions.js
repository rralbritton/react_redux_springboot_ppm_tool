import axios from "axios";
import { GET_ERRORS } from "./Types";

export const createProject = (project, history) => async dispatch => {
    try {
        const res = await axios.post("/api/project", project)
        history.push("/dashboard")
    } catch (err) {
        dispatch({
            type: GET_ERRORS,
            payload: err.response.data,
        })
    }
}