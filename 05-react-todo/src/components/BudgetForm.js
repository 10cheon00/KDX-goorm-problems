import { Component } from "react";
import { MdSend } from "react-icons/md"

import "./BudgetForm.css";

export default class BudgetForm extends Component {
  render() {
    return (
      <div id="budget-form">
        <div id="budget-form-input">
          <div className="budget-form-item">
            <label>지출 항목</label>
            <input
              type="text"
              value={this.props.budget.name || ""}
              onChange={(e) => this.props.budget.setName(e.target.value)}
            />
          </div>
          <div className="budget-form-item">
            <label>비용</label>
            <input
              type="number"
              value={this.props.budget.amount || 0}
              onChange={(e) => this.props.budget.setAmount(e.target.value)}
            />
          </div>
        </div>
        <button onClick={this.props.submitButtonCallback}>
          <span>{this.props.submitButtonLabel}</span>{" "}
          <MdSend />
        </button>
      </div>
    );
  }
}
