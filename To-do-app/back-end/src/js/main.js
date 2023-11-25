const btnAddElm = document.querySelector('#btn-add');
const txtElm = document.querySelector('#txt');
const taskContainerElm = document.querySelector('#task-container');

taskContainerElm.addEventListener('click', (e)=>{
    if (e.target.classList.contains('delete')) {
        e.target.closest('li').remove();
    }
});

let taskId = 0;

btnAddElm.addEventListener('click', () => {
    const task = txtElm.value;
    if (task.trim().length === 0) {
        txtElm.focus();
        txtElm.select();
        return;
    }

    const newLiElm = document.createElement('li');
    taskContainerElm.append(newLiElm);

    newLiElm.innerHTML = `
    <input type="checkbox" name="" id="chk-task-${taskId}">
    <label for="chk-task-${taskId++}">${task}</label>
    <i class="delete bi bi-trash3-fill"></i>
    `;

    txtElm.value = "";
    txtElm.focus();
});