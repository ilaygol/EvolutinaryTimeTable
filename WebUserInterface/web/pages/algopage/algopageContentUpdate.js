$(function() {
    $.ajax({
        data: "",
        url: "welcome",
        timeout: 2000,
        error: function (loginURL) {
            window.location.replace(loginURL);
        },
        success: function (clientName) {
            $("#userName").text(clientName.trim());
        }
    });
});

$(function (){
    setInterval(algopageContentUpdate,2000);
});

$(function() {
    $.ajax({
        url: "updateSolvingUsers",
        success: function (usersList) {
            refreshSolvingUsersList(usersList);
        }
    });
});
function algopageContentUpdate()
{
    $.ajax({
        url:"updateSolvingUsers",
        timeout:2000,
        success:function (usersList){
            refreshSolvingUsersList(usersList)
        }
    });
}

function algoProgressUpdate()
{
    $.ajax({
        url:"progressUpdate",
        timeout:1000,
        success:function (progressData){
            refreshProgressLabels(progressData)
        }
    });
}


function refreshSolvingUsersList(userList)
{
    $("#solvingTableBody").empty();
    $.each(userList || [], function (index, solver) {
        $("<tr><td>" + solver["m_SolverName"] +
            "</td><td>" + solver["m_GenerationsMade"] +
            "</td><td>" + solver["m_BestFitness"] +
            "</td><td><button class='btn btn-primary btn-sm checkSetting' id='" + index + "' type='button'>Check Settings</button></td>" +
            "</td><td><button class='btn btn-success btn-sm watchSolution' id='" + index + "' type='button'>Best Solution</button></td></tr>").appendTo($("#solvingTableBody"))
    });
}

function refreshProgressLabels(progressData)
{
    $("#statusLine").empty().text("Generation made: "+progressData["m_GenerationMade"]);
    $("#updatesLine").empty().text("After "+progressData["m_ShowEveryGeneration"]+" generations, Best fitness: "+progressData["m_ShowEveryFitness"]);
    if(progressData["m_IsRunningAlgo"]===false) {
        stopBtnDisabilityManagement()
        clearInterval(labelsUpdate);
    }
}
