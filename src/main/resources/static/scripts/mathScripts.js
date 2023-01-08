const url = "http://localhost:2121/"

const calculator = document.querySelector('.calculator')
const keys = calculator.querySelector('.calculator__keys')
let displayInput = document.getElementById('displayInput')
let clear = true
let isGraph = false
let isHelpVisible = false
let historyNum = 0

keys.addEventListener('click', e => {
    if (e.target.matches('button')) {
        const key = e.target
        const action = key.dataset.action
        const keyContent = key.textContent
        const displayedNum = displayInput.value

        let last_symbol = displayInput[displayInput.length - 2]

        switch (action) {
            case 'single_operator':
                if (last_symbol !== keyContent) {
                    displayInput.value = displayedNum + ' ' + keyContent + ' '
                }
                break;
            case 'bracket_operator':
                if (displayedNum === '0' || clear) {
                    displayInput.value = keyContent + '('
                } else {
                    displayInput.value = displayedNum + keyContent + '('
                }
                break;
            case 'decimal':
                if (last_symbol !== keyContent) {
                    displayInput.value = displayedNum + '.'
                }
                break;
            case 'increment':
                displayInput.value = clear ? '+' : displayedNum + '+'
                break;
            case 'decrement':
                displayInput.value = clear ? '-' : displayedNum + '-'
                break;
            case 'var':
                if (displayedNum === '0' || clear) {
                    displayInput.value = "x"
                } else {
                    displayInput.value = displayedNum + " x "
                }
                break;
            case 'clear' :
                displayInput.value = "0";
                document.getElementsByClassName('calculator__var')[0].style.display = "none";
                document.getElementsByClassName('calculator__graph')[0].style.display = "none";
                document.getElementsByClassName('graph__container')[0].style.display = "none";
                break;
            case 'calculate':
                if (displayInput.value.includes('x')) {
                    document.getElementsByClassName('calculator__var')[0].style.display = "inline-block";
                    let xValue = document.getElementById('var_value')

                    let response = {
                        status: null,
                        message: null,
                        output: null,
                        graph: null
                    };

                    xValue.oninput = function () {
                        const req = {
                            input: displayInput.value.replaceAll("x",
                                xValue.value.empty ? 0 : xValue.value)
                        };
                        const data = JSON.stringify(req);
                        const xhr = new XMLHttpRequest();
                        xhr.open("POST", url + "constant");
                        xhr.setRequestHeader("Content-Type", "application/json");
                        xhr.onload = function() {
                            response = JSON.parse(xhr.responseText);
                            const status = response.status
                            if (status === "OK") {
                                document.getElementById('varResult').innerHTML = "Result: " + response.output;
                            }
                        }
                        xhr.send(data);
                    }

                } else {
                    const req = {
                        input: displayInput.value
                    };
                    let response = {
                        status: null,
                        message: null,
                        output: null,
                        graph: null
                    };
                    const data = JSON.stringify(req);
                    const xhr = new XMLHttpRequest();
                    xhr.open("POST", url + "constant");
                    xhr.setRequestHeader("Content-Type", "application/json");
                    xhr.onload = function() {
                        response = JSON.parse(xhr.responseText);
                        const status = response.status
                        if (status === "OK") {
                            displayInput.value = response.output;
                        } else {
                            displayInput.value = status + ": " + response.message
                        }
                    }
                    xhr.send(data);
                }

                break;
            case 'graph':
                document.getElementsByClassName('calculator__graph')[0].style.display = "inline-block";
                if (isGraph) {
                    makeGraph();
                }
                isGraph = true;
                break;
            default:
                if (displayedNum === '0' || clear) {
                    displayInput.value = keyContent
                } else {
                    displayInput.value = displayedNum + keyContent
                }
                break;
        }

        clear = action === 'calculate' || action === 'clear';

    }
})

document.querySelector('.help').onclick = function() {
    if (isHelpVisible) {
        document.getElementsByClassName('help_layer')[0].style.visibility = 'hidden'
        isHelpVisible = false
    } else {
        document.getElementsByClassName('calculator__graph')[0].style.display = 'none';
        document.getElementsByClassName('calculator__var')[0].style.display = 'none';
        document.getElementsByClassName('graph__container')[0].style.display = 'none';
        document.getElementsByClassName('help_layer')[0].style.visibility = 'visible'
        isHelpVisible = true
    }
}

document.querySelector('.history_up').onclick = async function () {
    let response = await fetch(url + 'getHistory/' + historyNum);
    let text = await response.text();

    displayInput.value = text
    if (text.length > 0) {
        historyNum++;
    }
}

document.querySelector('.history_down').onclick = async function () {
    if (historyNum > 0) {
        historyNum--
    }
    let response = await fetch(url + 'getHistory/' + historyNum);
    displayInput.value = await response.text()
}

document.querySelector('.history_clear').onclick = async function () {
    fetch(url + "clearHistory", null).then(() => null);
}

document.querySelector('.exit').onclick = function () {
    fetch(url + "exit", null).then(() => null);
    setTimeout(() => { window.close(); }, 1000);
}

function makeGraph() {
    document.getElementsByClassName('graph__container')[0].style.display = "block";
    let fromInput = document.getElementById('from').value
    let toInput = document.getElementById('to').value

    if (fromInput < toInput) {
        const req = {
            input: displayInput.value,
            from: fromInput,
            to: toInput
        };
        let response = {
            status: null,
            message: null,
            output: null,
            xvalues: null,
            yvalues: null
        };
        const data = JSON.stringify(req);
        const xhr = new XMLHttpRequest();
        xhr.open("POST", url + "graph");
        xhr.setRequestHeader("Content-Type", "application/json");
        xhr.onload = function() {
            response = JSON.parse(xhr.responseText);
            const status = response.status
            if (status === "OK") {

                let ctx = document.getElementById('myChart').getContext('2d');
                let chart = new Chart(ctx, {
                    type: 'line',

                    data: {
                        labels: response.xvalues,
                        datasets: [{
                            label: displayInput.value, // Название
                            borderColor: 'rgb(255, 99, 132)', // Цвет линии
                            data: response.yvalues // Данные каждой точки графика
                        }]
                    },

                    options: {
                        elements: {
                            point: {
                                radius: 0
                            }
                        }
                    }
                });

            } else {
                displayInput.value = status + ": " + response.message
            }
        }
        xhr.send(data);
    } else {
        alert("From value must be less then To value")
    }

}






