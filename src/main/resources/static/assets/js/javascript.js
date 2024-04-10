function myFunction() {
	var x = document.getElementById("myInput");
	if (x.type === "password") {
		x.type = "text";
	} else {
		x.type = "password";
	}
}
// Get the modal
var modal = document.getElementById('id01');

// When the user clicks anywhere outside of the modal, close it
window.onclick = function(event) {
	if (event.target == modal) {
		modal.style.display = "none";
	}
};
var modal = document.getElementById('sing');

// When the user clicks anywhere outside of the modal, close it
window.onclick = function(event) {
	if (event.target == modal) {
		modal.style.display = "none";
	}
};



// details fils 
function changeInput(dir) {
	if (dir == "right") {
		qty.value = Number(qty.value) + 1;
	} else if (qty.value == 1) {
		qty.value = Number(qty.value) + 0;
	} else {
		qty.value = Number(qty.value) - 1;
	}
}
let qty = document.getElementById("qty");
let left_btn = document.getElementById("minus");
let right_btn = document.getElementById("plus");


left_btn.addEventListener("click", () => {
	changeInput("left");
});
right_btn.addEventListener("click", () => {
	changeInput("right");
});
var coll = document.getElementsByClassName("collapsible");
var i;

for (i = 0; i < coll.length; i++) {
	coll[i].addEventListener("click", function() {
		this.classList.toggle("active");
		var content = this.nextElementSibling;
		if (content.style.display === "block") {
			content.style.display = "none";
		} else {
			content.style.display = "block";
		}
	});
};

