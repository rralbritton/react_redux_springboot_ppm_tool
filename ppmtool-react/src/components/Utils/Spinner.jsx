import React from "react";
import spinner from "../../images/loading_spinner.gif";



const Spinner = () => {

    const spinnerCss = {
        width: "20%",
        marginLeft: "40%"
    }

    return (
        <React.Fragment>
            <div><img src={spinner} style={spinnerCss} alt="loading spinner" /></div>
        </React.Fragment>
    )
}

export default Spinner;