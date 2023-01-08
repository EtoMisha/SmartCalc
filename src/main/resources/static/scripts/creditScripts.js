const url = "http://localhost:2121/"

document.querySelector('.credit_submit').onclick = function () {
    const amountInput = document.getElementById('amount').value.replaceAll(",", ".")
    const periodInput = document.getElementById('period').value.replaceAll(",", ".")
    const percentInput = document.getElementById('percent').value.replaceAll(",", ".")

    if (!isNumeric(amountInput) || !isNumeric(periodInput) || !isNumeric(percentInput)) {
        alert("Можно вводить только числа")
        return
    }

    const req = {
        amount: amountInput,
        period: periodInput,
        percent: percentInput,
        type: document.querySelector('input[name="type"]:checked').id
    };
    let response = {
        status: null,
        message: null,
        monthlyPayment: null,
        overPayment: null,
        totalPayment: null
    };

    const data = JSON.stringify(req);
    const xhr = new XMLHttpRequest();
    xhr.open("POST", url + "credit");
    xhr.setRequestHeader("Content-Type", "application/json");
    xhr.onload = function() {
        response = JSON.parse(xhr.responseText);
        const status = response.status
        if (status === "OK") {
            document.getElementsByClassName('result')[0].style.display = "block";
            document.getElementById('monthlyPayment').innerText = response.monthlyPayment
            document.getElementById('overPayment').innerText = response.overPayment
            document.getElementById('totalPayment').innerText = response.totalPayment
        } else {
            document.getElementsByClassName('result_error')[0].style.display = "block";
            document.getElementById('errorText').innerText = response.message
        }
    }
    xhr.send(data);
}

document.querySelector('.credit_clear').onclick = function () {
    document.getElementById('amount').value = ""
    document.getElementById('period').value = ""
    document.getElementById('percent').value = ""
    document.getElementsByClassName('result')[0].style.display = "none";
    document.getElementsByClassName('result_error')[0].style.display = "none";
}

document.querySelector('.exit').onclick = function () {
    fetch(url + "exit", null).then(() => null);
    setTimeout(() => { window.close(); }, 1000);
}

function isNumeric(n) {
    return !isNaN(parseFloat(n)) && isFinite(n);
}
