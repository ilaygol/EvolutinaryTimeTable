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
            refreshProgressLabels(progressData);
            refreshProgressBars(progressData);
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
            "</td><td><button class='btn btn-primary btn-sm checkSetting' id='" + solver["m_SolverID"] + "' name='"+index+"' type='button'>Check Settings</button></td>" +
            "</td><td><button class='btn btn-success btn-sm watchSolution' id='" + solver["m_SolverID"] + "' name='"+index+"' type='button'>Best Solution</button></td></tr>").appendTo($("#solvingTableBody"))
    });
    $(".checkSetting").click(function(){
        var myModal = new bootstrap.Modal(document.getElementById('anotherUserModal'));
        $("#anotherUserHeader").text("Algorithm references of "+userList[this.getAttribute("name")]["m_SolverName"]);
        var id=this.getAttribute("id");
        $.ajax({
            data:"otherUserID="+id,
            url:"otherUserAlgoRef",
            timeout: 2000,
            success: function (algoRefObj){
                $("<table class='table table-striped w-auto text-center align-middle table-bordered border' style='margin: auto'>" +
                    "<tbody><tr><th rowspan='4'>" + "Selection" + "</th><th>" + "Type" + "</th><td>"+algoRefObj["m_SelectionType"]+"</td></tr>" +
                    "<tr><th>" + "Elitism" + "</th><td>" + algoRefObj["m_Elitism"] + "</td></tr>" +
                    "<tr><th>" + "Percent" + "</th><td>" + algoRefObj["m_Percent"] + "</td></tr>" +
                    "<tr><th>" + "PTE" + "</th><td>" + algoRefObj["m_PTE"] + "</td></tr>" +
                    "<tr><th rowspan='3'>" + "Crossover" + "</th><th>" + "Type" + "</th><td>" + algoRefObj["m_CrossoverType"] + "</td></tr>" +
                    "<tr><th>" + "Cutting Points" + "</th><td>" + algoRefObj["m_CuttingPoints"] + "</td></tr>" +
                    "<tr><th>" + "Aspect" + "</th><td>" + algoRefObj["m_Aspect"] + "</td></tr>" +
                    "</tbody></table>").appendTo($("#anotherUserBody").empty());
                $.ajax({
                    url:"mutation",
                    type:'GET',

                    success:function (mutationDataList){
                        var mutationsStr="";
                        $.each(mutationDataList || [], function (index, mutation) {
                            mutationsStr+="<tr><td>" + mutation["m_Name"] +
                                "</td><td>" + mutation["m_Probability"] +
                                "</td><td>" + mutation["m_Tupples"] +
                                "</td><td>" + mutation["m_Component"] +
                                "</td></tr>"
                        });
                        $("<table class='table table-striped table-responsive table-hover text-center table-bordered align-middle mt-3 caption-top'>" +
                            "<caption>Mutations</caption><thead>"+
                            "<tr class='table-primary'>"+
                            "<th>Type</th>"+
                            "<th>Probability</th>"+
                            "<th>Tupples</th>"+
                            "<th>Component</th>"+
                            "</tr></thead><tbody>"+mutationsStr+"</tbody></table>").appendTo($("#anotherUserBody"));
                    }
                });
            }
        });
        myModal.show();
    })

    $(".watchSolution").click(function(){
        var myModal = new bootstrap.Modal(document.getElementById('anotherUserModal'));
        $("#anotherUserHeader").text("Algorithm preferences of "+userList[this.getAttribute("id")]["m_SolverName"]);
        $("<div class='row justify-content-center'>" +
            "<div class='col-9'>" +
                "<div class='input-group mb-3'>" +
                    "<label class='input-group-text' for='showValue'>Show Results By</label>" +
                    "<select class='form-select' id='showValueModal' name='showValue'>" +
                        "<option value='' selected></option>" +
                        "<option value='Raw'>Raw</option>" +
                        "<option value='Teacher'>Teacher</option>" +
                        "<option value='Class'>Class</option>" +
                    "</select>" +
                "</div>" +
            "</div>" +
            "<div class='col-3 align-bottom'>" +
                "<button id='bestSolutionButtonModal' type='button' class='btn btn-success'>Show</button>" +
            "</div>" +
        "</div>").appendTo($("#anotherUserBody").empty());
    myModal.show();
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

function refreshProgressBars(progressData)
{
    if(progressData["m_IsGenerationStopPicked"])
    {
        var generationMade=parseInt(progressData["m_GenerationMade"]);
        var reqGeneration=parseInt(document.getElementById("generationProgressBar").getAttribute("aria-valuemax"));
        var valueGeneration=(generationMade/reqGeneration)*100;
        $("#generationProgressBar").css('width', valueGeneration+'%').attr('aria-valuenow', generationMade);
    }
    if(progressData["m_IsFitnessStopPicked"])
    {
        var fitness=progressData["m_Fitness"];
        var reqfitness=parseInt(document.getElementById("fitnessProgressBar").getAttribute("aria-valuemax"));
        var valueFitness=(fitness/reqfitness)*100;
        $("#fitnessProgressBar").css('width', valueFitness+'%').attr('aria-valuenow', fitness);
    }
    if(progressData["m_IsTimeStopPicked"])
    {
        var time=progressData["m_TimePassedInMillis"];
        var reqTime=parseInt(document.getElementById("timeProgressBar").getAttribute("aria-valuemax"));
        var valueTime=(time/reqTime)*100;
        $("#timeProgressBar").css('width', valueTime+'%').attr('aria-valuenow', time);
    }

}

