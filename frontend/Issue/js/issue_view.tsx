import React, { Component}  from 'react'
import ReactDOM from 'react-dom'
import '../css/issue.css'
import IssueOptions from './IssueOptions.tsx'


class IssueView extends React.Component{
	render(){
		return <div className='mainDiv'>
			<IssueOptions />
		</div>
	}
}

ReactDOM.render(
  <IssueView />,
  document.getElementById('react')
);

