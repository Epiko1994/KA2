var url = '../KA2/api/person/'
//'https://jjugroup.ga/KA1/api/car/'

var personTable = document.getElementById('persons_table');
var hobbyList = document.getElementById('hobbyList');

function personMapper(array){

    var c = array.map(el=>'<tr><td>'+el.id+'</td>\n\
    <td>'+el.email+'</td>\n\
    <td>'+el.firstName+'</td>\n\
    <td>'+el.lastName+'</td>\n\
    <td>'+el.address+'</td>\n\
    <td>'+el.city+'</td>\n\
    <td>'+hobbyMapper(el.hobbies)+'</td>\n\
    <td>'+phoneMapper(el.phones)+'</td></tr>');
    return c.join('');
}

function hobbyMapper(hobbies){
    const map1 = hobbies.map(el=> el.name);
    return map1.join(', ');
    }

function phoneMapper(phones){
    const map1 = phones.map(el=> el.description + ": " + el.number);
    return map1.join(', ');
    }

function hobbyListMapper(array){

        var c = array.map(el=>'<div class="form-check"><input class="form-check-input" type="checkbox" value="" id="' + el.id + '">\n\
        <label class="form-check-label" for="'+ el.id +'">\n\
        ' + el.name+ '</label></div>');
        return c.join('');
    }

var persons;
//console.log(mapper(cars))
//carTable.innerHTML = mapper(cars);
window.onload = function(){
    allPersons();
    allHobbies();
}
var urlAll = url + 'all';

function allPersons(){
fetch(urlAll)
    .then(res => res.json())
    .then(data => {
        personTable.innerHTML = personMapper(data);
        persons = data;
        });
    };

var hobbies;

var urlAllh = url + 'hobby/all';

function allHobbies(){
        fetch(urlAllh)
            .then(res => res.json())
            .then(data => {
                console.log(hobbyListMapper(data))
                hobbyList.innerHTML = hobbyListMapper(data);
                hobbies = data;
                });
            };

btn1.onclick = function(){
    var inputFrom = document.getElementById("input1").value;
    var inputTo = document.getElementById("input2").value;
if (inputFrom === '' || inputTo === ''){
    if(inputFrom === ''){
    alert('Please enter min price!')
    }if (inputTo === ''){
    alert('Please enter max price!')
    }
} else {
    filteredcars = cars.filter(function(el){return el.price >= inputFrom && el.price <= inputTo});
    carTable.innerHTML = mapper(filteredcars);
}
};

function sorter(sortBy){
    var key = sortBy;
        function compare(a, b) {
            const comA = a[key];
            const comB = b[key];
            let comparison = 0;
            if (comA > comB) {
            comparison = 1;
            } else if (comA < comB) {
            comparison = -1;
            }
            return comparison;
        }
  return compare;
}

btn2.onclick = function(){
    var select = document.getElementById("inputGroupSelect01");
    var selectedSort = select.options[select.selectedIndex].value;
    carTable.innerHTML = mapper(filteredcars.sort(sorter(selectedSort)));

    //console.log(cars.sort(dynamicSort(selectedSort)));

    //console.log(selectedSort);
    
};

btn4.onclick = allCars;