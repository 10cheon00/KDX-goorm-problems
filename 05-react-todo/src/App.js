import { useState } from "react";

import BudgetForm from "./components/BudgetForm.js";
import BudgetList from "./components/BudgetList.js";
import BudgetSummary from "./components/BudgetSummary.js";
import BudgetAlert from "./components/BudgetAlert.js";

import "./App.css";

function App() {
  const [name, setName] = useState("");
  const [amount, setAmount] = useState(0);
  const budget = {
    name: name,
    setName: setName,
    amount: amount,
    setAmount: setAmount,
  };
  const [budgets, setBudgets] = useState([]);
  const [selectedBudgetIndex, setSelectedBudgetIndex] = useState(-1);
  const [submitButtonLabel, setSubmitButtonLabel] = useState("제출");
  const [budgetAlert, setBudgetAlert] = useState({state:false, message: ""});

  const addBudget = () => {
    setBudgets((budgets) => {
      const newBudgets = [...budgets];
      newBudgets.push({ name: name, amount: Number.parseInt(amount) });
      return newBudgets;
    });
    clearBudget();
    alertMessage("아이템이 생성되었습니다.", "create")
  };

  const updateBudget = () => {
    setBudgets((budgets) => {
      const newBudgets = [...budgets];
      return newBudgets.map((budget, index) =>
        index !== selectedBudgetIndex
          ? budget
          : { name: name, amount: Number.parseInt(amount) }
      );;
    });
    clearBudget();
    setSelectedBudgetIndex(-1);
    setSubmitButtonLabel("제출");
    alertMessage("아이템이 수정되었습니다.", "update")
  };

  const removeAllBudget = () => {
    setBudgets([])
    alertMessage("모든 아이템이 삭제되었습니다.", "delete")
  }

  const removeBudget = (budgetIndex) => {
    const newBudgets = [...budgets];
    setBudgets(newBudgets.filter((budget, index) => budgetIndex !== index));
    alertMessage("아이템이 삭제되었습니다.", "delete")
  };

  const clearBudget = () => {
    budget.setName("");
    budget.setAmount(0);
  };

  const selectBudget = (index) => {
    setSelectedBudgetIndex(index);
    const selectedBudget = budgets.find(
      (budget, budgetIndex) => budgetIndex === index
    );
    budget.setName(selectedBudget.name);
    budget.setAmount(selectedBudget.amount);
    setSubmitButtonLabel("수정");
  };

  const submitButtonCallback = () => {
    if (selectedBudgetIndex === -1) {
      addBudget();
    } else {
      updateBudget();
    }
  };

  const alertMessage = (message, state) => {
    setBudgetAlert({message: message, state: state, isVisible: true})
    setTimeout(() => setBudgetAlert({message: "", isVisible: false}), 1000)
  }

  return (
    <div className="app">
      <BudgetAlert budgetAlert={budgetAlert} />
      <h1>예산 계산기</h1>
      <BudgetForm
        budget={budget}
        submitButtonCallback={submitButtonCallback}
        submitButtonLabel={submitButtonLabel}
      />
      <BudgetList
        budgets={budgets}
        onSelectBudget={selectBudget}
        onRemoveBudget={removeBudget}
        onRemoveAllBudget={removeAllBudget}
      />
      <BudgetSummary budgets={budgets} />
    </div>
  );
}

export default App;
