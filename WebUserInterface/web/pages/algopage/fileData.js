
$(function() {
    $.ajax({
        data: "",
        url: "filedata",
        timeout: 2000,
        success: (function(managerIndex){
            $("#fileID").text("manager index is: "+managerIndex);
        }),
        error: function (){
            console.log("Error moving to algo page");
        }
    });
});
