var slider_alpha = document.getElementById("alpha");
var output_alpha = document.getElementById("alpha-val");
output_alpha.innerHTML = slider_alpha.value; // Display the default slider value

// Update the current slider value (each time you drag the slider handle)
slider_alpha.oninput = function() {
    output_alpha.innerHTML = this.value;
}

var slider_beta = document.getElementById("beta");
var output_beta= document.getElementById("beta-val");
output_beta.innerHTML = slider_beta.value; // Display the default slider value

// Update the current slider value (each time you drag the slider handle)
slider_beta.oninput = function() {
    output_beta.innerHTML = this.value;
}

var slider_edge = document.getElementById("edge");
var output_edge= document.getElementById("edge-val");
output_beta.innerHTML = slider_edge.value; // Display the default slider value

// Update the current slider value (each time you drag the slider handle)
slider_edge.oninput = function() {
    output_edge.innerHTML = this.value;
}