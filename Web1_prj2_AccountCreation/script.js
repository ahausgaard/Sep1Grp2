const steps = document.querySelectorAll(".step");
const person = {}
let currentStep = 0;

function showStep(index) {
  steps.forEach((step, i) => {
    step.style.display = i === index ? "block" : "none";
  });
}

function nextStep() {
  if (currentStep < steps.length - 1) {
    currentStep++;
    showStep(currentStep);
  }
}

function prevStep() {
  if (currentStep > 0) {
    currentStep--;
    showStep(currentStep);
  }
}

function page1Submit(){
  const firstName = document.querySelector("#firstName")?.value
  const lastName = document.querySelector("#lastName")?.value
  const email = document.querySelector("#email")?.value
  const phone = document.querySelector("#phone")?.value
  const dateOfBirth = document.querySelector("#dateOfBirth")?.value
  const gender = document.querySelector('input[name="gender"]:checked')?.value
  const password = document.querySelector("#password")?.value
  const passwordCheck = document.querySelector("#passwordCheck")?.value  

  let errors = [];

  // Basic checks
  if (firstName === null) errors.push("First name is required.");
  if (lastName === null) errors.push("Last name is required.");
  if (!email.match(/^[^\s@]+@[^\s@]+\.[^\s@]+$/)) errors.push("Invalid email format.");
  
  // Password checks
  if (password.length < 8) errors.push("Password must be at least 8 characters.");
  if (password !== passwordCheck) errors.push("Passwords do not match.");

  // Phone check (basic)
  if (!phone.match(/^(\+45\s?)?[1-9]{2}\s?[0-9]{2}\s?[0-9]{2}\s?[0-9]{2}$/)) {
    errors.push("Invalid Danish phone number format.");
  }

  if (!gender) errors.push("Please select a gender.");

  if (errors.length > 0) {
    alert(errors.join("\n"));
    return false;
  }


  person.firstName = firstName
  person.lastName = lastName
  person.email = email
  person.phone = phone
  person.dateOfBirth = dateOfBirth
  person.gender = gender
  console.log(person)
  nextStep()
}


function page2Submit(){
  
const height = document.querySelector('#height').value;
  const bodyType = document.querySelector('input[name="body_type"]:checked')?.value;
  const education = document.querySelector('#education').value;
  const occupation = document.querySelector('#occupation').value;
  const smoking = document.querySelector('input[name="Smoking"]:checked')?.value;
  const drinking = document.querySelector('input[name="Drinking"]:checked')?.value;

  let errors = []

  // Basic checks
  if (height < 40) errors.push("Please select your correct height.")
  if (!bodyType) errors.push("Please select a bodytype.");
  if (!education) errors.push("Please select an education level.");
  if (!occupation) errors.push("Please enter your current occupation");
  if (!smoking) errors.push("Please select your smoking habits.");
  if (!drinking) errors.push("Please select your drinking habits.");
  
  if (errors.length > 0) {
    alert(errors.join("\n"));
    return false;
  }

  person.height = height
  person.bodyType = bodyType
  person.education = education
  person.occupation = occupation
  person.smoking = smoking
  person.drinking = drinking
  console.log(person)
  nextStep()
}

function page3Submit (){
  /*Interests*/
  const checkedBoxes = document.querySelectorAll('.interestSection input[type="checkbox"]:checked')
  const interests = Array.from(checkedBoxes).map(h => h.id)
  const interestsTotal = interests.length
  const about = document.querySelector("#aboutMe")?.value


  let errors = []

  if (!about) errors.push("Please write a short text about yourself.");
  if (interestsTotal < 3) errors.push("Please choose at least 3 interests.")
  if (errors.length > 0) {
    alert(errors.join("\n"));
    return false;
  }

  person.interests = interests
  person.about = about  
  nextStep()
}

function finalSubmit()
{
  localStorage.setItem(person, JSON.stringify(person));
}

document.addEventListener("DOMContentLoaded", () => {
  showStep(currentStep);

  document.getElementById("multiStepForm").addEventListener("submit", function (e) {
    e.preventDefault();
    alert("Form submitted!");
  });
});