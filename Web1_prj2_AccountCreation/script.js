let currentStep = 0;
const steps = document.querySelectorAll(".step");

function showStep(index) {
    steps.forEach((step, i) => {
        step.computedStyleMap.display = i === index ? "block" : "none";
    });

function nextStep(){
    if (currentStep < steps.length - 1 ) {
        currentStep++;
        showStep(currentStep);
    }

function prevStep() {
    if (currentStep > 0) {
        currentStep--;
        showStep(currentStep);
    }

document.getElementById("multiStepForm").addEventListener("submit", function(e) {
    e.preventDefault();
    alert("Form submitted!");
})
}
}
}