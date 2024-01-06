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
           
            <form onSubmit={this.handleSubmit} className="form">     
            <h2>Login</h2>  
            <div className="input">
            <label><span>
                Name: </span> 
                <input type="text"
                 /> 
            </label>
            </div>
            <br/>
            <div className="input">
            <label><span>
                Password: </span>
                <input type="password" 
                /> 
            </label>
            </div> 
            <button className="button" type="submit"> Submit </button>
            </form>
        );
    }
}