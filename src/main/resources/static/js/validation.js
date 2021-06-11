let form = document.getElementById("form");
let username = document.getElementsByName("username")[0];
let password = document.getElementsByName("password")[0];
let firstName = document.getElementsByName("firstName")[0];
let lastName = document.getElementsByName("lastName")[0];
let errors = document.querySelectorAll(".error");

form.addEventListener("submit", function (event) {
    errors.forEach(err => err.innerText = '');
    if (!checkUsername()) {
        event.preventDefault();
        errors[0].innerText = 'Только латинские символы (от 3 до 16)';
    }
    if (!checkPassword()) {
        event.preventDefault();
        errors[1].innerText = 'Пароль может содержать от 3 до 16 латинских символов или цифр ' +
            '(минимум 1 символ и 1 цифра)'
    }
    if (!checkName(firstName)) {
        event.preventDefault();
        errors[2].innerText = 'Только латинские символы (от 1 до 16)';
    }
    if (!checkName(lastName)) {
        event.preventDefault();
        errors[3].innerText = 'Только латинские символы (от 1 до 16)'
    }
}, false);


function checkUsername() {
    let str = username.value;
    let regexp = /[a-zA-Z]{3,16}/g;
    let result = str.match(regexp);
    return result != null && result.length === 1 && result[0].length === str.length;
}

function checkName(name) {
    let str = name.value;
    let regexp = /[a-zA-Z]{1,16}/g;
    let result = str.match(regexp);
    return result != null && result.length === 1 && result[0].length === str.length;
}

function checkPassword() {
    let str = password.value;
    let regexp = /[a-zA-Z0-9]{3,16}/;
    let result = str.match(regexp);
    if (result != null && result.length === 1 && result[0].length === str.length) {
        if (str.match(/\d+/) != null && str.match(/[a-zA-Z]+/) != null) {
            return true;
        }
    }
    return false;
}