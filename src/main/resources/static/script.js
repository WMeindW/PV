let productNumber = 0;
document.addEventListener("DOMContentLoaded", function () {
    let name = document.getElementById("name");
    let surname = document.getElementById("surname");
    let products = document.getElementsByName("products");
    let orderDiv = document.getElementById("order");
    if (name != null && surname != null) {
        name.value = localStorage.getItem("name");
        surname.value = localStorage.getItem("surname");
    }
    if (products.length > 0) {
        order();
    }
    if (orderDiv != null) {
        profile();
    }
});

function parseProducts(value) {
    let products = document.getElementsByName("products");
    let values = value.split(",");
    for (let i = 0; i < values.length - 1; i++) {
        products[productNumber].innerHTML += '<option value="' + values[i] + '">' + values[i] + '</option>';
    }
    productNumber++;
}

function order() {
    let values;
    let add = document.getElementById("add");
    let form = document.getElementById("form");
    fetch("/products").then(r => {
        r.text().then((value) => {
            values = value;
            parseProducts(value);
        });
    });
    if (add != null) {
        add.addEventListener("click", function (event) {
            event.preventDefault();
            form.innerHTML += "<label>\n" + "                <select name=\"products\">\n" + "                </select>\n" + "            </label>\n" + "            <label>\n" + "                <input type=\"number\" value=\"1\" name=\"productNumber\"\n" + "                       required>\n" + "            </label>";
            parseProducts(values);
        });
    }
}

function profile() {
    fetch("/orders?name=" + localStorage.getItem("name") + "&surname=" + localStorage.getItem("surname")).then(r => {
        r.text().then((value) => {
            parseOrders(value);
        });
    });
}

function parseOrders(value) {
    let order = document.getElementById("order");
    order.innerHTML += '<option selected="selected" disabled hidden="hidden">Chose an order</option>';
    for (let i = 0; i < value.split(",").length - 1; i++) {
        order.innerHTML += '<option name="options" value="' + value.split(",")[i] + '">' + value.split(",")[i] + '</option>';
    }
    order.addEventListener("change", function (e) {
        fetch("/order?name=" + localStorage.getItem("name") + "&surname=" + localStorage.getItem("surname") + "&date=" + e.target[e.target.selectedIndex].value.toString()).then(r => {
            r.text().then((value) => {
                const object = JSON.parse(value);
                console.log(object);
            });
        });
    });
}