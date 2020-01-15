import { createStore, applyMiddleware, compose } from 'redux';
import thunk from 'redux-thunk';
import rootReducer from "./reducers";

const intialState = {}
const middleware = [thunk];

let store;

store = createStore(rootReducer, intialState,
    compose(applyMiddleware(...middleware)));

/*This is to help setup Redux Store devtools in Chrome*/
/*if (window.navigator.userAgent.includes("Chrome")) {
    store = createStore(rootReducer, intialState, compose (applyMiddleware(...middleware),
            window._REDUX_DEVTOOLS_EXTENSION_ &&
            window._REDUX_DEVTOOLS_EXTENSION_()
        )
    );
} else { //Allows to load in other browsers
    store = createStore(rootReducer, intialState,
        compose(applyMiddleware(...middleware)));
}*/
export default store;
