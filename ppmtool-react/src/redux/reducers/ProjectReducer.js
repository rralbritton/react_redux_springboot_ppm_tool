const initialState = {
    projects: [],
    project: {}
}

export const getAllUserProjectsReducer = (state = initialState.projects, action) => {
    switch (action.type) {
        case "GET_ALL_USER_PRJS":
            return { ...state, payload: action.payload }
        default:
            return state
    }
}

export const singleProjectsReducer = (state = initialState.project, action) => {
    switch (action.type) {
        case "GET_PRJ_BY_ID":
            return { ...state, payload: action.payload }
        case "CREATE_PRJ_SUCCESS":
            return { ...state, payload: action.payload }
        case "DELETE_PRJ_SUCCESS":
            return { ...state, payload: action.payload }
        default:
            return state
    }
}