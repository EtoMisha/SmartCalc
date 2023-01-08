const url = "http://localhost:2121/"

document.querySelector('.deposit_submit').onclick = function () {
    const amountInput = document.getElementById('amount').value.replaceAll(",", ".")
    const periodInput = document.getElementById('period').value.replaceAll(",", ".")
    const percentInput = document.getElementById('percent').value.replaceAll(",", ".")
    const taxInput = document.getElementById('tax').value.replaceAll(",", ".")

    if (!isNumeric(amountInput) || !isNumeric(periodInput) || !isNumeric(percentInput) || !isNumeric(taxInput)) {
        alert("Можно вводить только числа")
        return
    }

    const req = {
        amount: amountInput,
        period: periodInput,
        percent: percentInput,
        tax: taxInput,
        frequency: document.querySelector('input[name="frequency"]:checked').id,
        capitalisation: document.getElementById('capitalisation').checked,
        deposits: [document.getElementById('deposit1').value.replaceAll(",", "."),
            document.getElementById('deposit2').value.replaceAll(",", "."),
            document.getElementById('deposit3').value.replaceAll(",", "."),
            document.getElementById('deposit4').value.replaceAll(",", "."),
            document.getElementById('deposit5').value.replaceAll(",", ".")],
        withdrawals: [document.getElementById('withdrawal1').value.replaceAll(",", "."),
            document.getElementById('withdrawal2').value.replaceAll(",", "."),
            document.getElementById('withdrawal3').value.replaceAll(",", "."),
            document.getElementById('withdrawal4').value.replaceAll(",", "."),
            document.getElementById('withdrawal5').value.replaceAll(",", ".")]
    };

    let response = {
        status: null,
        message: null,
        profit: null,
        taxes: null,
        total: null
    };

    const data = JSON.stringify(req);
    const xhr = new XMLHttpRequest();
    xhr.open("POST", url + "deposit");
    xhr.setRequestHeader("Content-Type", "application/json");
    xhr.onload = function() {
        response = JSON.parse(xhr.responseText);
        const status = response.status
        if (status === "OK") {
            document.getElementsByClassName('result')[0].style.display = "block";
            document.getElementById('profit').innerText = new Intl.NumberFormat('ru-RU').format(response.profit)
            document.getElementById('taxes').innerText = new Intl.NumberFormat('ru-RU').format(response.taxes)
            document.getElementById('total').innerText = new Intl.NumberFormat('ru-RU').format(response.total)
        } else {
            document.getElementsByClassName('result_error')[0].style.display = "block";
            document.getElementById('errorText').innerText = response.message
        }
    }
    xhr.send(data);
}

document.querySelector('.deposit_clear').onclick = function () {
    document.getElementById('amount').value = ""
    document.getElementById('period').value = ""
    document.getElementById('percent').value = ""
    document.getElementById('tax').value = ""
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
