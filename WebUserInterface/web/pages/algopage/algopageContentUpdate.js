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
            "</td><td><button class='btn btn-primary checkSetting' id='" + index + "' type='button'>Check Settings</button></td>" +
            "</td><td><button class='btn btn-primary watchSolution' id='" + index + "' type='button'>Best Solution</button></td></tr>").appendTo($("#solvingTableBody"))
    });
}

function refreshProgressLabels(progressData)
{
    $("#statusLine").empty().text("Generation made: "+progressData["m_Generation"]);
    if(progressData["m_Generation"]%progressData["m_ShowEvery"]===0)
    {
        $("#updatesLine").empty().text("After "+progressData["m_Generation"]+" generations, Best fitness: "+progressData["m_Fitness"]);
    }
}
