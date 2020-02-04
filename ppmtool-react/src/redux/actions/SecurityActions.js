import axios from "axios";
import jwtDecode from "jwt-decode";
import { GET_ERRORS } from "./Types";

import setJwtToken from "./../../components/Security/securityUtils";

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
                //set user in local storage
                localStorage.setItem("jwtToken", results.data.token);
                localStorage.setItem("validUser", results.data.success);
                //Decode token to set user
                const decode = jwtDecode(results.data.token);
                dispatch({ type: "SET_USER", payload: decode })
                //set token in header
                setJwtToken(results.data.token)
                //redirect
                history.push("/dashboard");
            }).catch(err => {
                dispatch({ type: "GET_ERRORS", payload: err.response.data })
            })
    }
}

export const logoutUser = () => dispatch => {
    //clear out local storage
    debugger;
    setJwtToken(false);
    localStorage.removeItem("jwtToken");
    localStorage.removeItem("validUser");
    dispatch({ type: "VERIFY_USER", payload: false });
    dispatch({ type: "STORE_TOKEN", payload: "" });
    window.location.href = "/";
}