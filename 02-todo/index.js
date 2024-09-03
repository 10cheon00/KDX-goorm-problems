const LOCAL_STORAGE_KEY = "todos";
let index = 0;

class Todo {
  index = 0;
  title = "";
  checked = false;
}

window.onload = () => {
  initializeLocalStorage();
  const addTodoBtn = document.getElementById("add-todo-btn");
  addTodoBtn.addEventListener("click", addTodo);
};

const initializeLocalStorage = () => {
  localStorage.clear();
  localStorage.setItem(LOCAL_STORAGE_KEY, JSON.stringify([]));
};

const getTodo = (todoIndex) => {
  const db = JSON.parse(localStorage.getItem(LOCAL_STORAGE_KEY));
  return db.find((todo) => todo.index === todoIndex);
};

const updateTodo = (todoIndex, callback) => {
  const todo = getTodo(todoIndex);
  callback(todo)
  localStorage.setItem(LOCAL_STORAGE_KEY, JSON.stringify(db));
}

const addTodo = () => {
  const container = document.getElementById("container");
  container.appendChild(createTodo());
};

const createTodo = () => {
  const todoIndex = insertTodo();
  return createTodoElement(todoIndex);
};

const insertTodo = () => {
  const db = JSON.parse(localStorage.getItem(LOCAL_STORAGE_KEY));
  db.push(new Todo(++index));
  localStorage.setItem(LOCAL_STORAGE_KEY, JSON.stringify(db));
  return index;
};

const createTodoElement = (todoIndex) => {
  const todo = document.createElement("div");
  todo.appendChild(createTodoCheckBoxElement(todoIndex));
  todo.appendChild(createTodoTitleElement());
  todo.appendChild(createTodoEditBtnElement(todoIndex));
  todo.appendChild(createTodoDeleteBtnElement(todoIndex));
  return todo;
};

const createTodoCheckBoxElement = (todoIndex) => {
  const checkBoxElement = document.createElement("input");
  checkBoxElement.type = "checkbox";
  checkBoxElement.addEventListener("change", (event) => {
    updateTodo(todoIndex, (todo) => {
      todo.state = event.target.value;
    });
  });
  return checkBoxElement;
};

const createTodoTitleElement = () => {
  const titleElement = document.createElement("input");
  titleElement.type = "text";
  titleElement.addEventListener("blur", () => (titleElement.disable = true));
  return titleElement;
};

const createTodoEditBtnElement = (todoIndex) => {
  const editBtnElement = document.createElement("span");
  editBtnElement.classList.add("material-icons");
  editBtnElement.innerText = "edit";
  editBtnElement.addEventListener("click", () => {
    const titleElement = document.querySelector(`#todo-${todoIndex} input[type="text"]`);
    titleElement.disable = false;
  });
  return editBtnElement;
};

const createTodoDeleteBtnElement = (todoIndex) => {};
