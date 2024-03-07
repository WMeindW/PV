let productNumber = 0;
document.addEventListener("DOMContentLoaded", function (event) {
    let values;
    let name = document.getElementById("name");
    let surname = document.getElementById("surname");
    let add = document.getElementById("add");
    let form = document.getElementById("form");
    fetch("/products").then(r => {
        r.text().then((value) => {
            values = value;
            parseProducts(value);
        });
    })
    if (name != null && surname != null) {
        name.value = localStorage.getItem("name");
        surname.value = localStorage.getItem("surname");
    }
    add.addEventListener("click", function (event) {
        event.preventDefault();
        form.innerHTML += "<label>\n" +
            "                <select name=\"select\">\n" +
            "                </select>\n" +
            "            </label>\n" +
            "            <label>\n" +
            "                <input type=\"number\" value=\"1\" name=\"productNumber\"\n" +
            "                       required>\n" +
            "            </label>";
        parseProducts(values);
    })
});

function parseProducts(value) {
    let products = document.getElementsByName("select");
    let values = value.split(",");
    for (let i = 0; i < values.length - 1; i++) {
        products[productNumber].innerHTML += '<option value="' + values[i] + '">' + values[i] + '</option>';
    }
    productNumber++;
}
