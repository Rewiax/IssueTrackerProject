import React, { Component}  from 'react'
import ReactDOM from 'react-dom'
import '../css/issue.css'
import IssueCreate from './IssueCreate.tsx'


class IssueAdd extends React.Component{
	render(){
		return <div className='mainDiv'>
			<IssueCreate />
		</div>
	}
}

ReactDOM.render(
  <IssueAdd/>,
  document.getElementById('react')
);

