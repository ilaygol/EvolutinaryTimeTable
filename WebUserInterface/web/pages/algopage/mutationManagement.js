$(function(){
    $("#addNewMutation").click(function(){
        $.ajax({
            url:"mutation",
            type: "PUT",
            timeout:2000,
            success:(function(){
                var myModal = new bootstrap.Modal(document.getElementById('algoRefModal'));
                $("#titleModalLabel").text("SUCCESS!");
                $("#bodyModalLabel").text("Mutation was added successfully");
                myModal.show();
            }),
            error: (function(errorObject){
                var myModal = new bootstrap.Modal(document.getElementById('algoRefModal'));
                $("#titleModalLabel").text("ERROR!");
                $("#bodyModalLabel").text(errorObject.responseText);
                myModal.show();
            })
        })
    })
})