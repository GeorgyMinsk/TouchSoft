const arrayDisk = document.getElementById('disksOnQueue');
const rightDisks = document.getElementById('rightDisks');
const leftDisks = document.getElementById('leftDisks');
const buttom = document.getElementById('button');
const input = document.getElementById('input');
const erroMessage = document.getElementById('erroMessage');
const message = document.getElementById('message');

let weightLeft = 0;
let weightRight = 0;

// Проверка на равенство левой и правой части
function checkOnEquality() {
  if (weightLeft === weightRight ) {
    message.innerHTML = "Rod is ready!!!";
  } else {
    message.innerHTML = "Rod is not ready.";
  }
}
// Проверка введённых данных
function clickOnButton() {
  if (checkingTheEnteredDisks (parseFloat (input.value))) {
    addDisksOnQueue (parseInt (input.value));
    erroMessage.hidden = true;
  } else {
    erroMessage.hidden = false;
  }
}

function checkingTheEnteredDisks(disk) {
  if (disk > 0 && disk < 20 && disk !=="") {
    return true;
  }else {
    return false;
  }
}
// Добавени диска в очередь
function addDisksOnQueue (value) {
  let disk = document.createElement('div');
  disk.innerHTML = value;
  disk.classList.add('elementOfDisk')
  disk.id = 'disksOnQueue' + Date.now();
  arrayDisk.appendChild(disk)
}
// Добавени диска на штангу
function addDisksToRod(location, value, locClass) {
  let disk = document.createElement('div');
  disk.innerHTML = value.innerHTML;
  disk.classList.add('elementOfDisk');
  disk.id = 'disksToRod' + location + Date.now();
  locClass.appendChild(disk);
}

buttom.addEventListener("click",  clickOnButton);
// Добавление с очереди на левую сторону
arrayDisk.addEventListener('click', function (event) {
  if (event.target.parentElement.className !== 'disksOnQueue') return;
  weightLeft += parseInt(event.target.innerHTML);
  addDisksToRod("Left", event.target, leftDisks );
  arrayDisk.children[event.target.id].remove();
  checkOnEquality();
});
// Добавление с очереди на правую сторону
arrayDisk.addEventListener('contextmenu', function (event) {
  if (event.target.parentElement.className !== 'disksOnQueue') return;
  weightRight += parseInt(event.target.innerHTML);
  addDisksToRod("Right", event.target, rightDisks )
  arrayDisk.children[event.target.id].remove();
  checkOnEquality();
});
// Добавление в очередь с правой стороны
rightDisks.addEventListener('click', function (event) {
  if (event.target.parentElement.className !== 'diskOnRod') return;
  weightRight -= parseInt(event.target.innerHTML);
  addDisksOnQueue( event.target.innerHTML);
  rightDisks.children[event.target.id].remove();
  checkOnEquality();
});
// Добавление в очередь с левой стороны
leftDisks.addEventListener('click', function (event) {
  if (event.target.parentElement.className !== 'diskOnRod') return;
  weightLeft -= parseInt(event.target.innerHTML);
  addDisksOnQueue( event.target.innerHTML);
  leftDisks.children[event.target.id].remove();
  checkOnEquality();

});
