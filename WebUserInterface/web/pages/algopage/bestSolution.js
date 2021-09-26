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
              console.log("sending ajax to get Teachers ID Names List");
              $.ajax({
                  url:"teachersNamesList",
                  timeout: 2000,
                  success:function (teachersList){
                     //m_TeacherID ---> teacher id member
                     //m_TeacherName ---> teacher name member
                  },
                  error:function (){
                      console.log("failed to get Teachers id names list")
                  }
              })
              break;

          case "Class":
              console.log("sending ajax to get Teachers ID Names List");
              $.ajax({
                  url:"classesNamesList",
                  timeout: 2000,
                  success:function (classesList){
                      //m_ClassID -->class id member
                      //m_ClassName --->class name member
                  },
                  error:function (){
                      console.log("failed to get classes id names list")
                  }
              })
              break;
      }
    })
})

// private Integer m_Day;
// private Integer m_Hour;
// private String m_ClassName;
// private String m_TeacherName;
// private String m_SubjectName;