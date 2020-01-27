import axios from "axios";
import { GET_ERRORS } from "./Types";

export const createNewUser = (newUser, history) => {
    return dispatch => {
        return axios.post("/api/users/register", newUser)
            .then(results => {
                dispatch({ type: "CREATE_NEW_USER", payload: results.data })
                dispatch({ type: GET_ERRORS, payload: {} });
                history.push("/login");
            }).catch(err => {
                dispatch({ type: "GET_ERRORS", payload: err.response.data })
            })
    }
}

export const loginUser = (userLogin, history) => {
    return dispatch => {
        return axios.post("/api/users/login", userLogin)
            .then(results => {
                dispatch({ type: "VERIFY_USER", payload: results.data.success });
                dispatch({ type: "STORE_TOKEN", payload: results.data.token });
                dispatch({ type: GET_ERRORS, payload: {} });
                history.push("/dashboard");
            }).catch(err => {
                dispatch({ type: "GET_ERRORS", payload: err.response.data })
            })
    }
}
