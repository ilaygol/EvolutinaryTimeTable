
$(function() {
    $.ajax({
        url: "progressUpdate",
        timeout: 1000,
        success: function (progressData) {
            if(progressData["m_isPaused"])
            {
                $("#statusLine").empty().text("Generation made: "+progressData["m_Generation"]);
                $("#updatesLine").empty().text("After "+progressData["m_Generation"]+" generations, Best fitness: "+progressData["m_Fitness"]);
                pauseBtnDisabilityManagement();
            }
            else if(progressData["m_IsRunningAlgo"])
            {
                labelsUpdate=setInterval(algoProgressUpdate,1000);
                playBtnDisabilityManagement();
            }
            else if(progressData["m_AlreadyActivatedAlgo"])
            {
                $("#statusLine").empty().text("Generation made: "+progressData["m_Generation"]);
                $("#updatesLine").empty().text("After "+progressData["m_Generation"]+" generations, Best fitness: "+progressData["m_Fitness"]);
            }
        }
    });
})