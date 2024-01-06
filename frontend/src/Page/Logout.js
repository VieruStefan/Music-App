import axios from "axios";
import { Component } from "react";

export default class Logout extends Component{
    constructor(props){
        super(props);
        this.state = {token: ""};
    }
    
    
    async componentDidMount(){
        console.log("mounted");
        this.setState({
            token: localStorage.getItem("token")
        });
        await this.setState({token : localStorage.getItem("token")});
        axios.put("http://localhost:7000/api/auth/logout", this.state.token)
        .then(alert("Logged out!"))
        .then(localStorage.clear())
        .then(window.location.href="/login");
        
    }

    render() {
        return (
            <div>
                <h2>ByeBye!</h2>
            </div>
        )
    }
        
}