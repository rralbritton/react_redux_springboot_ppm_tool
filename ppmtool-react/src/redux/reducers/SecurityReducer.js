const initialState = {
    newUser: {},
    validUser: false,
    userToken: ""
}

export const createNewUserReducer = (state = initialState.newUser, action) => {
    switch (action.type) {
        case "CREATE_NEW_USER":
            return { ...state, payload: action.payload }
        default:
            return state
    }
}

export const verifyUserReducer = (state = initialState.validUser, action) => {
    switch (action.type) {
        case "VERIFY_USER":
            return { ...state, payload: action.payload }

        default:
            return state
    }
}

export const storeTokenReducer = (state = initialState.userToken, action) => {
    switch (action.type) {
        case "STORE_TOKEN":
            return { ...state, payload: action.payload }
        default:
            return state
    }
}