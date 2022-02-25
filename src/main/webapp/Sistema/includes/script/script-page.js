$(document).ready(function(){
    var urlParams = new URLSearchParams(window.location.search);
    var elemento = document.getElementById('content');
    const msgElement = document.createElement('div');
    msgElement.setAttribute('role', 'alert');
    if(urlParams.has('msg')) {
        msgElement.setAttribute('class', 'alert alert-success');
        msgElement.innerText = urlParams.get('msg');
        elemento.prepend(msgElement);
    } else if (urlParams.has('erro')) {
        msgElement.setAttribute('class', 'alert alert-danger');
        msgElement.innerText = urlParams.get('erro');
        elemento.prepend(msgElement);
    }
  });