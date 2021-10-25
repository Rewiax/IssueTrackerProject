import $ from 'jquery';
import React, { Component}  from 'react';
import ReactDOM from 'react-dom';
import axios from 'axios';

import 'bootstrap/dist/css/bootstrap.min.css';

import {Button, Form} from 'react-bootstrap';
import Select from 'react-select';

import '../css/issue.css';

const ipAddress='localhost:8080';


class IssueCreate extends React.Component {
	constructor(props){
		super(props);

		this.state={
			currentIssue: {
				id: 0,
				startDate: null,
				name: "",
				author: {
					name: "",
				},
				status: {
					id: 0,
					name: "Created",
				},
				description: "",				
			},
		}		
	}

	getIssueById=(id)=>{
		return axios.get('http://'+ipAddress+'/api/issue/get_issue'+id);
	}

	getIssueStatuses=()=>{
		return axios.get('http://'+ipAddress+'/api/issue/get_statuses');
	}

	createNewIssue=(issue)=>{
		let currentdate = new Date(); 
		let currentMinutes = currentdate.getMinutes();
		if (currentMinutes < 10) {
			currentMinutes = "0" + currentMinutes;
		}
		let datetime = currentdate.getFullYear() + "-"
                + (currentdate.getMonth()+1)  + "-" 
                + currentdate.getDate() + " "  
                + currentdate.getHours() + ":"  
                + currentMinutes + ":"
                + currentdate.getSeconds();

        issue.startDate = datetime;
        if (issue.name != "" && issue.author.name != "" && issue.description != "") {
			axios.post('http://'+ipAddress+'/issue/create', issue);		
		}		
	}

	render(){
		let currentIssue = this.state.currentIssue;
		return (
			<div>			
				<form action="/main">
					<div className="mainDiv">
						<h1 className="labelElement"><b>Create issue</b></h1>
						<div className="labelGroupDiv">
							<div className="labelElementsDiv">							
								<div className="labelText">
									<label className="labelElement">Name</label>
								</div>
								<input className="inputElement" style={{width: '70%'}} type='text' value={currentIssue.name} required onChange={(event)=> { 
									let reg = /^[a-zA-Z0-9\s]*$/;
									if (reg.test(event.target.value)) {
										currentIssue.name = event.target.value;
										this.setState({
											currentIssue:currentIssue,
										});
									}									
								}}/>
							</div>
							<div className="labelElementsDiv">
								<div className="labelText">
									<label className="labelElement">Author</label>
								</div>
								<input className="inputElement" style={{width: '40%'}} type='text' value={currentIssue.author.name} required onChange={(event)=> { 
									let reg = /^[a-zA-Z\s]*$/;
									if (reg.test(event.target.value)) {
										currentIssue.author.name = event.target.value;
										this.setState({
											currentIssue:currentIssue,
										});
									}									
								}}/>
							</div>						
						</div>
						<div className="descriptionDiv">
							<label className="labelElement">Description</label>
							<textarea className="textAreaElement" type='text' required onChange={(event)=> { 
								currentIssue.description = event.target.value;
								this.setState({
									currentIssue:currentIssue,
								});
							}}/>
						</div>					
					</div>
					<div>									
						<button className='button' style={{marginLeft: '2%'}} onClick={()=>this.createNewIssue(currentIssue)}>Save</button>
						<a href="/main">		
							<button className='button' type="button" style={{marginLeft: '1%'}}>Cancel</button>
						</a> 
					</div>
				</form>
			</div>			
		)
	}
}

export default IssueCreate