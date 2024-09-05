import { Component } from "react";
import { MdEdit, MdDelete } from "react-icons/md";

import "./BudgetList.css";

export default class BudgetList extends Component {
  render() {
    return (
      <div id="budget-list">
        <ul>
          {this.props.budgets.map((budget, index) => {
            return (
              <li key={index}>
                <span id="budget-name">{budget.name}</span>
                <span id="budget-amount">{budget.amount}</span>
                <MdEdit onClick={() => this.props.onSelectBudget(index)}/>
                <MdDelete onClick={() => this.props.onRemoveBudget(index)}/>
              </li>
            );
          })}
        </ul>
        <button onClick={this.props.onRemoveAllBudget}>
          모두 지우기{" "}
          <MdDelete />
        </button>
      </div>
    );
  }
}
