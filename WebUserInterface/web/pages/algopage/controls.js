$(function() {
    $("#startBtn").click(function () {
        var showEvery=document.getElementById("showEvery").value;
        $.ajax({
            data:"showEvery="+showEvery.toString(),
            url:"activate",
            timeout:2000,
            success: function(){
                console.log("algorithm was activated successfully")
            },
            error: function(errorObject){
                var myModal = new bootstrap.Modal(document.getElementById('algoRefModal'));
                $("#titleModalLabel").text("ERROR!");
                $("#bodyModalLabel").text(errorObject.responseText);
                myModal.show();
            }

        })

    })
})