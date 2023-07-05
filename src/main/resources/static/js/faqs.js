// FAQ Data
var faqData = [
  {
    question: "How can I request an asset for an employee?",
    answer: "To request an asset for an employee, please fill out the Asset Request form and submit it to the Asset Management department."
  },
  {
    question: "What is the process for returning an asset?",
    answer: "When an employee no longer requires an asset, they should contact the Asset Management department. They will provide instructions on returning the asset."
  },
  {
    question: "How can I check the availability of a specific asset?",
    answer: "You can check the availability of a specific asset by contacting the Asset Management department. They will provide you with the current status and availability details."
  },

];

// Function to generate FAQ HTML
function generateFAQHtml() {
  var faqContainer = document.getElementById("faq-container");
    faqContainer.style.fontStyle = "bold";
    faqContainer.style.color = "orange";
    faqContainer.style.margin = "10px";

    Frequently asked Questions

  for (var i = 0; i < faqData.length; i++) {
    var question = faqData[i].question;
    var answer = faqData[i].answer;

    var spaceElement = document.createElement("br");
    spaceElement.textContent = "";

    // Create question element
    var questionElement = document.createElement("div");
    questionElement.classList.add("faq-question");
    questionElement.textContent = question;

    // Create answer element
    var answerElement = document.createElement("div");
    answerElement.classList.add("faq-answer");
    answerElement.textContent = answer;

    // Append question and answer to container
    faqContainer.appendChild(questionElement);
    faqContainer.appendChild(spaceElement);
    faqContainer.appendChild(answerElement);
    faqContainer.appendChild(spaceElement);
    faqContainer.appendChild(spaceElement);

  }
}

// Call the function to generate FAQ HTML
generateFAQHtml();
