import { combineReducers } from "redux";
import ErrorReducer from "./ErrorReducer";
import { createNewUserReducer, verifyUserReducer, storeTokenReducer, setCurrentUserReducer } from "./SecurityReducer";

export default combineReducers({
    errors: ErrorReducer,
    newUser: createNewUserReducer,
    validUser: verifyUserReducer,
    userToken: storeTokenReducer,
    user: setCurrentUserReducer
});