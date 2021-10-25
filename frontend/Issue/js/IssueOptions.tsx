import $ from 'jquery';
import React, { Component}  from 'react';
import ReactDOM from 'react-dom';
import axios from 'axios';

import 'bootstrap/dist/css/bootstrap.min.css';

import {Button} from 'react-bootstrap';
import Select from 'react-select';

import '../css/issue.css';

const ipAddress='localhost:8080';


class IssueOptions extends React.Component {
	constructor(props){
		super(props);
		this.state={
			currentIssue:null,
			statusesList:[],
			newComment:{
				id: 0,
				author: {
					id: 0,
					name: "",
				},
				statusId: 0,
				status: {
					id: 0,
					name: "Resolved",
				},
				commentText:"",
			}
		}
		
	}

	componentDidMount(){
		var data = getUrlVars()["data"];
		// console.log(data);

		if (data) {
			Promise.all([this.getIssueById(data), this.getIssueStatuses()])
			   .then(function (results) {
			   		let issue = results[0].data;
			   		let statusesList = results[1].data;
					statusesList.sort((a, b) => a.name.localeCompare(b.name));

			   		let result = [];
					result.push(issue);
					result.push(statusesList);
					return result;
				}).then(response => {
		     		this.setState({
						currentIssue:response[0],
						statusesList:response[1],
					});
     		});
		}
	}

	getIssueById=(id)=>{
		return axios.get('http://'+ipAddress+'/api/issue/get_issue'+id);
	}

	getIssueStatuses=()=>{
		return axios.get('http://'+ipAddress+'/api/issue/get_statuses');
	}

	addNewComment=(comment)=>{
		let currentIssue = this.state.currentIssue;
		comment.issueId = currentIssue.id;

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

        comment.updateTime = datetime;

		if (currentIssue.comments) {
			currentIssue.comments.push(comment);
		} else {
			currentIssue.comments = [];
			currentIssue.comments.push(comment);
		}

		currentIssue.timeToShow = null;

		axios.post('http://'+ipAddress+'/issue/comment/create', comment);
	}

	parseTimeToShow=(time)=>{
		let currentdate = new Date(time); 
		let currentMinutes = currentdate.getMinutes();
		if (currentMinutes < 10) {
			currentMinutes = "0" + currentMinutes;
		}
		let datetime = currentdate.getDate() + "/"
            + (currentdate.getMonth()+1)  + "/" 
            + currentdate.getFullYear() + " "  
            + currentdate.getHours() + ":"  
            + currentMinutes;

        return datetime;
	}

	render(){
		let currentIssue = this.state.currentIssue;
		let statusesList = Object.assign([], this.state.statusesList);
		let newComment = this.state.newComment;
		if (currentIssue) {
	        currentIssue.timeToShow = this.parseTimeToShow(currentIssue.startDate);

			let statusData=[];
			statusesList.forEach(status => {
				statusData.push({value:status.id, label:status.name});
			});
			return (
				<div>
					<div className="mainDiv">
						<h1 className="labelElement"><b>{currentIssue.name}</b></h1>
						<div className="labelGroupDiv">
							<div className="labelElementsDiv">
								<div className="labelText">
									<label className="labelElement"><b>Status</b></label>
								</div>
								<label className="labelElement">{currentIssue.status.name}</label>
							</div>
							<div className="labelElementsDiv">
								<div className="labelText">
									<label className="labelElement"><b>Start date</b></label>
								</div>
								<label className="labelElement">{currentIssue.timeToShow}</label>
							</div>
							<div className="labelElementsDiv">
								<div className="labelText">
									<label className="labelElement"><b>Author</b></label>
								</div>
								<label className="labelElement">{currentIssue.author.name}</label>
							</div>
						</div>
						<div className="descriptionDiv">
							<label className="labelElement"><b>Description</b></label>
							<label className="labelMarginLeft">{currentIssue.description}</label>
						</div>
						<div className="descriptionDiv">
							<label className="labelElement"><b>Comments:</b></label>
							{
								currentIssue.comments.sort((a, b) => a.id - b.id).map((comment, i) => {
									comment.timeToShow = this.parseTimeToShow(comment.updateTime);
									return (
										<div key={i} style={{marginBottom: '1%'}}>
											<label className="labelElement">Update by {comment.author.name} {comment.timeToShow}:</label>
											<label className="labelMarginLeft"><i>Status changed to {comment.status.name}</i></label>
											<label className="labelElement">{comment.commentText}</label>
										</div>
									)
								})
							}						
						</div>						
					</div>
					<div className="divBorder">
						<form action="/main">
							<div className="marginLeftDiv">
								<label className="labelElement">Add comment:</label>
								<div className="labelElementsDiv">
									<div className="labelText">
										<label className="labelElement">Status:</label>
									</div>
									<div className="select-editable">
										<Select menuPosition="fixed" value={{value:newComment.status.id, label:newComment.status.name}} options={statusData} onChange={(event)=> {
											newComment.status.id = event.value;
											newComment.status.name = event.label;
											newComment.statusId = event.value;
											this.setState({
												newComment:newComment,
											});
										}}/>
									</div>
								</div>		
								<div className="marginTopDiv">
									<div className="labelText">
										<label className="labelElement">Author:</label>
									</div>
									<div>
										<input className="inputElement" type='text' value={newComment.author.name} required onChange={(event)=> { 
											let reg = /^[a-zA-Z\s]*$/;
											if (reg.test(event.target.value)) {
												newComment.author.name = event.target.value;
												this.setState({
													newComment:newComment,
												});
											}
										}}/>
									</div>
								</div>		
								<div className="commentTextDiv">
									<div className="labelText">
										<label className="labelElement">Text</label>
									</div>
									<div>
										<textarea className="textAreaElement" type='text' value={newComment.commentText} required onChange={(event)=> { 
											newComment.commentText = event.target.value;
											this.setState({
												newComment:newComment,
											});
										}}/>
									</div>
									<div>	
										<button className='buttonSaveComment' onClick={()=>this.addNewComment(newComment)}>Add</button>				
										<a href="/main">
											<button className='button' type="button"style={{marginLeft: '1%'}}>Cancel</button>
										</a>
									</div>
								</div>					
							</div>
						</form>
					</div>
				</div>				
				)
		} else {
			return ( <div></div>)
		}
	}
}

function getUrlVars() { 
	var vars = {}; 
	var parts = window.location.href.replace(/[?&]+([^=&]+)=([^&]*)/gi, function(m,key,value) { 
	   vars[key] = value; 
	});
	return vars; 
}

export default IssueOptions