const url = "http://localhost:2121/"

async function setProperties() {

    let response = {
        bgColor1: null,
        bgColor2: null,
        fontSize: null,
        fontColor: null,
    };

    const xhr = new XMLHttpRequest();
    xhr.open("GET", url + "properties");
    xhr.setRequestHeader("Content-Type", "application/json");
    xhr.onload = function() {
        response = JSON.parse(xhr.responseText);

        console.log(response.status)
        console.log(response.message)
        console.log(response.bgColor1)
        console.log(response.bgColor2)
        console.log(response.fontSize)
        console.log(response.fontColor)

        const status = response.status

        if (status === "OK") {
            let bgColor1 = response.bgColor1;
            let bgColor2 = response.bgColor2;
            let fontSize = response.fontSize;
            let fontColor = response.fontColor;

            document.querySelector('html').style.fontSize = fontSize;
            console.log("font size ok")
            document.querySelector('html').style.color = fontColor;
            console.log("font color ok")
            document.querySelector('body').style.background = "linear-gradient(236deg, " + bgColor1 + ", " + bgColor2 + ")";
            console.log("bg color ok")

        }
    }
    xhr.send();

    // response = await fetch(url + 'properties');


}
window.onpaint = setProperties();

