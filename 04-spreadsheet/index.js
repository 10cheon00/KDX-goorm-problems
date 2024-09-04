const SIZE = 10;

window.onload = () => {
  createSpreadsheet();
  document.querySelector("button").addEventListener("click", exportSpreadsheet);
};

const createSpreadsheet = () => {
  const sheetElement = document.getElementById("sheet");

  sheetElement.appendChild(createColumnIndexCellElements());

  Array(SIZE)
    .keys()
    .forEach((i) => {
      sheetElement.appendChild(createRowIndexCellElementAndCellElements(i));
    });
};

const createColumnIndexCellElements = () => {
  const columnIndexCellElements = createRowElement();
  const disabledCellElement = createCellElement();
  disabledCellElement.classList.add("cell-header");
  disabledCellElement.disabled = true;
  columnIndexCellElements.append(disabledCellElement);
  Array(SIZE).keys().forEach((i) => {
    columnIndexCellElements.append(createColumnIndexCellElement(i));
  });
  return columnIndexCellElements;
};

const createColumnIndexCellElement = (index) => {
  const columnIndexCellElement = createCellElement();
  columnIndexCellElement.classList.add("cell-header", "cell-header-column");
  columnIndexCellElement.disabled = true;
  columnIndexCellElement.value = indexToAlphabet(index);
  return columnIndexCellElement;
};

const createRowIndexCellElementAndCellElements = (i) => {
  const rowElement = createRowElement();
  rowElement.appendChild(createRowIndexCellElement(i));
  Array(SIZE)
    .keys()
    .forEach((j) => {
      const cellElement = createCellElement();
      cellElement.classList.add("cell-data");
      cellElement.addEventListener("focus", () => {
        highlightCellHeader(i, j);
        indicateCell(i, j);
      });
      rowElement.appendChild(cellElement);
    });
  return rowElement;
};

const createRowElement = () => {
  const rowElement = document.createElement("div");
  rowElement.classList.add("cell-row");
  return rowElement;
};

const createRowIndexCellElement = (index) => {
  const indexCellElement = createCellElement();
  indexCellElement.classList.add("cell-header", "cell-header-row");
  indexCellElement.disabled = true;
  indexCellElement.value = index;
  return indexCellElement;
};

const highlightCellHeader = (i, j) => {
  const columnHeaderElements = document.querySelectorAll(".cell-header-column");
  columnHeaderElements.forEach((element, index) => {
    element.classList.remove("active");
    if (index === j) {
      element.classList.add("active");
    }
  });
  const rowHeaderElements = document.querySelectorAll(".cell-header-row");
  rowHeaderElements.forEach((element, index) => {
    element.classList.remove("active");
    if (index === i) {
      element.classList.add("active");
    }
  });
};

const indicateCell = (i, j) => {
  const indicatorElement = document.getElementById("cell-indicator");
  indicatorElement.innerText = `Cell : ${indexToAlphabet(i)}${j}`;
};

const createCellElement = () => {
  const cellElement = document.createElement("input");
  cellElement.classList.add("cell");
  return cellElement;
};

const exportSpreadsheet = () => {
  const cellElements = Array.from(document.querySelectorAll(".cell-data"));
  const cells = [];
  while (cellElements.length) {
    cells.push(cellElements.splice(0, SIZE).map((element) => element.value));
  }

  const csvData = cells.map((row) => row.join(",")).join("\n");
  const blob = new Blob([csvData], { type: "text/csv;charset=utf-8;" });
  const url = URL.createObjectURL(blob);
  const link = document.createElement("a");
  link.setAttribute("href", url);
  link.setAttribute("download", "data.csv");
  document.body.appendChild(link);
  link.click();
  document.body.removeChild(link);
};

const indexToAlphabet = (index) => {
  return "ABCDEFGHIJ".split("")[index];
}