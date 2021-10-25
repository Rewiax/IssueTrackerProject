import React, { Component}  from 'react'
import ReactDOM from 'react-dom'
import '../css/issue.css'
import IssueManager from './IssueManager.tsx'


class IssueMain extends React.Component{
	render(){
		return <div className='mainDiv'>
			<IssueManager />
		</div>
	}
}

ReactDOM.render(
  <IssueMain />,
  document.getElementById('react')
);

