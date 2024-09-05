class Todo {
  index = 0;
  title = "";
  checked = false;

  constructor(index) {
    this.index = index;
  }
}

class LocalStorageManager {
  LOCAL_STORAGE_KEY = "todos";
  index = 0;

  constructor() {
    if (localStorage.getItem(this.LOCAL_STORAGE_KEY) == null) {
      localStorage.clear();
      localStorage.setItem(this.LOCAL_STORAGE_KEY, JSON.stringify([]));
    }
    this.index = this.getDB().reduce(
      (acc, cur) => (acc < cur.index ? cur.index : acc),
      0
    );
  }

  getTodo(todoIndex) {
    return this.getDB().find((todo) => todo.index === todoIndex);
  }

  updateTodo = (todoIndex, callback) => {
    const db = this.getDB();
    db.map((todo) => {
      if (todo.index === todoIndex) {
        callback(todo);
      }
    });
    localStorage.setItem(this.LOCAL_STORAGE_KEY, JSON.stringify(db));
  };

  createTodo = () => {
    const db = this.getDB();
    db.push(new Todo(++this.index));
    localStorage.setItem(this.LOCAL_STORAGE_KEY, JSON.stringify(db));
    return this.index;
  };

  deleteTodo = (todoIndex) => {
    const db = this.getDB();
    localStorage.setItem(
      this.LOCAL_STORAGE_KEY,
      JSON.stringify(db.filter((todo) => todo.index !== todoIndex))
    );
  };

  getDB() {
    return JSON.parse(localStorage.getItem(this.LOCAL_STORAGE_KEY));
  }
}

let localStorageManager = null;

window.onload = () => {
  localStorageManager = new LocalStorageManager();
  const addTodoBtn = document.getElementById("add-todo-btn");
  addTodoBtn.addEventListener("click", addTodoElement);
  restoreTodoList();
};

const addTodoElement = () => {
  const container = document.getElementById("container");
  const todoIndex = localStorageManager.createTodo();
  container.appendChild(createTodoElement(todoIndex));
};

const createTodoElement = (todoIndex) => {
  const todo = document.createElement("div");
  todo.classList.add("todo");
  todo.id = `todo-${todoIndex}`;
  todo.appendChild(createTodoCheckBoxElement(todoIndex));
  todo.appendChild(createTodoTitleElement(todoIndex));
  todo.appendChild(createTodoEditBtnElement(todoIndex));
  todo.appendChild(createTodoDeleteBtnElement(todoIndex));
  return todo;
};

const createTodoCheckBoxElement = (todoIndex) => {
  const checkBoxElement = document.createElement("input");
  checkBoxElement.type = "checkbox";
  checkBoxElement.addEventListener("change", (event) => {
    localStorageManager.updateTodo(todoIndex, (todo) => {
      todo.checked = event.currentTarget.checked;
    });
    const todoTitleElement = document.querySelector(
      `#todo-${todoIndex} input[type="text"]`);
    if (event.target.checked) {
      todoTitleElement.style.setProperty("text-decoration", "line-through")
    } else {
      todoTitleElement.style.setProperty("text-decoration", "none")
    }
  });
  return checkBoxElement;
};

const createTodoTitleElement = (todoIndex) => {
  const titleElement = document.createElement("input");
  titleElement.type = "text";
  titleElement.disabled = true;
  titleElement.addEventListener("blur", () => (titleElement.disable = true));
  titleElement.addEventListener("change", (event) => {
    localStorageManager.updateTodo(todoIndex, (todo) => {
      todo.title = event.target.value;
    });
  });
  return titleElement;
};

const createTodoEditBtnElement = (todoIndex) => {
  const editBtnElement = document.createElement("span");
  editBtnElement.classList.add("material-icons");
  editBtnElement.innerText = "edit";
  editBtnElement.addEventListener("click", () => {
    const titleElement = document.querySelector(
      `#todo-${todoIndex} input[type="text"]`
    );
    titleElement.disabled = false;
    titleElement.focus();
  });
  return editBtnElement;
};

const createTodoDeleteBtnElement = (todoIndex) => {
  const deleteBtnElement = document.createElement("span");
  deleteBtnElement.innerText = "delete";
  deleteBtnElement.classList.add("material-icons");
  deleteBtnElement.addEventListener("click", () => {
    localStorageManager.deleteTodo(todoIndex);
    document.querySelector(`#container #todo-${todoIndex}`).remove();
  });
  return deleteBtnElement;
};

const restoreTodoList = () => {
  const db = localStorageManager.getDB();
  db.forEach((todo) => {
    const todoElement = createTodoElement(todo.index);
    const checkboxElement = todoElement.children[0];
    const titleElement = todoElement.children[1];
    checkboxElement.checked = todo.checked;
    titleElement.value = todo.title;
    document.getElementById("container").appendChild(todoElement);
  });
};
