function myFunction() {
  var x = document.getElementById("chatframe");
  var y = document.getElementById("mybutton");
  
  if (x.style.display === "none") {
    x.style.display = "block";
    y.src="/img/cancel.png";
    y.height = "10";
    y.width = "10";
  } else {
    x.style.display = "none";
    y.src="/img/chat.png";
    y.height = "35";
    y.width = "35";
  }
  
}