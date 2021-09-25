
var labelsUpdate;
$(function() {
    $("#startBtn").click(function () {
        var showEvery=document.getElementById("showEvery").value;
        $.ajax({
            data:"showEvery="+showEvery.toString(),
            url:"activate",
            timeout:2000,
            success: function(){
                console.log("algorithm was activated successfully");
                playBtnDisabilityManagement();
                $("#updatesLine").empty();
                labelsUpdate=setInterval(algoProgressUpdate,1000);
            },
            error: function(errorObject){
                var myModal = new bootstrap.Modal(document.getElementById('algoRefModal'));
                $("#titleModalLabel").text("ERROR!");
                $("#bodyModalLabel").text(errorObject.responseText);
                myModal.show();
            }

        });
    })
})

$(function() {
    $("#pauseBtn").click(function () {
        $.ajax({
            url:"pause",
            timeout: 2000,
            success: function(){
                console.log("paused the algorithm");
                pauseBtnDisabilityManagement();
                clearInterval(labelsUpdate);
            },
            error: function(errorObj){
                console.log("failed to pause algorithm because: "+errorObj.responseText);
            }
        })
    })
})

$(function() {
    $("#stopBtn").click(function () {
        $.ajax({
            url:"stopAlgo",
            timeout: 2000,
            success: function(progressData){
                console.log("stopped algorithm");
                stopBtnDisabilityManagement();
                clearInterval(labelsUpdate);
                $("#statusLine").empty().text("Generation made: "+progressData["m_GenerationMade"]);
                $("#updatesLine").empty().text("After "+progressData["m_GenerationMade"]+" generations, Best fitness: "+progressData["m_Fitness"]);
            },
            error: function(errorObj){
                console.log("failed to stop algorithm because: "+errorObj.responseText);
            }
        })
    })
})



function playBtnDisabilityManagement()
{
    document.getElementById("startBtn").disabled = true;
    document.getElementById("pauseBtn").disabled=false;
    document.getElementById("stopBtn").disabled=false;
    document.getElementById("addNewMutation").disabled = true;
    document.getElementById("submitBtn").disabled = true;
}

function pauseBtnDisabilityManagement()
{
    document.getElementById("startBtn").disabled = false;
    document.getElementById("pauseBtn").disabled=true;
    document.getElementById("stopBtn").disabled=false;
    document.getElementById("addNewMutation").disabled = false;
    document.getElementById("submitBtn").disabled = false;

}

function stopBtnDisabilityManagement()
{
    document.getElementById("startBtn").disabled = false;
    document.getElementById("pauseBtn").disabled=true;
    document.getElementById("stopBtn").disabled=true;
    document.getElementById("addNewMutation").disabled = false;
    document.getElementById("submitBtn").disabled = false;

}