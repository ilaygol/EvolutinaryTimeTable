
$(function() {
    $.ajax({
        data: "DATA=SUBJECTS",
        url: "filedata",
        timeout: 2000,
        success: (function(subjectsList){
            $("#fileData").empty().text("The subjects are:").append("<br>");

            $.each(subjectsList || [],function (index,subject){
                $("#fileData").append("ID: "+subject["m_SubjectID"]+" | Name: "+subject["m_SubjectName"]+"<br>");
            });


        }),
        error: function (){
            console.log("Error printing file");
        }
    });
});

$(function() {
    $.ajax({
        data: "DATA=TEACHERS",
        url: "filedata",
        timeout: 2000,
        success: (function(teachersList){
            $("#fileData").append("The teachers are:"+"<br>");

            $.each(teachersList || [],function (index,teacher){
                $("#fileData").append("Teacher ID: "+teacher["m_TeacherID"]+" with Preferred working Hours: "+teacher["m_WorkingHours"]+" Subjects he teaches are:"+"<br>");
                $.each(teacher["m_TeacherSubjects"] || [],function (index,subject) {
                    $("#fileData").append("ID: "+subject["m_SubjectID"]+" | Name: "+subject["m_SubjectName"]+"<br>");
                });

            });


        }),
        error: function (){
            console.log("Error printing file");
        }
    });
});
