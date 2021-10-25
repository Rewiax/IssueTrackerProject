import React, { Component}  from 'react'
import ReactDOM from 'react-dom'
import '../css/issue.css'
import LoginManager from './LoginManager.tsx'


class IssueLogin extends React.Component{
	render(){
		return <div className='mainDiv'>
			<LoginManager />
		</div>
	}
}

ReactDOM.render(
  <IssueLogin/>,
  document.getElementById('react')
);

