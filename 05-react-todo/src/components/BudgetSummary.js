import { Component } from "react";

import "./BudgetSummary.css"

export default class BudgetSummary extends Component {
  render() {
    return (
      <div id="budget-summary">
        <p>
          총 지출 : {" "}
          {this.props.budgets.reduce((amount, budget) => {
            return amount + budget.amount;
          }, 0)}원
        </p>
      </div>
    );
  }
}
