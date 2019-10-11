var url = '../KA2/api/person/'
//'https://jjugroup.ga/KA1/api/car/'

var personTable = document.getElementById('persons_table');
var hobbyList = document.getElementById('hobbyList');

function personMapper(array) {

    var c = array.map(el => '<tr><td>' + el.id + '</td>\n\
    <td>'+ el.email + '</td>\n\
    <td>'+ el.firstName + '</td>\n\
    <td>'+ el.lastName + '</td>\n\
    <td>'+ el.address + '</td>\n\
    <td>'+ el.city + '</td>\n\
    <td>'+ hobbyMapper(el.hobbies) + '</td>\n\
    <td>'+ phoneMapper(el.phones) + '</td></tr>');
    return c.join('');
}

function hobbyMapper(hobbies) {
    const map1 = hobbies.map(el => el.name);
    return map1.join(', ');
}

function phoneMapper(phones) {
    const map1 = phones.map(el => el.description + ": " + el.number);
    return map1.join(', ');
}

function hobbyListMapper(array) {

    var c = array.map(el => '<div class="form-check"><input class="form-check-input" type="checkbox" id="' + el.id + '">\n\
        <label class="form-check-label" for="'+ el.id + '">\n\
        ' + el.name + '</label></div>');
    return c.join('');
}

var persons;
//console.log(mapper(cars))
//carTable.innerHTML = mapper(cars);
window.onload = function () {
    allPersons();
    allHobbies();
}
var urlAll = url + 'all';

function allPersons() {
    fetch(urlAll)
        .then(res => res.json())
        .then(data => {
            personTable.innerHTML = personMapper(data);
            persons = data;
        });
};

var hobbies;

var urlAllh = url + 'hobby/all';

function allHobbies() {
    fetch(urlAllh)
        .then(res => res.json())
        .then(data => {
            hobbyList.innerHTML = hobbyListMapper(data);
            hobbies = data;
        });
};


let personPostBtn = document.getElementById('personPostBtn')
personPostBtn.addEventListener('click', addPerson)
function addPerson() {
    var checkedHobbys = []
    var temphobbys = hobbyList.children
    for(var i = 0; i < temphobbys.length; i++) {
        if (temphobbys.item(i).firstChild.checked == true){
            checkedHobbys.push({id: temphobbys.item(i).firstChild.id})}
    
    }    
    
    let addPersonFirstName = document.getElementById('personFirstName').value
    let addPersonLastName = document.getElementById('personLastName').value
    let addPersonEmail = document.getElementById('personEmail').value
    let addPersonAddress = document.getElementById('personAddress').value
    let addPersonZip = document.getElementById('personZip').value
    let addPersonPhoneType = document.getElementById('personPhoneType').value
    let addPersonPhoneNumber = document.getElementById('personPhoneNumber').value
    let options = {
        method: "POST",
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            "email": addPersonEmail,
            "firstName": addPersonFirstName,
            "lastName": addPersonLastName,
            "address": addPersonAddress,
            "city": addPersonZip,
            "phones": [
              {
                "number": addPersonPhoneNumber,
                "description": addPersonPhoneType
              }
            ],
            "hobbies": checkedHobbys
        })
    }

    fetch("../KA2/api/person", options)

}