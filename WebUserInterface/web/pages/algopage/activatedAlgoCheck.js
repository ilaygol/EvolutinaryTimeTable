
$(function() {
    $.ajax({
        url: "progressUpdate",
        timeout: 1000,
        success: function (progressData) {
            if(progressData["m_isPaused"])
            {
                resetBars(progressData);
                $("#statusLine").empty().text("Generation made: "+progressData["m_GenerationMade"]);
                $("#updatesLine").empty().text("After "+progressData["m_GenerationMade"]+" generations, Best fitness: "+progressData["m_Fitness"]);
                pauseBtnDisabilityManagement();

            }
            else if(progressData["m_IsRunningAlgo"])
            {
                resetBars(progressData);
                labelsUpdate=setInterval(algoProgressUpdate,1000);
                playBtnDisabilityManagement();
            }
            else if(progressData["m_AlreadyActivatedAlgo"])
            {
                resetBars(progressData);
                $("#statusLine").empty().text("Generation made: "+progressData["m_GenerationMade"]);
                $("#updatesLine").empty().text("After "+progressData["m_GenerationMade"]+" generations, Best fitness: "+progressData["m_Fitness"]);
                stopBtnDisabilityManagement();
            }
        }
    });
});

function resetBars(progressData)
{
    if(progressData["m_IsGenerationStopPicked"])
    {
        var reqGeneration=document.getElementById("reqGenerationTXT").value;
        document.getElementById("generationProgressBar").setAttribute("aria-valuemax",reqGeneration);
        var generationMade=parseInt(progressData["m_GenerationMade"]);
        var reqGenerationInt=parseInt(reqGeneration);
        var valueGeneration=(generationMade/reqGenerationInt)*100;
        $("#generationProgressBar").css('width', valueGeneration+'%').attr('aria-valuenow', generationMade);
    }
    if(progressData["m_IsFitnessStopPicked"])
    {
        var reqFitness=document.getElementById("reqFitnessTXT").value;
        document.getElementById("fitnessProgressBar").setAttribute("aria-valuemax",reqFitness);
        var fitness=progressData["m_Fitness"];
        var reqFitnessInt=parseInt(reqFitness);
        var valueFitness=(fitness/reqFitnessInt)*100;
        $("#fitnessProgressBar").css('width', valueFitness+'%').attr('aria-valuenow', fitness);
    }
    if(progressData["m_IsTimeStopPicked"])
    {
        var reqTimeInMinutes = parseInt(document.getElementById("reqTimeTXT").value);
        var reqTimeInMillis = reqTimeInMinutes * 60000;
        document.getElementById("timeProgressBar").setAttribute("aria-valuemax", reqTimeInMillis.toString());
        var time=progressData["m_TimePassedInMillis"];
        var valueTime=(time/reqTimeInMillis)*100;
        $("#timeProgressBar").css('width', valueTime+'%').attr('aria-valuenow', time);
    }
}