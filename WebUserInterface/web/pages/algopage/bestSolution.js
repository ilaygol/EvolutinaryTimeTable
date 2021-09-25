$(function(){
    $("#bestSolutionButton").click(function(){
        var select = document.getElementById('showValue');
        var value = select.options[select.selectedIndex].value;
      switch(value)
      {
          case "":
              break;
          case "Raw":
              console.log("Raw");
              $.ajax({

              })
              break;
          case "Teacher":
              console.log("Teacher");
              break;
          case "Class":
              console.log("Class");
              break;
      }
    })
})