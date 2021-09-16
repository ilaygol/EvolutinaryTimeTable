
$(function() {
    $.ajax({
        data: "",
        url: "filedata",
        timeout: 2000,
        success: (function(webFileData){
            $("#fileData").empty().text("The subjects are:").append("<br>");
            $.each(webFileData["m_SubjectsData"] || [],function (index,subject){
                $("#fileData").append("ID: "+subject["m_SubjectID"]+" | Name: "+subject["m_SubjectName"]+"<br>");
            });
            $("#fileData").append("The teachers are:"+"<br>");
            $.each(webFileData["m_TeacherData"] || [],function (index,teacher){
                $("#fileData").append("Teacher ID: "+teacher["m_TeacherID"]+" with Preferred working Hours: "+teacher["m_WorkingHours"]+" Subjects he teaches are:"+"<br>");
                $.each(teacher["m_TeacherSubjects"] || [],function (index,subject) {
                    $("#fileData").append("ID: "+subject["m_SubjectID"]+" | Name: "+subject["m_SubjectName"]+"<br>");
                });

            });
            $("#fileData").append("The classes are:"+"<br>");
            $.each(webFileData["m_ClassData"] || [],function (index,clazz){
                $("#fileData").append("Class ID: "+clazz["m_ClassID"]+", The subjects taught in this class are:"+"<br>");
                $.each(clazz["m_ClassSubjects"] || [],function (index,subject) {
                    $("#fileData").append("ID: "+subject["m_SubjectID"]+" | Name: "+subject["m_SubjectName"]+"<br>");
                });
            });
            $("#fileData").append("The rule are:"+"<br>");
            $.each(webFileData["m_RuleData"] || [],function (index,rule){
                $("#fileData").append("Rule name: "+rule["m_RuleName"]+" | Rule type: "+rule["m_RuleType"]+"<br>");
            });


        }),
        error: function (){
            console.log("Error printing file");
        }
    });
});


