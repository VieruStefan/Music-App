import { Component } from "react";
import { setAuthToken } from "../services/setAuthToken";
import axios from "axios";
export default class Register extends Component{
    constructor(props) {
        super(props);
        this.handleSubmit = this.handleSubmit.bind(this);
    }

    handleSubmit = (e) => {
        e.preventDefault();
        axios.post("http://localhost:7000/api/auth/register", {
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
            <h2>Register</h2>  
            <div className="input">
            <label><span>
                Name: </span> 
                <input type="text"/> 
            </label>
            </div>
            <br/>
            <div className="input">
            <label><span>
                Password: </span>
                <input type="password"/> 
            </label>
            </div> 
            <button className="button" type="submit"> Submit </button>
            </form>
        );
    }
}