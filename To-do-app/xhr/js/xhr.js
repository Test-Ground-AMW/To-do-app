const txtElm = document.querySelector('#txt');
const btnElm = document.querySelector('#btn');

btnElm.addEventListener('click', ()=>{
    const xhr = new XMLHttpRequest();
    xhr.addEventListener('readystatechange', ()=>{
        console.log("Received the response");
    });
    xhr.open('POST', 'http://localhost:8080/todo/tasks');
    xhr.setRequestHeader('Content-Type','application/json');
    const task = {description: txtElm.value};
    xhr.send(JSON.stringify(task));
});