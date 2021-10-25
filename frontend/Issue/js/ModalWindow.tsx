import $ from 'jquery'
import React, { Component}  from 'react'
import ReactDOM from 'react-dom'
import axios from 'axios'
import '../css/issue.css'



import {Modal, Dropdown, Button, ButtonGroup } from 'react-bootstrap';
import IssueOptions from './IssueOptions.tsx'

class ModalWindow extends React.Component{
	constructor(props){
		super(props);
	}

	render(){
		return(
			<div>
				{
					false && this.props.choosedIssue != null && <IssueOptions choosedIssue={this.props.choosedIssue}/>
				}
				</div>
			)
	}
}

export default ModalWindow