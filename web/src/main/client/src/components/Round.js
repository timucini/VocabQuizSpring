import React, { useState, useEffect } from "react";
import axios from "axios";


function Round(props) {

    return(
        <div>
           <p>IN Round</p>
           <p>Round for category: {props.round.category.name}</p>
        </div>
    );
}

export default Round;