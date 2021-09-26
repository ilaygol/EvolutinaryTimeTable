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
                      $("<div id='rawSolutionContent' class='d-flex flex-row flex-wrap justify-content-between p-3 gap-1'></div>").appendTo($("#bestSolutionContent").empty());
                      $.each(webLessonsList || [],function (index,lesson) {
                          $("<table class='table table-sm table-striped w-auto text-center align-middle table-bordered border'>" +
                              "<tbody><tr><td>" + "Day" + "</td><td>" + lesson["m_Day"] + "</td></tr>" +
                              "<tr><td>" + "Hour" + "</td><td>" + lesson["m_Hour"] + "</td></tr>" +
                              "<tr><td>" + "Class" + "</td><td>" + lesson["m_ClassName"] + "</td></tr>" +
                              "<tr><td>" + "Teacher" + "</td><td>" + lesson["m_TeacherName"] + "</td></tr>" +
                              "<tr><td>" + "Subject" + "</td><td>" + lesson["m_SubjectName"] + "</td></tr>" +
                              "</tbody></table>").appendTo($("#rawSolutionContent"));
                      });
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
                      $("<div class='row p-3'>" +
                          "<div class='col-auto border-end'><h6>Teacher Selection</h6><div id='teachersList' class='list-group'></div></div>" +
                          "<div class='col' id='teacherSolutionContent'></div>" +
                          "</div>")
                          .appendTo($("#bestSolutionContent").empty());
                      $.each(teachersList || [],function (index,teacher) {
                          $("<button type='button' class='list-group-item list-group-item-action list-group-item-light'" + " id='"+teacher["m_TeacherID"]+"'>" +
                              teacher["m_TeacherName"] +
                          "</button>").appendTo($("#teachersList"));
                      });
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
                      $("<div class='row p-3'>" +
                          "<div class='col-auto border-end'><h6>Class Selection</h6><div id='classesList' class='list-group'></div></div>" +
                          "<div class='col' id='classSolutionContent'></div>" +
                          "</div>")
                          .appendTo($("#bestSolutionContent").empty());
                      $.each(classesList || [],function (index,clazz) {
                          $("<button type='button' class='list-group-item list-group-item-action list-group-item-light'" + " id='"+clazz["m_ClassID"]+"'>" +
                              clazz["m_ClassName"] +
                              "</button>").appendTo($("#classesList"));
                      });
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