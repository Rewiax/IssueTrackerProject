import $ from 'jquery'
import React, {Component}  from 'react'
import axios from 'axios'
import '../css/issue.css'
import 'bootstrap/dist/css/bootstrap.min.css';

import {Form, Button} from 'react-bootstrap';

const ipAddress='localhost:8080';

class IssueManager extends React.Component{	
	constructor(props){
		super(props);

		this.state={
			issueList:[],
			choosedIssue:null,
		}
	}

	getIssueList=()=>{
		return axios.get('http://'+ipAddress+'/api/issue/get_issue_list');
	}

	componentDidMount(){
		Promise.all([this.getIssueList()])
		   .then(function (results) {
				let newIssueList = Object.assign([], results[0].data);
				newIssueList.sort((a, b) => a.id - b.id).forEach((issue, i) => {
					let currentdate = new Date(issue.startDate); 
					let datetime = currentdate.getDate() + "/"
			                + (currentdate.getMonth()+1)  + "/" 
			                + currentdate.getFullYear();

			        issue.dateToShow = datetime;
				});
		   		let result = [];
				result.push(newIssueList);
				return result;
		}).then(response => {
     		this.setState({
				issueList:response[0],
			});
     	});
	}

	render(){
		return (
			<div>
				<form>
					<div className="form-group">
						<h1 className="labelElement">Issue Tracker</h1>
					</div>
			  		<div className="mainIssueDiv"> 
			  		{
					  	this.state.issueList.map((issue, i) => {
					  		return (
					  			<div key={i} className="issueListDiv">
					  				<div className="issueElement"><a href={"/view?data="+issue.id} className="link-primary">{issue.name}</a></div>
					  				<div className="issueElement"><label>{issue.status.name}</label></div>
					  				<div className="issueElement"><label>{issue.dateToShow}</label></div>							  			
					  			</div>
					  		)				  		
					  	})			
				  	}  	
			  		</div>	
			  		<div>
				  		<a href="create">
							<button className="button" type="button" style={{fontSize: '16px', width: '15%'}}>Create new issue</button>
						</a>  										
					</div>		  
				</form>
			</div>
		)
	}
}



export default IssueManager