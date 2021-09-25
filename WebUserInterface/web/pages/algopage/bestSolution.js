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
                  url:"rawSolution",
                  timeout:2000,
                  success: function(webLessonsList){
                      //ilay
                  },
                  error:function(errorObj){
                      var myModal = new bootstrap.Modal(document.getElementById('algoRefModal'));
                      $("#titleModalLabel").text("Error!");
                      $("#bodyModalLabel").text(errorObj.responseText);
                      myModal.show();
                  }
              })
              break;
          case "Teacher":

              //teachers name and id's
              break;
          case "Class":
              console.log("Class");
              break;
      }
    })
})