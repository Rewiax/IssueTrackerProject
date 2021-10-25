import $ from 'jquery'
import React, {Component}  from 'react'
import axios from 'axios'
import '../css/issue.css'
import 'bootstrap/dist/css/bootstrap.min.css';

import {Form, Button} from 'react-bootstrap';

const ipAddress='localhost:8080';

class LoginManager extends React.Component{	
	constructor(props){
		super(props);

		this.state={
			login: {
				username: "",
				password: "",
			},
			checkedValue: "login/password is incorrect",
			visible: 'hidden',
		}
	}

	getLogin=()=>{
		return axios.post('http://'+ipAddress+'/api/issue/login', this.state.login);
	}

	errorHandle=(error)=>{
		// console.log(error.response.status);
		if (error.response.status == 401) {
			this.setState({
				visible: 'visible',
			});
		}
	}

	//cancel refresh page
	submitForm=(event)=>{
		event.preventDefault();
	}

	checkLoginAuthorization=()=>{
		let login = this.state.login;
		// console.log(login);
		if (login.username != "" && login.password != "") {
			this.getLogin().then(response =>{
				location.href = "/main";							
			}).catch((error) => this.errorHandle(error));		
		}
	}

	
	render(){
		let login = this.state.login;
		return (
			<div style={{display: 'flex', flexDirection: 'column', justifyContent: 'center', height: '55rem'}}>
				<div style={{display: 'flex', flexDirection: 'row', justifyContent: 'center'}}>
					<form style={{display: 'flex', flexDirection: 'column', alignItems: 'end'}} onSubmit={(event)=> this.submitForm(event)}>
						<label>username:
							<input type="text" required onChange={(event)=> {							
								login.username = event.target.value;
								this.setState({
									login:login,
								});								
							}}/>
						</label>
						<label>password: 
							<input type="password" required onChange={(event)=> {					
								login.password = event.target.value;
								this.setState({
									login:login,
								});								
							}}/>
						</label>
						<div style={{width: '100%', justifyContent: 'center', display: 'flex'}}>
		                    <input type="text" style={{border: 'unset', textAlign: 'center', color: 'red', width: '100%', visibility: this.state.visible}} value={this.state.checkedValue} readOnly/>
		                </div>
						<button className="button" type="submit" style={{width: '100%'}} onClick={()=>this.checkLoginAuthorization()}>Login</button>						
					</form>
				</div>
			</div>
		)
	}
}



export default LoginManager