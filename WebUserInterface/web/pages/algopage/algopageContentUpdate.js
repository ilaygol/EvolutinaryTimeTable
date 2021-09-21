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
        success:function (usersList){
            refreshSolvingUsersList(usersList)
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
            "</td><td><button class='btn btn-danger watchSolution' id='" + index + "' type='button'>Best Solution</button></td></tr>").appendTo($("#solvingTableBody"))
    });
}
