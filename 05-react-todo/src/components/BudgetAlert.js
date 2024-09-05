import { Component } from "react";

import "./BudgetAlert.css"

export default class BudgetAlert extends Component {
  colorCode = {
    "create": "#3493ff",
    "update": "#3493ff",
    "delete": "#ff3434"
  }
  
  render() {
    return (
      this.props.budgetAlert.isVisible && (
        <div id="budget-alert"
        style={{
          "background-color": this.colorCode[this.props.budgetAlert.state]
        }}>
          <p>{this.props.budgetAlert.message}</p>
        </div>
      )
    );
  }
}
