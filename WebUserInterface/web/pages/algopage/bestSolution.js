function printRulesData(rulesDataList)
{
    var rulesStr="";
    $.each(rulesDataList || [],function (index,rule) {
        rulesStr+= "<tr><td><table style='margin: auto;' class='table table-sm table-striped text-center align-middle table-bordered border'>" +
            "<tbody><tr><th>" + "Name" + "</th><td>" + rule["m_Name"] + "</td></tr>" +
            "<tr><th>" + "Type" + "</th><td>" + rule["m_Type"] + "</td></tr>" +
            "<tr><th>" + "Grade" + "</th><td>" + rule["m_Grade"] + "</td></tr>";
        if(rule["m_Name"]==="WORKINGHOURSPREFERENCE")
        {
            rulesStr+="<tr><th>" + "Working Hours" + "</th><td>" + rule["m_TotalHours"] + "</td></tr>";
        }
        rulesStr+= "</tbody></table></td></tr>";
    });

    $("<table class='table table-striped table-hover text-center table-bordered align-middle'>" +
        "<thead>" +
        "<tr class='table-warning'>" +
        "<th>Rules</th>" +
        "</tr>" +
        "</thead>" +
        "<tbody>"+rulesStr+"</tbody>" +
        "</table>").appendTo($("#solutionRules").empty());
}

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
                  success: function(rawSolutionData){
                      $("<div class='row p-3'>" +
                          "<div class='col-9 border-end'><div id='rawSolutionContent' class='d-flex flex-row flex-wrap justify-content-between gap-1'></div></div>" +
                          "<div class='col-3' id='solutionRules'></div></div>").appendTo($("#bestSolutionContent").empty());
                      $.each(rawSolutionData["m_RawSolution"] || [],function (index,lesson) {
                          $("<table class='table table-sm table-striped w-auto text-center align-middle table-bordered border'>" +
                              "<tbody><tr><td>" + "Day" + "</td><td>" + lesson["m_Day"] + "</td></tr>" +
                              "<tr><td>" + "Hour" + "</td><td>" + lesson["m_Hour"] + "</td></tr>" +
                              "<tr><td>" + "Class" + "</td><td>" + lesson["m_ClassName"] + "</td></tr>" +
                              "<tr><td>" + "Teacher" + "</td><td>" + lesson["m_TeacherName"] + "</td></tr>" +
                              "<tr><td>" + "Subject" + "</td><td>" + lesson["m_SubjectName"] + "</td></tr>" +
                              "</tbody></table>").appendTo($("#rawSolutionContent"));
                      });
                      printRulesData(rawSolutionData["m_RuleDataList"]);
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
                          "<div class='col-xxl-1 border-end'><h6>Teacher Selection</h6><div id='teachersList' class='list-group'></div></div>" +
                          "<div class='col-xxl-8 border-end' id='teacherSolutionContent'></div>" +
                          "<div class='col-xxl-3' id='solutionRules'></div>" +
                          "</div>")
                          .appendTo($("#bestSolutionContent").empty());
                      $.each(teachersList || [],function (index,teacher) {
                          $("<button type='button' class='list-group-item list-group-item-action list-group-item-light bestSolutionTeacherBTN'" + " id='"+teacher["m_TeacherID"]+"'>" +
                              teacher["m_TeacherName"] +
                          "</button>").appendTo($("#teachersList"));
                      });
                      $(".bestSolutionTeacherBTN").click(function(){
                          console.log("clicked on teacher "+this.getAttribute("id"));
                          $.ajax({
                              data:"teacherID="+this.getAttribute("id"),
                              url:"teacherSolution",
                              timeout:2000,
                              success:function(teacherSolution){
                                  //day --->m_Day
                                  //hour --->m_Hour
                                  //class name --->m_ClassName
                                  //teacher name --->m_TeacherName
                                  //subject name --->m_SubjectName
                                  var teacherTimeTable=teacherSolution["m_Solution"];
                                  let numOfDays=teacherTimeTable.length;
                                  var daysStr="";
                                  for(let i=0; i<numOfDays;i++)
                                  {
                                      daysStr+="<th>"+(i+1)+"</th>";
                                  }
                                  let numOfHours=teacherTimeTable[0]["m_LessonsInDay"].length;
                                  var hoursStr="";
                                  for(let i=0; i<numOfHours;i++)
                                  {
                                      hoursStr+="<th>"+(i+1)+"</th>";
                                  }

                                  $("<table style='margin: auto' class='table table-striped table-hover text-center table-bordered align-middle'>" +
                                      "<thead>" +
                                      "<tr class='table-primary align-middle'>" +
                                      "<th>Hour / Day</th>" +
                                      daysStr +
                                      "</tr>" +
                                      "</thead>" +
                                      "<tbody id='lessonsTableBody'></tbody>" +
                                      "</table>").appendTo($("#teacherSolutionContent").empty());

                                  var rowStr="";
                                  for(let h=0; h<numOfHours;h++)
                                  {
                                      rowStr+="<tr><th>"+(h+1)+"</th>";
                                      for(let d=0; d<numOfDays;d++)
                                      {
                                          rowStr+="<td>";
                                          if(teacherTimeTable[d]["m_LessonsInDay"][h]) {
                                              rowStr += "<table style='margin: auto;' class='table table-sm table-striped w-auto text-center align-middle table-bordered border'>" +
                                                  "<tbody><tr><td>" + "Day" + "</td><td>" + teacherTimeTable[d]["m_LessonsInDay"][h]["m_Day"] + "</td></tr>" +
                                                  "<tr><td>" + "Hour" + "</td><td>" + teacherTimeTable[d]["m_LessonsInDay"][h]["m_Hour"] + "</td></tr>" +
                                                  "<tr><td>" + "Class" + "</td><td>" + teacherTimeTable[d]["m_LessonsInDay"][h]["m_ClassName"] + "</td></tr>" +
                                                  "<tr><td>" + "Teacher" + "</td><td>" + teacherTimeTable[d]["m_LessonsInDay"][h]["m_TeacherName"] + "</td></tr>" +
                                                  "<tr><td>" + "Subject" + "</td><td>" + teacherTimeTable[d]["m_LessonsInDay"][h]["m_SubjectName"] + "</td></tr>" +
                                                  "</tbody></table>";
                                          }
                                          rowStr+="</td>";
                                      }
                                      rowStr+="</tr>"
                                      $(rowStr).appendTo($("#lessonsTableBody"));
                                      rowStr="";
                                  }

                                  //rules
                                  printRulesData(teacherSolution["m_RuleDataList"]);
                              },
                              error:function(){
                                  console.log("failed to get teacher schedule");
                              }
                          })
                      })
                  },
                  error:function (errorObject){
                      console.log("failed to get Teachers id names list")
                      var myModal = new bootstrap.Modal(document.getElementById('algoRefModal'));
                      $("#titleModalLabel").text("ERROR!");
                      $("#bodyModalLabel").text(errorObject.responseText);
                      myModal.show();
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
                          "<div class='col-xxl-1 border-end'><h6>Class Selection</h6><div id='classesList' class='list-group'></div></div>" +
                          "<div class='col-xxl-8 border-end' id='classSolutionContent'></div>" +
                          "<div class='col-xxl-3' id='solutionRules'></div>" +
                          "</div>")
                          .appendTo($("#bestSolutionContent").empty());
                      $.each(classesList || [],function (index,clazz) {
                          $("<button type='button' class='list-group-item list-group-item-action list-group-item-light bestSolutionClassBTN'" + " id='"+clazz["m_ClassID"]+"'>" +
                              clazz["m_ClassName"] +
                              "</button>").appendTo($("#classesList"));
                      });
                      $(".bestSolutionClassBTN").click(function(){
                          console.log("clicked on class with id "+this.getAttribute("id"));
                          $.ajax({
                              data:"classID="+this.getAttribute("id"),
                              url:"classSolution",
                              timeout:2000,
                              success:function(classSolution){
                                  //day --->m_Day
                                  //hour --->m_Hour
                                  //class name --->m_ClassName
                                  //teacher name --->m_TeacherName
                                  //subject name --->m_SubjectName
                                  var classTimeTable=classSolution["m_Solution"];
                                  let numOfDays=classTimeTable.length;
                                  var daysStr="";
                                  for(let i=0; i<numOfDays;i++)
                                  {
                                      daysStr+="<th>"+(i+1)+"</th>";
                                  }
                                  let numOfHours=classTimeTable[0]["m_LessonsInDay"].length;
                                  var hoursStr="";
                                  for(let i=0; i<numOfHours;i++)
                                  {
                                      hoursStr+="<th>"+(i+1)+"</th>";
                                  }

                                  $("<table style='margin: auto' class='table table-striped table-hover text-center table-bordered align-middle'>" +
                                      "<thead>" +
                                      "<tr class='table-primary align-middle'>" +
                                      "<th>Hour / Day</th>" +
                                      daysStr +
                                      "</tr>" +
                                      "</thead>" +
                                      "<tbody id='lessonsTableBody'></tbody>" +
                                      "</table>").appendTo($("#classSolutionContent").empty());

                                  var rowStr="";
                                  for(let h=0; h<numOfHours;h++)
                                  {
                                      rowStr+="<tr><th>"+(h+1)+"</th>";
                                      for(let d=0; d<numOfDays;d++)
                                      {
                                          rowStr+="<td>";
                                          if(classTimeTable[d]["m_LessonsInDay"][h]) {
                                              rowStr += "<table style='margin: auto;' class='table table-sm table-striped w-auto text-center align-middle table-bordered border'>" +
                                                  "<tbody><tr><td>" + "Day" + "</td><td>" + classTimeTable[d]["m_LessonsInDay"][h]["m_Day"] + "</td></tr>" +
                                                  "<tr><td>" + "Hour" + "</td><td>" + classTimeTable[d]["m_LessonsInDay"][h]["m_Hour"] + "</td></tr>" +
                                                  "<tr><td>" + "Class" + "</td><td>" + classTimeTable[d]["m_LessonsInDay"][h]["m_ClassName"] + "</td></tr>" +
                                                  "<tr><td>" + "Teacher" + "</td><td>" + classTimeTable[d]["m_LessonsInDay"][h]["m_TeacherName"] + "</td></tr>" +
                                                  "<tr><td>" + "Subject" + "</td><td>" + classTimeTable[d]["m_LessonsInDay"][h]["m_SubjectName"] + "</td></tr>" +
                                                  "</tbody></table>";
                                          }
                                          rowStr+="</td>";
                                      }
                                      rowStr+="</tr>"
                                      $(rowStr).appendTo($("#lessonsTableBody"));
                                      rowStr="";
                                  }

                                  //rules
                                  printRulesData(classSolution["m_RuleDataList"]);
                              },
                              error:function(){
                                  console.log("failed to get Class schedule");
                              }
                          })
                      })
                  },
                  error:function (errorObject){
                      console.log("failed to get classes id names list")
                      var myModal = new bootstrap.Modal(document.getElementById('algoRefModal'));
                      $("#titleModalLabel").text("ERROR!");
                      $("#bodyModalLabel").text(errorObject.responseText);
                      myModal.show();
                  }
              })
              break;
      }
    })
})

