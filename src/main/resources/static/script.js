document.addEventListener("DOMContentLoaded", function (event) {
    let name = document.getElementById("name");
    let surname = document.getElementById("surname");
    let add = document.getElementById("add");
    let form = document.getElementById("form");
    if (name != null && surname != null) {
        name.value = localStorage.getItem("name");
        surname.value = localStorage.getItem("surname");
    }
    add.addEventListener("click", function (event) {
        event.preventDefault();
        form.innerHTML += "<label>\n" +
            "            <input type=\"text\" name=\"itemName\" placeholder=\"Item name\" required>\n" +
            "        </label>\n" +
            "        <label>\n" +
            "            <input type=\"text\" contenteditable=\"false\" value=\"1000Kč\" name=\"itemPrice\" placeholder=\"Item price\"\n" +
            "                   required>\n" +
            "        </label>";

    })
});