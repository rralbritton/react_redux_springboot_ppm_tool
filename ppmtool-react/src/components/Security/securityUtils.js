import axios from "axios";

const setJwtToken = (token, validUser) => {
    if(token & validUser){
        axios.defaults.headers.common["AUTHORIZATION"] = token
    } else {
        delete  axios.defaults.headers.common["AUTHORIZATION"]
    }
}

export default setJwtToken; 