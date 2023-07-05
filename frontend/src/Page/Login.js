import { Component } from "react";
import { setAuthToken } from "../services/setAuthToken";
import axios from "axios";
export default class Login extends Component{
    constructor(props) {
        super(props);
        this.handleSubmit = this.handleSubmit.bind(this);

    }

    handleSubmit = (e) => {
        e.preventDefault();
        axios.put("http://localhost:7000/api/auth/authenticate", {
            uname: e.target[0].value,
            upass: e.target[1].value
        })
          .then(response => {
            console.log("got here");
            const token  =  response.data.token;
            localStorage.setItem("token", token);
            setAuthToken(token);
            window.location.href = '/'
          })
          .catch(err => console.log(err));
      };
        
    render() {
        return (
            <form onSubmit={this.handleSubmit}>        
            <label>
                Name: 
                <input type="text"
                 /> 
            </label>
            <br/>
            <label>
                Password: 
                <input type="password" 
                /> 
            </label>
            <input type="submit" value="Submit" />
            </form>
        );
    }
}