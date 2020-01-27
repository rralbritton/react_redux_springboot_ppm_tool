import { createStore, applyMiddleware, compose } from 'redux';
import thunk from 'redux-thunk';
import rootReducer from "./reducers";
import reduxImmutableStateInvariant from "redux-immutable-state-invariant"

let store;

export default function configureStore(initialState) {
    const composeEnhancers =
      //add support for redux dev tools
      window.__REDUX_DEVTOOLS_EXTENSION_COMPOSE__ || compose;
  
    store = createStore(
      rootReducer,
      initialState,
      composeEnhancers(applyMiddleware(thunk, reduxImmutableStateInvariant()))
    );
  
    return store;
  }


